package JavaQuest.Game.Core.Map;

import java.util.Random;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Rendering.*;
import JavaQuest.Game.Core.Map.Builds.Build;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Resources.ResourceHandler;
import JavaQuest.Game.Core.Resources.ResourceType;

public class Tile {
    protected Player owner;
    protected Build build;
    protected ResourceType resourceType = null;
    protected int resourceQuantity = 0;
    protected int baseDefense = 0;

    public Player getOwner(){
        return this.owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public Build getBuild(){
        return this.build;
    }

    public int getDefense(){
        int defense = baseDefense;

        if (this.build != null){
            defense += this.build.defenseBonus;
        }

        return defense;
    }

    public void collectResources(ResourceHandler rh){
        if (this.resourceType != null){
            rh.add(this.resourceType, this.resourceQuantity);
        }

        if (this.build != null){
            rh.add(this.build.resourceType, this.build.resourceQuantity);
        }
    }

    public String getSymbol(){
        String res = "";

        if (this.owner != null){
            switch(this.owner.getId()){
                case 0: res += Renderer.GREEN;  break;
                case 1: res += Renderer.RED;    break;
            }
        }

        res += "?";

        if (this.owner != null){
            res += Renderer.RESET;
        }

        return res;
    }

    public static Tile getRandom(){
        var rand = new Random();
        int type = rand.nextInt(2);

        switch(type){
            case 0: return new Plain();
            case 1: return new Forest();
            case 2: return new Montain();
        }

        return new Plain();
    }
}
