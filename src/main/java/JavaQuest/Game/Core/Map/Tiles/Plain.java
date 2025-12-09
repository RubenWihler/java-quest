package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Tile;

public class Plain extends Tile {
    public Plain(int x, int y) {
        super(x, y);
        this.biome = Biome.Plain;
    }

    @Override
    public ANSI getColor(){
        return ANSI.GREEN_BRIGHT;
    }

    @Override
    public String getSymbol(){
        // return "░";
        return super.getSymbol();
    }
}
