package JavaQuest.Game.Core.Army;

import java.util.Arrays;
import java.util.HashMap;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Rendering.Renderer;

//  [OK] achat de troupes
//  [] creation d'une squad
//  [] attaquer une case ennemie
//  [] envoyer des troupes sur une case
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
            .allMatch((entry) -> rh.get(entry.getKey()) >= entry.getValue());

        if (!playerHaveResources){
            Renderer.getUi().dialog(
                "Not enough resources", 
                "You dont have enough resources to recruit " + count +  " " + type.toString() + " !", 
                Arrays.asList(MessageDialogButton.OK));

            return false;
        }

        type.getRecruitmentCost().forEach((rtype, quantity) -> rh.sub(rtype, quantity));
        player.getArmyHandler().addUnits(type, count);
        return true;
    }
}
