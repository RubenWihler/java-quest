package JavaQuest.Game.Core.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {
    private List<List<Tile>> tiles;

    public Map(){
        this.tiles = null;
    }

    public Tile getAt(int x, int y){
        return this.tiles.get(y).get(x);
    }

    public int getWidth(){
        return tiles.getFirst().size();
    }

    public int getHeight(){
        return tiles.size();
    }

    public List<List<Tile>> getTiles(){
        return this.tiles;
    }

    public List<Tile> getFlatTiles(){
        return Collections.unmodifiableList(
            this.tiles.stream()
                .flatMap(List::stream)
                .toList()
        );
    }

    public void setTiles(List<List<Tile>> tiles){
        this.tiles = tiles;
    }
}
