package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Map.Build;
import JavaQuest.Game.Core.Resources.ResourceType;

public class Market extends Build {
    public Market(){
        this.name = "Market";
        this.symbol = "⚖ ";
        this.defenseBonus = 0;
        this.resourcesBonus = Map.of(
            ResourceType.Gold, 3
        );
        this.resourcesCost = Map.of(
            ResourceType.Wood,  50,
            ResourceType.Stone, 30,
            ResourceType.Metal, 15
        );
    }
}
