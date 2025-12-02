package JavaQuest.Game.Core.Map.Builds;

import java.util.Map;

import JavaQuest.Game.Core.Resources.ResourceType;

public class Barricade extends Build {
    public Barricade(){
        this.name = "Barricade";
        this.symbol = "⛼ ";
        this.defenseBonus = 10;
        this.resourcesBonus = null;
        this.resourcesCost = Map.of(
            ResourceType.Wood,  20,
            ResourceType.Stone, 10,
            ResourceType.Metal,  3
        );
    }
}
