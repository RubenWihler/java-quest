package JavaQuest.Game.Core.Army;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Core.Map.Biome;

public class WarUnitTypeTest extends TestCase {

    public WarUnitTypeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(WarUnitTypeTest.class);
    }

    public void testWarUnitTypeInfantryBehavior() {
        WarUnitBehavior behavior = WarUnitType.Infantry.getBehavior();
        assertNotNull(behavior);
    }

    public void testWarUnitTypeKnightBehavior() {
        WarUnitBehavior behavior = WarUnitType.Knight.getBehavior();
        assertNotNull(behavior);
    }

    public void testWarUnitTypePaladinBehavior() {
        WarUnitBehavior behavior = WarUnitType.Paladin.getBehavior();
        assertNotNull(behavior);
    }

    public void testWarUnitTypeArcherBehavior() {
        WarUnitBehavior behavior = WarUnitType.Archer.getBehavior();
        assertNotNull(behavior);
    }

    public void testWarUnitTypeCavalryBehavior() {
        WarUnitBehavior behavior = WarUnitType.Cavalry.getBehavior();
        assertNotNull(behavior);
    }

    public void testWarUnitTypeInfantryRecruitmentCost() {
        var cost = WarUnitType.Infantry.getRecruitmentCost();
        assertNotNull(cost);
        assertEquals(2, cost.get(ResourceType.Food).intValue());
        assertEquals(1, cost.get(ResourceType.Wood).intValue());
    }

    public void testWarUnitTypeKnightRecruitmentCost() {
        var cost = WarUnitType.Knight.getRecruitmentCost();
        assertNotNull(cost);
        assertEquals(3, cost.get(ResourceType.Food).intValue());
        assertEquals(1, cost.get(ResourceType.Metal).intValue());
    }

    public void testWarUnitTypePaladinRecruitmentCost() {
        var cost = WarUnitType.Paladin.getRecruitmentCost();
        assertNotNull(cost);
        assertEquals(3, cost.get(ResourceType.Food).intValue());
        assertEquals(3, cost.get(ResourceType.Metal).intValue());
        assertEquals(3, cost.get(ResourceType.Gold).intValue());
    }

    public void testWarUnitTypeArcherRecruitmentCost() {
        var cost = WarUnitType.Archer.getRecruitmentCost();
        assertNotNull(cost);
        assertEquals(2, cost.get(ResourceType.Food).intValue());
        assertEquals(3, cost.get(ResourceType.Wood).intValue());
    }

    public void testWarUnitTypeCavalryRecruitmentCost() {
        var cost = WarUnitType.Cavalry.getRecruitmentCost();
        assertNotNull(cost);
        assertEquals(5, cost.get(ResourceType.Food).intValue());
        assertEquals(3, cost.get(ResourceType.Metal).intValue());
    }

    public void testWarUnitTypeInfantryPower() {
        WarUnitBehavior behavior = WarUnitType.Infantry.getBehavior();
        assertEquals(1, behavior.getPower(Biome.Plain));
        assertEquals(1, behavior.getPower(Biome.Forest));
        assertEquals(1, behavior.getPower(Biome.Desert));
        assertEquals(1, behavior.getPower(Biome.Montain));
    }

    public void testWarUnitTypeKnightPower() {
        WarUnitBehavior behavior = WarUnitType.Knight.getBehavior();
        assertEquals(3, behavior.getPower(Biome.Plain));
        assertEquals(3, behavior.getPower(Biome.Forest));
    }

    public void testWarUnitTypePaladinPower() {
        WarUnitBehavior behavior = WarUnitType.Paladin.getBehavior();
        assertEquals(7, behavior.getPower(Biome.Plain));
        assertEquals(7, behavior.getPower(Biome.Forest));
    }

    public void testWarUnitTypeArcherPowerForestBonus() {
        WarUnitBehavior behavior = WarUnitType.Archer.getBehavior();
        assertEquals(5, behavior.getPower(Biome.Forest));
    }

    public void testWarUnitTypeArcherPowerNoBonus() {
        WarUnitBehavior behavior = WarUnitType.Archer.getBehavior();
        assertEquals(2, behavior.getPower(Biome.Plain));
        assertEquals(2, behavior.getPower(Biome.Desert));
    }

    public void testWarUnitTypeCavalryPowerPlainBonus() {
        WarUnitBehavior behavior = WarUnitType.Cavalry.getBehavior();
        assertEquals(7, behavior.getPower(Biome.Plain));
    }

    public void testWarUnitTypeCavalryPowerNoBonus() {
        WarUnitBehavior behavior = WarUnitType.Cavalry.getBehavior();
        assertEquals(5, behavior.getPower(Biome.Forest));
        assertEquals(5, behavior.getPower(Biome.Desert));
    }

    public void testWarUnitTypeGetAllRecruitmentCost() {
        var allCosts = WarUnitType.getAllRecruitmentCost();
        assertNotNull(allCosts);
        assertEquals(5, allCosts.size());
        assertTrue(allCosts.containsKey(WarUnitType.Infantry));
        assertTrue(allCosts.containsKey(WarUnitType.Knight));
        assertTrue(allCosts.containsKey(WarUnitType.Paladin));
        assertTrue(allCosts.containsKey(WarUnitType.Archer));
        assertTrue(allCosts.containsKey(WarUnitType.Cavalry));
    }
}
