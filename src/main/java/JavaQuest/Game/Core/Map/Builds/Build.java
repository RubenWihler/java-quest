package JavaQuest.Game.Core.Map.Builds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Log;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Rendering.Renderer;

//〠
public abstract class Build {
    public String name;
    public String symbol;
    public int defenseBonus = 0;
    public Map<ResourceType,Integer> resourcesCost;//ressources qu'il faut pour contruire le bat
    public Map<ResourceType,Integer> resourcesBonus;//ressources donne a chaque tours

    public static Map<String, Supplier<Build>> getBuilds(){
        return Map.of(
            "Farm", () -> new Farm(),
            "Sawmill", () -> new Sawmill(),
            "Mine", () -> new Mine(),
            "Market", () -> new Market(),
            "Barricade", () -> new Barricade(),
            "Outpost", () -> new Outpost()
        );
    }

    public static void showBuildUi(){
        var tile = Renderer.getUi().mapElement.getSelectedTile();

        //verifier qu'une tile est selectionnee
        if (tile == null){
            Log.logError("No tile selected, unable to build !");
            return;
        }

        var actions = new HashMap<String, Runnable>();

        getBuilds().forEach((name, constructor) -> {
            var build = constructor.get();
            String resourceStr = ResourceType.toSymbolsString(build.resourcesCost);

            StringBuilder sb = new StringBuilder();
            sb.append(build.symbol);
            sb.append(build.name);
            sb.append(":");

            //bien gerer l'alignement
            for (int i = 0; i < 40 - (name.length() + resourceStr.length()); i++)
                sb.append(" ");

            sb.append(resourceStr);

            actions.put(sb.toString(), new Runnable() {
                @Override
                public void run() {
                    if (!ConstructBuild(build, tile)){
                        showBuildUi();
                    }
                }
            });
        });

        Renderer.getUi().dialog_action("Build", "Select a build to construct", actions);
    }

    private static boolean ConstructBuild(Build build, Tile tile){
        var player = GameManager.getCurrentGame().getRound().getCurrentPlayer();
        var rh = player.getResourceHandler();

        if (tile.getOwner() != player){
            Renderer.getUi().dialog(
                "Not an owned tile", 
                "You can only build on your tiles !", 
                Arrays.asList(MessageDialogButton.OK));
            
            return false;
        }

        boolean playerHaveResources = build.resourcesCost.entrySet().stream()
            .allMatch((entry) -> rh.get(entry.getKey()) >= entry.getValue());

        if (!playerHaveResources){
            Renderer.getUi().dialog(
                "Not enough resources", 
                "You dont have enough resources to contruct a " + build.name + " !", 
                Arrays.asList(MessageDialogButton.OK));

            return false;
        }
    
        //si la case contient deja un bat 
        //-> dmd a l'utilisateur si il faut le remplacer
        if (tile.getBuild() != null){
            var btn = Renderer.getUi().dialog(
                "Tile already contains build", 
                "Do you want to destroy the existing building [" + tile.getBuild().name + "] to replace it by a " + build.name, 
                Arrays.asList(MessageDialogButton.Yes, MessageDialogButton.No));

            if (btn == null || btn == MessageDialogButton.No){
                return false;
            }
        }

        if (!tile.setBuild(build)){
            Renderer.getUi().dialog(
                "Unsuitable tile", 
                "You cannot build a " + build.name + " on a tile of type " + tile.getBiome() + " !", 
                Arrays.asList(MessageDialogButton.OK));
            
            return false;
        }

        build.resourcesCost.forEach((rtype, quantity) -> rh.sub(rtype, quantity));
        return true;
    }
}
