package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Map.Build;
import JavaQuest.Game.Core.Resources.ResourceType;

public class Farm extends Build {
    public Farm(){
        this.name = "Farm";
        this.symbol = "⛺";
        this.defenseBonus = 0;
        this.resourcesBonus = Map.of(ResourceType.Food, 5);
        this.resourcesCost = Map.of(
            ResourceType.Wood, 10,
            ResourceType.Stone, 5
        );
    }
}
