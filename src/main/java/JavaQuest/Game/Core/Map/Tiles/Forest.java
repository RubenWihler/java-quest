package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Forest extends Tile {
    public Forest(int x, int y) {
        super(x, y);
    }

    @Override
    public ANSI getColor(){
        return ANSI.CYAN_BRIGHT;
    }

    @Override
    public String getSymbol(){
        return super.getSymbol();
    }

    @Override
    public String getBiome(){
        return "Forest";
    }
}
