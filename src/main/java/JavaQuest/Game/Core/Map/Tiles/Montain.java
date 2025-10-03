package JavaQuest.Game.Core.Map.Tiles;

import JavaQuest.Game.Core.Map.Tile;

public class Montain extends Tile {
    @Override
    public String getSymbol(){
        return super.getSymbol().replace("?", "⛰");
    }
}
