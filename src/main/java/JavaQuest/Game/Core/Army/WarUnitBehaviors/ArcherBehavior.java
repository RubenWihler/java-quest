package JavaQuest.Game.Core.Army.WarUnitBehaviors;

import JavaQuest.Game.Core.Army.WarUnitBehavior;
import JavaQuest.Game.Core.Map.Biome;

public class ArcherBehavior implements WarUnitBehavior{

    @Override
    public int getPower(Biome biome) {
        if (biome == Biome.Forest){
            return 5;
        }

        return 2;
    }
}
