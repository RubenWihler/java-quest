package JavaQuest.Game.Core.Army.WarUnitBehaviors;

import JavaQuest.Game.Core.Army.WarUnitBehavior;
import JavaQuest.Game.Core.Map.Biome;

public class CavalryBehavior implements WarUnitBehavior{

    @Override
    public int getPower(Biome biome) {
        if (biome == Biome.Plain){
            return 7;
        }

        return 5;
    }
}
