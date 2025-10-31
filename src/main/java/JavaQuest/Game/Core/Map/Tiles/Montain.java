package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Montain extends Tile {
    public Montain(int x, int y) {
        super(x, y);
    }

    @Override
    public ANSI getColor(){
        return ANSI.BLACK_BRIGHT;
    }

    @Override
    public String getSymbol(){
        return "⛰";
    }

    @Override
    public String getBiome(){
        return "Montain";
    }
}
