package JavaQuest.Game.Core;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Resources.ResourceHandler;

public final class Player {
    private int id;
    private String name;
    private ResourceHandler resources;
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

    public Player(int id, String name, ANSI color){
        this.id = id;
        this.name = name;
        this.resources = new ResourceHandler();
        this.color = color;
    }
}
