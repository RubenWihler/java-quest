package JavaQuest.Game.Core.Map.Tiles;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Build;

public class Water extends Tile {
    public Water(int x, int y) {
        super(x, y);
        this.biome = Biome.Water;
    }

    @Override
    public ANSI getColor(){
        return ANSI.BLUE_BRIGHT;
    }

    @Override
    public String getSymbol(){
        return super.getSymbol();
        // return "␥";
    }

    @Override
    public boolean setBuild(Build build){
        //on ne peut pas contruire sur l'eau
        return false;
    }
}
