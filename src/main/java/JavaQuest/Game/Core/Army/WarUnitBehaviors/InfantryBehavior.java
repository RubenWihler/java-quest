package JavaQuest.Game.Core.Army.WarUnitBehaviors;

import JavaQuest.Game.Core.Army.WarUnitBehavior;
import JavaQuest.Game.Core.Map.Biome;

public class InfantryBehavior implements WarUnitBehavior{

    @Override
    public int getPower(Biome biome) {
        return 1;
    }
}
