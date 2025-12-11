package JavaQuest.Game.Core.Map;

import java.util.Random;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.GameManager;
import JavaQuest.Game.Rendering.*;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Army.Squad;
import JavaQuest.Game.Core.Map.Build;
import JavaQuest.Game.Core.Map.Tiles.Desert;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Water;
import JavaQuest.Game.Core.Resources.ResourceHandler;
import JavaQuest.Game.Core.Resources.ResourceType;

public abstract class Tile {
    protected int x, y;
    protected int baseDefense = 0;
    protected int resourceQuantity = 0;
    protected ResourceType resourceType = null;
    protected Biome biome;
    protected Build build;
    protected Player owner;
    protected Squad squad;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.squad = new Squad();
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Biome getBiome(){
        return this.biome;
    }

    public Player getOwner(){
        return this.owner;
    }

    public Squad getSquad(){
        return this.squad;
    }

    public void setSquad(Squad squad){
        this.squad = squad;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public Build getBuild(){
        return this.build;
    }

    public boolean setBuild(Build build){
        this.build = build;
        return true;
    }

    public int getTotalPower(){
        return this.getDefense() + this.squad.getTotalPower(this.biome);
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

        if (this.build != null && this.build.resourcesBonus != null){
            this.build.resourcesBonus.forEach((rtype, quantity) -> {
                rh.add(rtype, quantity);
            });
        }
    }

    public String getSymbol(){
        if (this.build != null){
            return this.build.symbol;
        }

        if (this.owner != null){
            return "╳╳";
        }

        return "";
    }

    public static Tile getRandom(Random rand, int x, int y){
        int type = rand.nextInt(9);

        switch(type){
            case 0: return new Plain(x, y);
            case 1: return new Plain(x, y);
            case 2: return new Plain(x, y);
            case 3: return new Forest(x, y);
            case 4: return new Forest(x, y);
            case 5: return new Forest(x, y);
            case 6: return new Montain(x, y);
            case 7: return new Water(x, y);
            case 8: return new Desert(x, y);
            case 9: return new Desert(x, y);
        }

        return new Plain(x, y);
    }
}
