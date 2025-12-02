package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Resources.ResourceType;

public class Sawmill extends Build {
    public Sawmill(){
        this.name = "Sawmill";
        this.symbol = "⛴ ";
        this.defenseBonus = 0;
        this.resourcesBonus = Map.of(ResourceType.Wood, 10);
        this.resourcesCost = Map.of(
            ResourceType.Wood, 10,
            ResourceType.Stone, 5
        );
    }
}
