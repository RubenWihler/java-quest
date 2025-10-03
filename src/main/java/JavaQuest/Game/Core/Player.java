package JavaQuest.Game.Core;

import JavaQuest.Game.Core.Resources.ResourceHandler;

public final class Player {
    private int id;
    private String name;
    private ResourceHandler resources;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public ResourceHandler getResourceHandler(){
        return this.resources;
    }

    public Player(int id, String name){
        this.id = id;
        this.name = name;
        this.resources = new ResourceHandler();
    }
}
