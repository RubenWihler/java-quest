package JavaQuest.Game.Rendering;

import java.util.List;

import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.Tile;

public class Renderer {
    public static void renderMap(Map map){
        Renderer.clear();//clear avant d'afficher la map

        for (List<Tile> row : map.getTiles()) {
            for (Tile tile : row){
                System.out.print(tile.getSymbol());
            }

            System.out.print("\n");
        }
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
}
