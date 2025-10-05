package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;

public class Plain extends Tile {
    @Override
    public ANSI getColor(){
        return ANSI.GREEN_BRIGHT;
    }

    @Override
    public String getSymbol(){
        // return "░";
        return "";
    }
}
