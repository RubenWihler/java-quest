package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Resources.ResourceType;

public class Mine extends Build {
    public Mine(){
        this.name = "Mine";
        this.symbol = "⛏ ";
        this.defenseBonus = 0;
        this.resourcesBonus = Map.of(
            ResourceType.Stone, 10,
            ResourceType.Metal,  5
        );
        this.resourcesCost = Map.of(
            ResourceType.Wood,  20,
            ResourceType.Stone, 10,
            ResourceType.Metal,  5
        );
    }
}
