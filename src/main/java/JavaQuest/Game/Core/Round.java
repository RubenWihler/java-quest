package JavaQuest.Game.Core;

import java.util.*;

import JavaQuest.Game.Game;
import JavaQuest.Game.Inputs.*;
import JavaQuest.Game.Rendering.*;
import JavaQuest.Game.Core.Actions.*;
import JavaQuest.Game.Core.Map.Map;

public final class Round {
    private Game game;

    public Round(Game game){
        this.game = game;
    }

    public void start(){
        var map = this.game.getMap();
        this.updateMap(map);
        Renderer.renderMap(map);

        for (Player player : this.game.getPlayers()){
            collectResources(player, map);
            playerAction(player);
        }
    }

    private void updateMap(Map map){
        //non-player related map update here
    }

    private void collectResources(Player player, Map map){
        var resourceHandler = player.getResourceHandler();

        map.getFlatTiles().stream()
            .filter(tile -> tile.getOwner() == player)
            .forEach(tile -> tile.collectResources(resourceHandler)
        );
    }

    private void playerAction(Player player){
        var builder = new PlayerActionBuilder();
        PlayerAction action = null;

        System.out.println("Tours de " + player.getName() + " :");

        do {
            try {
                String actionString = InputManager.readLine();
                List<String> actionList = Arrays.asList(actionString.split(" "));
                List<String> params = actionList.subList(1, actionList.size());

                action = builder
                    .setAction(actionList.getFirst())
                    .setPlayer(player)
                    .setParams(params)
                    .build();

            } catch (Exception e) {
                System.out.println("Commande invalide: " + e.getMessage());
            }
        } while(action == null || !action.execute());
    }
}
