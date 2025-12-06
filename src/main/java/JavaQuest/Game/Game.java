package JavaQuest.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Inputs.*;
import JavaQuest.Game.Rendering.Renderer;
import JavaQuest.Log;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Round;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.MapBuilder;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Resources.ResourceType;

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

        //peut eter bouger ca autre pars:
        var p1_initial_tiles = Arrays.asList(
            map.getAt(2, 9),
            map.getAt(3, 9),
            map.getAt(4, 9),
            map.getAt(3, 8),
            map.getAt(3, 10)
        );

        var p2_initial_tiles = Arrays.asList(
            map.getAt(14, 9),
            map.getAt(15, 9),
            map.getAt(16, 9),
            map.getAt(15, 8),
            map.getAt(15, 10)
        );

        var p1 = players.get(0);
        var p2 = players.get(1);

        p1_initial_tiles.forEach((t) -> t.setOwner(p1));
        p2_initial_tiles.forEach((t) -> t.setOwner(p2));

        p1.getResourceHandler().add(ResourceType.Gold, 1000);
        p1.getResourceHandler().add(ResourceType.Food, 100);
        p1.getResourceHandler().add(ResourceType.Wood, 100);
        p1.getResourceHandler().add(ResourceType.Stone, 100);
        p1.getResourceHandler().add(ResourceType.Metal, 100);

        p2.getResourceHandler().add(ResourceType.Gold, 1000);
        p2.getResourceHandler().add(ResourceType.Food, 100);
        p2.getResourceHandler().add(ResourceType.Wood, 100);
        p2.getResourceHandler().add(ResourceType.Stone, 100);
        p2.getResourceHandler().add(ResourceType.Metal, 100);
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
        //seulement 2 joueur pour l'instant
        var player_color_by_id = Arrays.asList(ANSI.RED, ANSI.BLUE_BRIGHT);

        this.players = new ArrayList<>();
        //ui popup
        for (int i = 0; i < this.config.playerCount; i++){
            var color = player_color_by_id.get(i);
            String player_name = null;

            while (player_name == null){
                player_name = Renderer.getUi().dialog_input(
                    "Player " + (i+1) + " Name",
                    "Enter name for player " + (i+1) + ":");
            }

            this.players.add(new Player(i, player_name, color));
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
