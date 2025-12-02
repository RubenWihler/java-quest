package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Desert extends Tile {
    public Desert(int x, int y) {
        super(x, y);
    }

    @Override
    public ANSI getColor(){
        return ANSI.YELLOW;
    }

    @Override
    public String getSymbol(){
        // return "⛏♜";
        return super.getSymbol();
    }

    @Override
    public String getBiome(){
        return "Desert";
    }
}
