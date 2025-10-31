package JavaQuest.Game.Core.Map;

import java.util.Random;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.GameManager;
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
    protected int x, y;
    protected Player owner;
    protected Build build;
    protected ResourceType resourceType = null;
    protected int resourceQuantity = 0;
    protected int baseDefense = 0;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

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

    public String getBiome(){
        return "None";
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

    public static Tile getRandom(Random rand, int x, int y){
        int type = rand.nextInt(8);

        switch(type){
            case 0: return new Plain(x, y);
            case 1: return new Plain(x, y);
            case 2: return new Plain(x, y);
            case 3: return new Forest(x, y);
            case 4: return new Forest(x, y);
            case 5: return new Montain(x, y);
            case 6: return new Water(x, y);
            case 7: return new Desert(x, y);
        }

        return new Plain(x, y);
    }
}
