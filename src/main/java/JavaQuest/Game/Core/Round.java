package JavaQuest.Game.Core;

import java.io.IOException;
import java.util.*;

import JavaQuest.Game.Game;
import JavaQuest.Game.Inputs.*;
import JavaQuest.Game.Rendering.*;
import JavaQuest.Game.Core.Map.Map;

public final class Round {
    private Game game;
    private Player currentPlayer;
    private int id;
    private boolean flag_player_turn_finished = false;

    public Round(Game game, int id){
        this.game = game;
        this.id = id;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setPlayerTurnFinished(){
        this.flag_player_turn_finished = true;
    }

    public int getID(){
        return this.id;
    }

    public void start() throws IOException, InterruptedException {
        var map = this.game.getMap();

        for (Player player : this.game.getPlayers()){
            this.flag_player_turn_finished = false;
            this.currentPlayer = player;
            collectResources(player, map);
            updateMap(map);

            while (!this.flag_player_turn_finished){
                Renderer.getUi()
                    .updateGameInfo(game)
                    .updateMap(map)
                    .updateRessource(player.getResourceHandler())
                    .updateArmy(player.getArmyHandler())
                    .refresh()
                    .pollEvents()
                    .refresh();
            }
        }

        Thread.sleep(10);
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
}
