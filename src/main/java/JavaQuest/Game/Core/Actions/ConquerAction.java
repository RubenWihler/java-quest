package JavaQuest.Game.Core.Actions;

import java.util.List;

import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Map.Tile;

public class ConquerAction extends PlayerAction {
    public Tile tile;

    public boolean execute(){
        if (this.player == null || this.tile == null){
            return false;
        }

        //ajouter la logique de conquete
        //genre si force total armee > defense etc
        this.tile.setOwner(this.player);

        return true;
    }

    public boolean parseParams(List<String> params){
        if (params.size() < 2){
            return false;
        }

        int x = Integer.parseInt(params.get(0));
        int y = Integer.parseInt(params.get(1));

        this.tile = GameManager.getCurrentGame().getMap().getAt(x, y);

        return (this.tile != null);
    }
}
