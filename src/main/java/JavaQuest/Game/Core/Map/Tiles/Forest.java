package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Tile;

public class Forest extends Tile {
    public Forest(int x, int y) {
        super(x, y);
        this.biome = Biome.Forest;
    }

    @Override
    public ANSI getColor(){
        return ANSI.CYAN_BRIGHT;
    }

    @Override
    public String getSymbol(){
        return super.getSymbol();
    }
}
