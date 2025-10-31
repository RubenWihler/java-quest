package JavaQuest.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private long seed;

    public Game(GameConfig config) throws IOException {
        this.config = config;
        this.round_index = 0;
        this.round = null;
        
        var rand = new Random();
        this.seed = Math.abs(rand.nextLong());

        initPlayers();
        initMap();
    }

    public GameConfig getConfig(){
        return this.config;
    }

    public Round getRound(){
        return round;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public Map getMap(){
        return this.map;
    }

    public long getSeed(){
        return this.seed;
    }

    public Random getRandom(){
        return new Random(seed);
    }

    public void start() throws IOException, InterruptedException {
        roundLoop();
    }

    private void roundLoop() throws IOException, InterruptedException {
        boolean runing = true;

        while(runing){
            this.round = new Round(this, round_index++);
            this.round.start();
        }
    }

    private void initPlayers(){
        this.players = new ArrayList<>();
        //ui popup
        for (int i = 0; i < this.config.playerCount; i++){
            String playerName = Renderer.getUi().dialog_input(
                "Player " + (i+1) + " Name",
                "Enter name for player " + (i+1) + ":");

            this.players.add(new Player(i, playerName));
        }
    }

    private void initMap(){
        this.map = new MapBuilder()
            .generateTiles(
                this.getRandom(), 
                this.config.mapWidth, 
                this.config.mapHeight, 
                MapBuilder.generatorRandom
            )
            .build();
    }
}
