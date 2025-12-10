package JavaQuest.Game.Core.Army;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Tile;

public final class Squad {
    public Map<WarUnitType, Integer> units;

    public Squad(){
        this.units = new HashMap<>();
    }

    public void addUnits(WarUnitType type, int count){
        Integer old_count = this.units.putIfAbsent(type, count);
        if (old_count != null){
            this.units.put(type, old_count + count);
        }
    }

    public boolean removeUnits(WarUnitType type, int count){
        int old_count = this.units.getOrDefault(type, 0);
        if (old_count < count){
            return false;
        }

        this.units.put(type, old_count - count);
        this.units.entrySet().removeIf((entry) -> entry.getValue() == 0);
        return true;
    }

    //self est l'attaquant other est en defense
    //si puissance egal -> defense gagne
    //retourn true si l'attaque a resussi (self a gagner)
    public boolean fight(Tile tile){
        final Squad other = tile.getSquad();
        final Biome biome = tile.getBiome();

        int power_self = getTotalPower(biome);
        int power_other = other.getTotalPower(biome);
        power_other +=  tile.getDefense();

        int rest_self = this.removePower(power_other, biome);
        int rest_other = other.removePower(power_self, biome);

        return rest_self > rest_other;
    }

    //calcule les pertes (unitee morte au combat) pour une puissance donne
    //retourn ce qui reste de la squad sous form de puissance total
    private int removePower(int lost_power, Biome biome) {
        if (getTotalPower(biome) < lost_power){
            this.units.clear();
            return 0;
        }

        while (lost_power > 0 && !this.units.isEmpty()){
            for (var entry : this.units.entrySet()){
                if (lost_power <= 0) break;

                var utype = entry.getKey();
                int old_quantity = entry.getValue();

                if (old_quantity > 0){
                    entry.setValue(old_quantity - 1);
                    lost_power -= utype.getBehavior().getPower(biome);
                }
            }

            this.units.entrySet().removeIf((entry) -> entry.getValue() == 0);
        }

        return getTotalPower(biome);
    }

    private int getTotalPower(Biome biome){
        return units.entrySet().stream().mapToInt(entry -> {
            int quantity = entry.getValue();
            var behavior = entry.getKey().getBehavior();
            return behavior.getPower(biome) * quantity;
        }).sum();
    }
}
