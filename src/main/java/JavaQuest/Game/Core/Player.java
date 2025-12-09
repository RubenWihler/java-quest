package JavaQuest.Game.Core;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Army.ArmyHandler;
import JavaQuest.Game.Core.Resources.ResourceHandler;

public final class Player {
    private int id;
    private String name;
    private ResourceHandler resources;
    private ArmyHandler army;
    private ANSI color;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public ANSI getColor(){
        return this.color;
    }

    public ResourceHandler getResourceHandler(){
        return this.resources;
    }

    public ArmyHandler getArmyHandler(){
        return this.army;
    }

    public Player(int id, String name, ANSI color){
        this.id = id;
        this.name = name;
        this.resources = new ResourceHandler();
        this.army = new ArmyHandler();
        this.color = color;
    }
}
