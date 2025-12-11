package JavaQuest.Game.Core.Army;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Log;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Rendering.Renderer;

//  [OK] achat de troupes
//  [OK] envoyer des troupes sur une case
//  [OK] recuperer les troupes sur une case pour les remetre dans l'armee
//
//  [OK] creation d'une squad
//  [OK] attaquer une case ennemie
public final class ArmyUi {

    public static void showRecruitment(){
        var actions = new HashMap<String, Runnable>();

        WarUnitType.getAllRecruitmentCost().forEach((rtype, cost) -> {
            String resourceStr = ResourceType.toSymbolsString(cost);

            StringBuilder sb = new StringBuilder();
            sb.append(rtype.toString());
            sb.append(":");

            //bien gerer l'alignement
            for (int i = 0; i < 40 - (rtype.toString().length() + resourceStr.length()); i++)
                sb.append(" ");

            sb.append(resourceStr);

            actions.put(sb.toString(), new Runnable() {
                @Override
                public void run() {
                    showRecruitSelectQuantity(rtype);
                }
            });
        });

        Renderer.getUi().dialog_action("Recruitment", "Select a type of unit to recruit", actions);
    }

    public static void showArmyTileAction(){
        Tile tile = Renderer.getUi().mapElement.getSelectedTile();
        Player player = GameManager.getCurrentGame().getRound().getCurrentPlayer();

        //verifier qu'une tile est selectionnee
        if (tile == null){
            Log.logError("No tile selected !");
            return;
        }

        Player owner = tile.getOwner();

        if (owner == player){
            //move units
            showManageSquad(player, tile.getSquad());
        }
        else {
            Squad attack_squad = new Squad();
            showManageSquad(player, attack_squad);

            if (attack_squad.fight(tile)){
                tile.setSquad(attack_squad);
                tile.setOwner(player);
                Renderer.getUi().dialog("Fight Win", "your squad win the fight !",
                    Arrays.asList(MessageDialogButton.OK));
            }
            else {
                Renderer.getUi().dialog("Fight Lose", "your squad losse the fight !",
                    Arrays.asList(MessageDialogButton.OK));
            }
        }
    }

    private static void showManageSquad(Player player, Squad squad){
        StringBuilder desc = new StringBuilder();
        desc.append("Units in squad:");

        squad.units.forEach((type, count) -> {
            desc.append("\n" + type + ":");

            //bien gerer l'alignement
            for (int i = 0; i < 30 - (type.toString().length() + count.toString().length()); i++)
                desc.append(" ");

            desc.append(count);
        });

        var actions = new HashMap<String, Runnable>();
        actions.put("Add Units", new Runnable() {
            @Override
            public void run() {
                showMoveUnitToSquad(player, squad);
                showManageSquad(player, squad);
            }
        });
        actions.put("Remove Units", new Runnable() {
            @Override
            public void run() {
                showRecoverUnitFromSquad(player, squad); 
                showManageSquad(player, squad);
            }
        });

        Renderer.getUi().dialog_action(
            "Manage Squad",
            desc.toString(),
            actions);
    }

    //pas de check si la tile appartient bien au currentPlayer
    private static void showMoveUnitToSquad(Player player, Squad squad){
        ArmyHandler ah = player.getArmyHandler();
        var actions = new HashMap<String, Runnable>();

        ah.getUnits().forEach((type, count) -> {
            var sb = new StringBuilder();
            sb.append(type);
            sb.append(":");

            //bien gerer l'alignement
            for (int i = 0; i < 43 - (type.toString().length() + count.toString().length()); i++)
                sb.append(" ");

            sb.append(count.toString());

            actions.put(sb.toString(), new Runnable() {
                @Override
                public void run() {
                    if (!showMoveUnitOfType(player, type, squad)){
                        showMoveUnitToSquad(player, squad);
                    }
                }
            });
        });

        Renderer.getUi().dialog_action(
            "Move Units to Squad",
            "Select a type of unit to move in the squad.",
            actions);
    }

    private static boolean showMoveUnitOfType(Player player, WarUnitType type, Squad squad){
        ArmyHandler ah = player.getArmyHandler();
        int count = ah.getUnitCount(type);

        Integer quantity = Renderer.getUi().dialog_input_int(
            "Move units - " + type,
            "Select the quantity of " + type + " to move into the squad."
                + "\n(" + count + " in army " + squad.units.getOrDefault(type, 0) + " in squad)"
        );

        //si appyyer sur cancel
        if (quantity == null) {
            return false;
        }

        if (quantity <= 0){
            Renderer.getUi().dialog("Invalid quantity", "Only positive values is allowed !",
                Arrays.asList(MessageDialogButton.OK));

            return showMoveUnitOfType(player, type, squad);
        }

        if (quantity > count){
            int needed = quantity - count;
            var sb = new StringBuilder();
            sb.append("You have only " + count +  " " + type);
            sb.append("\nDo you want to recruit " + needed + " more ?");
            sb.append("\n\nIt would cost:");

            type.getRecruitmentCost().forEach((rtype, rcount) -> {
                sb.append("\n  " + rcount * needed + " " + rtype + " " + rtype.getSymbol());
            });

            MessageDialogButton res = Renderer.getUi().dialog("Not enough " + type, sb.toString(),
                Arrays.asList(MessageDialogButton.Yes,MessageDialogButton.No));

            if (res == MessageDialogButton.No || !tryRecruitUnit(player, type, needed)){
                return showMoveUnitOfType(player, type, squad);
            }
        }

        if (!ah.moveUnitsToSquad(type, quantity, squad)){
            Log.logError("Unable to move units");
            return showMoveUnitOfType(player, type, squad);
        }

        return true;
    }

    //pas de check si la tile appartient bien au currentPlayer
    private static void showRecoverUnitFromSquad(Player player, Squad squad){
        ArmyHandler ah = player.getArmyHandler();
        var actions = new HashMap<String, Runnable>();

        squad.units.forEach((type, count) -> {
            var sb = new StringBuilder();
            sb.append(type);
            sb.append(":");

            //bien gerer l'alignement
            for (int i = 0; i < 30 - (type.toString().length() + count.toString().length()); i++)
                sb.append(" ");

            sb.append(count.toString());

            actions.put(sb.toString(), new Runnable() {
                @Override
                public void run() {
                    if (!showRecoverUnitOfType(player, type, squad)){
                        showRecoverUnitFromSquad(player, squad);
                    }
                }
            });
        });

        Renderer.getUi().dialog_action(
            "Recover Units from Squad",
            "Select a type of unit to recover from the squad.",
            actions);
    }

    private static boolean showRecoverUnitOfType(Player player, WarUnitType type, Squad squad){
        ArmyHandler ah = player.getArmyHandler();
        int count = squad.units.getOrDefault(type, 0);

        Integer quantity = Renderer.getUi().dialog_input_int(
            "Recover units - " + type,
            "Select the quantity of " + type + " to recover from the squad."
                + "\n(" + count + " in squad " + ah.getUnitCount(type) + " in army)"
        );

        //si appyyer sur cancel
        if (quantity == null) {
            return false;
        }

        if (quantity <= 0){
            Renderer.getUi().dialog("Invalid quantity", "Only positive values is allowed !",
                Arrays.asList(MessageDialogButton.OK));
            return showRecoverUnitOfType(player, type, squad);
        }

        if (quantity > count){
            Renderer.getUi().dialog("Invalid quantity", "the squad only contains " + count + " " + type + " (entered: " + quantity + ")!",
                Arrays.asList(MessageDialogButton.OK));
            return showRecoverUnitOfType(player, type, squad);
        }

        if (!ah.recoverUnitsFromSquad(type, quantity, squad)){
            Log.logError("Unable to recover units");
            return showRecoverUnitOfType(player, type, squad);
        }

        return true;
    }

    private static void showRecruitSelectQuantity(WarUnitType type){
        var sb = new StringBuilder();
        sb.append("unit cost :\n");
        type.getRecruitmentCost().forEach((rtype, count) -> {
            sb.append("\t" + rtype.getSymbol() + " " + count + " " + rtype.toString());
        });

        Integer quantity = Renderer.getUi().dialog_input_int(
            "Recruitment - " + type.toString(),
            "Select the quantity of " + type.toString() + " \n" + sb.toString()
        );

        //si appyyer sur cancel -> reafiche le menu de recrutement
        if (quantity == null) {
            showRecruitment();
            return;
        }

        var player = GameManager.getCurrentGame().getRound().getCurrentPlayer();
        if (!tryRecruitUnit(player, type, quantity)){
            showRecruitSelectQuantity(type);
            return;
        }

        showRecruitment();
    }

    private static boolean tryRecruitUnit(Player player, WarUnitType type, int count){
        var rh = player.getResourceHandler();

        boolean playerHaveResources = type.getRecruitmentCost().entrySet().stream()
            .allMatch((entry) -> rh.get(entry.getKey()) >= entry.getValue() * count);

        if (!playerHaveResources){
            Renderer.getUi().dialog(
                "Not enough resources", 
                "You dont have enough resources to recruit " + count +  " " + type + " !", 
                Arrays.asList(MessageDialogButton.OK));

            return false;
        }

        type.getRecruitmentCost().forEach((rtype, quantity) -> rh.sub(rtype, quantity));
        player.getArmyHandler().addUnits(type, count);
        return true;
    }
}
