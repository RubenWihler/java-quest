package JavaQuest.Game.Core.Map;

import java.util.Random;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Rendering.*;
import JavaQuest.Game.Core.Map.Builds.Build;
import JavaQuest.Game.Core.Map.Tiles.Desert;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Water;
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

    public ANSI getColor(){
        return ANSI.DEFAULT;
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
        return "?";
    }

    public static Tile getRandom(){
        var rand = new Random();
        int type = rand.nextInt(8);

        switch(type){
            case 0: return new Plain();
            case 1: return new Plain();
            case 2: return new Plain();
            case 3: return new Forest();
            case 4: return new Forest();
            case 5: return new Montain();
            case 6: return new Water();
            case 7: return new Desert();
        }

        return new Plain();
    }
}
