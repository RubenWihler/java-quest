package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Water extends Tile {
    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public ANSI getColor(){
        return ANSI.BLUE_BRIGHT;
    }

    @Override
    public String getSymbol(){
        return "␥";
    }

    @Override
    public String getBiome(){
        return "Water";
    }
}
