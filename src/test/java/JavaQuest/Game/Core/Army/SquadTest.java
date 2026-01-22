package JavaQuest.Game.Core.Army;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Forest;

public class SquadTest extends TestCase {

    private Squad squad;

    public SquadTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(SquadTest.class);
    }

    protected void setUp() {
        squad = new Squad();
    }

    public void testGetTotalPowerPlain() {
        squad.addUnits(WarUnitType.Infantry, 10);
        assertEquals(10, squad.getTotalPower(Biome.Plain));
    }

    public void testGetTotalPowerForestArcherBonus() {
        squad.addUnits(WarUnitType.Archer, 5);
        assertEquals(25, squad.getTotalPower(Biome.Forest));
    }

    public void testGetTotalPowerPlainCavalryBonus() {
        squad.addUnits(WarUnitType.Cavalry, 3);
        assertEquals(21, squad.getTotalPower(Biome.Plain));
    }

    public void testGetTotalPowerMountain() {
        squad.addUnits(WarUnitType.Knight, 5);
        assertEquals(15, squad.getTotalPower(Biome.Montain));
    }

    public void testGetTotalPowerPaladin() {
        squad.addUnits(WarUnitType.Paladin, 3);
        assertEquals(21, squad.getTotalPower(Biome.Plain));
    }

    public void testGetTotalPowerMixedUnits() {
        squad.addUnits(WarUnitType.Infantry, 5);
        squad.addUnits(WarUnitType.Knight, 3);
        assertEquals(14, squad.getTotalPower(Biome.Plain));
    }

    public void testGetTotalPowerEmptySquad() {
        assertEquals(0, squad.getTotalPower(Biome.Plain));
    }

    public void testFightWinningAttack() {
        Plain tile = new Plain(0, 0);
        tile.getSquad().addUnits(WarUnitType.Infantry, 5);

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Knight, 3);

        assertTrue(attacker.fight(tile));
    }

    public void testFightLosingAttack() {
        Plain tile = new Plain(0, 0);
        tile.getSquad().addUnits(WarUnitType.Knight, 5);

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Infantry, 2);

        assertFalse(attacker.fight(tile));
    }

    public void testFightEqualPower() {
        Plain tile = new Plain(0, 0);
        tile.getSquad().addUnits(WarUnitType.Knight, 3);

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Knight, 3);

        assertFalse(attacker.fight(tile));
    }

    public void testFightWithDefenseBonus() {
        Plain tile = new Plain(0, 0);
        tile.getSquad().addUnits(WarUnitType.Infantry, 3);
        tile.setBuild(new JavaQuest.Game.Core.Map.Builds.Outpost());

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Knight, 2);

        assertFalse(attacker.fight(tile));
    }

    public void testFightWithForestBonus() {
        Forest tile = new Forest(0, 0);
        tile.getSquad().addUnits(WarUnitType.Archer, 4);//4*5=20

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Cavalry, 4);//4*5

        assertFalse(attacker.fight(tile));
    }

    public void testFightWithPlainBonus() {
        Plain tile = new Plain(0, 0);
        tile.getSquad().addUnits(WarUnitType.Cavalry, 3);

        Squad attacker = new Squad();
        attacker.addUnits(WarUnitType.Archer, 8);

        assertFalse(attacker.fight(tile));
    }

    public void testRemovePower() {
        squad.addUnits(WarUnitType.Infantry, 10);
        squad.removePower(5, Biome.Plain);
        assertEquals(5, squad.getTotalPower(Biome.Plain));
    }

    public void testRemovePowerClearsSquad() {
        squad.addUnits(WarUnitType.Infantry, 5);
        squad.removePower(10, Biome.Plain);
        assertEquals(0, squad.getTotalPower(Biome.Plain));
    }

    public void testRemovePowerFromMixedUnits() {
        squad.addUnits(WarUnitType.Infantry, 5);
        squad.addUnits(WarUnitType.Knight, 3);
        squad.removePower(7, Biome.Plain);
        assertEquals(6, squad.getTotalPower(Biome.Plain));
    }

    public void testAddUnits() {
        squad.addUnits(WarUnitType.Infantry, 5);
        assertEquals(5, squad.units.get(WarUnitType.Infantry).intValue());
    }

    public void testAddMultipleTimes() {
        squad.addUnits(WarUnitType.Knight, 2);
        squad.addUnits(WarUnitType.Knight, 3);
        assertEquals(5, squad.units.get(WarUnitType.Knight).intValue());
    }

    public void testRemoveUnits() {
        squad.addUnits(WarUnitType.Archer, 10);
        assertTrue(squad.removeUnits(WarUnitType.Archer, 3));
        assertEquals(7, squad.units.get(WarUnitType.Archer).intValue());
    }

    public void testRemoveAllUnits() {
        squad.addUnits(WarUnitType.Cavalry, 5);
        assertTrue(squad.removeUnits(WarUnitType.Cavalry, 5));
        assertNull(squad.units.get(WarUnitType.Cavalry));
    }

    public void testRemoveInsufficientUnits() {
        squad.addUnits(WarUnitType.Paladin, 2);
        assertFalse(squad.removeUnits(WarUnitType.Paladin, 5));
    }
}
