package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Resources.ResourceType;

public class Outpost extends Build {
    public Outpost(){
        this.name = "Outpost";
        this.symbol = "♜ ";
        this.defenseBonus = 50;
        this.resourcesBonus = null;
        this.resourcesCost = Map.of(
            ResourceType.Wood,  100,
            ResourceType.Stone, 100,
            ResourceType.Metal,  50
        );
    }
}
