package JavaQuest.Game.Core.Actions;

import java.util.List;

import JavaQuest.Game.Core.Player;

public class PlayerActionBuilder {
    private PlayerAction action;

    public PlayerActionBuilder(){
        this.action = null;
    }

    public PlayerActionBuilder setAction(String actionName){
        actionName = actionName.toLowerCase();
        actionName = Character.toUpperCase(actionName.charAt(0)) + actionName.substring(1);

        //find in registery
        var supplier = PlayerAction.ACTIONS.get(actionName);

        if (supplier == null){
            throw new IllegalArgumentException("Action inconnue: " + actionName);
        }

        this.action = supplier.get();
        return this;
    }

    public PlayerActionBuilder setPlayer(Player player){
        this.action.player = player;
        return this;
    }

    public PlayerActionBuilder setParams(List<String> params){
        if (!this.action.parseParams(params)){
            throw new IllegalArgumentException("Parametres invalid pour " + this.action.getClass().getName());
        }

        return this;
    }

    public PlayerAction build(){
        return this.action;
    }

}
