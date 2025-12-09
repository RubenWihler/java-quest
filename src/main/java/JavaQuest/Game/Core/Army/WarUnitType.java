package JavaQuest.Game.Core.Army;

import java.util.Map;

import JavaQuest.Game.Core.Army.WarUnitBehaviors.ArcherBehavior;
import JavaQuest.Game.Core.Army.WarUnitBehaviors.CavalryBehavior;
import JavaQuest.Game.Core.Army.WarUnitBehaviors.InfantryBehavior;
import JavaQuest.Game.Core.Army.WarUnitBehaviors.KnightBehavior;
import JavaQuest.Game.Core.Army.WarUnitBehaviors.PaladinBehavior;
import JavaQuest.Game.Core.Resources.ResourceType;

public enum WarUnitType {
    Infantry,
    Knight,
    Paladin,
    Archer,
    Cavalry;

    public WarUnitBehavior getBehavior(){
        switch(this){
            case Infantry: return new InfantryBehavior();
            case Archer: return new ArcherBehavior();
            case Cavalry: return new CavalryBehavior();
            case Knight: return new KnightBehavior();
            case Paladin: return new PaladinBehavior();
            default: return null;
        }
    }

    public Map<ResourceType, Integer> getRecruitmentCost(){
        return getAllRecruitmentCost().get(this);
    }

    public static Map<WarUnitType, Map<ResourceType, Integer>> getAllRecruitmentCost(){
        return Map.of(
            Infantry, Map.of(
                ResourceType.Food, 2,
                ResourceType.Wood, 1
            ),
            Knight, Map.of(
                ResourceType.Food, 3,
                ResourceType.Metal, 1
            ),
            Paladin, Map.of(
                ResourceType.Food, 3,
                ResourceType.Metal, 3,
                ResourceType.Gold, 3
            ),
            Archer, Map.of(
                ResourceType.Food, 2,
                ResourceType.Wood, 3
            ),
            Cavalry, Map.of(
                ResourceType.Food, 5,
                ResourceType.Metal, 3
            )
        );
    }
}
