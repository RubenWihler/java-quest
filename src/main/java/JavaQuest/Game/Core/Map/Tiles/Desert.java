package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Desert extends Tile {
    @Override
    public ANSI getColor(){
        return ANSI.YELLOW;
    }

    @Override
    public String getSymbol(){
        // return "⛏♜";
        return "";
    }
}
