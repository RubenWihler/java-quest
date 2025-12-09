package JavaQuest.Game.Core.Army;

import java.util.HashMap;
import java.util.Map;

import JavaQuest.Game.Core.Map.Tile;

//s'occupe de :
// - stock les unitees d'un joueur
// - ajout d'unitees
//
// - permettre d'envoyer (enleve des unitees) un groupe d'unitees (@Squad) sur une tile
// - permettre de recuperer les unitees de la squad sittuer sur une tile
//
// - attaquer une tile ennemie en envoyant une squad dessus
public class ArmyHandler{
    private Map<WarUnitType, Integer> units;
    
    public ArmyHandler(){
        this.units = new HashMap<>();
        WarUnitType.getAllRecruitmentCost().forEach((type, _) -> this.units.put(type, 0));
    }

    public Integer getUnitCount(WarUnitType type){
        return this.units.get(type);
    }

    public void addUnits(WarUnitType type, int count){
        Integer old_count = this.units.putIfAbsent(type, count);
        if (old_count != null){
            this.units.put(type, old_count + count);
        }
    }

    public boolean removeUnits(WarUnitType type, int count){
        int army_count = this.units.getOrDefault(type, 0);
        if (army_count < count){
            return false;
        }

        this.units.put(type, army_count - count);
        return true;
    }

    //retourn false si l'arme ne contient pas assez de troupe
    public boolean moveUnitsToTile(WarUnitType type, int count, Tile tile){
        if (this.removeUnits(type, count)){
            tile.getSquad().addUnits(type, count);
            return true;
        }

        return false;
    }

    public void recoverUnitsFromTile(Tile tile){
        tile.getSquad().units.forEach((type, count) -> this.addUnits(type, count));
        tile.getSquad().units.clear();
    }

    // public boolean attackTile(Squad squad, Tile tile){
    //     return squad.fight(tile);
    // }
}
