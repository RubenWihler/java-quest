package JavaQuest.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import JavaQuest.Game.Inputs.*;
import JavaQuest.Game.Rendering.Renderer;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Round;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.MapBuilder;
import JavaQuest.Game.Core.Map.Tile;

public final class Game {

    private GameConfig config;
    private int round_index;
    private Round round;
    private List<Player> players;
    private Map map;

    public Game(GameConfig config) throws IOException {
        this.config = config;
        this.round_index = 0;
        this.round = null;

        initPlayers();
        initMap();
    }

    public GameConfig getConfig(){
        return this.config;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public Map getMap(){
        return this.map;
    }

    public void start() throws IOException, InterruptedException {
        roundLoop();
    }

    private void roundLoop() throws IOException, InterruptedException {
        boolean runing = true;

        while(runing){
            this.round = new Round(this);
            this.round.start();
        }
    }

    private void initPlayers(){
        this.players = new ArrayList<>();

        for (int i = 0; i < this.config.playerCount; i++){
            System.out.println("Enter name for player " + (i + 1) + ":");
            // String playerName = InputManager.readLine();
            String playerName = "P"+(i+1);

            this.players.add(new Player(i, playerName));
        }
    }

    private void initMap(){
        this.map = new MapBuilder()
            .generateTiles(this.config.mapWidth, this.config.mapHeight, Tile::getRandom)
            .build();
    }
}
