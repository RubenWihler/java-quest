package JavaQuest.Game.Core.Army;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArmyHandlerTest extends TestCase {

    private ArmyHandler army;

    public ArmyHandlerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ArmyHandlerTest.class);
    }

    protected void setUp() {
        army = new ArmyHandler();
    }

    public void testInitialUnitCounts() {
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Infantry));
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Knight));
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Paladin));
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Archer));
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Cavalry));
    }

    public void testAddUnits() {
        army.addUnits(WarUnitType.Infantry, 5);
        assertEquals((Integer)5, army.getUnitCount(WarUnitType.Infantry));
    }

    public void testAddMultipleTypes() {
        army.addUnits(WarUnitType.Infantry, 3);
        army.addUnits(WarUnitType.Knight, 2);
        army.addUnits(WarUnitType.Archer, 4);

        assertEquals((Integer)3, army.getUnitCount(WarUnitType.Infantry));
        assertEquals((Integer)2, army.getUnitCount(WarUnitType.Knight));
        assertEquals((Integer)4, army.getUnitCount(WarUnitType.Archer));
    }

    public void testAddMultipleTimes() {
        army.addUnits(WarUnitType.Cavalry, 2);
        army.addUnits(WarUnitType.Cavalry, 3);
        assertEquals((Integer)5, army.getUnitCount(WarUnitType.Cavalry));
    }

    public void testRemoveUnits() {
        army.addUnits(WarUnitType.Infantry, 10);
        assertTrue(army.removeUnits(WarUnitType.Infantry, 5));
        assertEquals((Integer)5, army.getUnitCount(WarUnitType.Infantry));
    }

    public void testRemoveAllUnits() {
        army.addUnits(WarUnitType.Knight, 3);
        assertTrue(army.removeUnits(WarUnitType.Knight, 3));
        assertEquals((Integer)0, army.getUnitCount(WarUnitType.Knight));
    }

    public void testRemoveInsufficientUnits() {
        army.addUnits(WarUnitType.Archer, 2);
        assertFalse(army.removeUnits(WarUnitType.Archer, 5));
        assertEquals((Integer)2, army.getUnitCount(WarUnitType.Archer));
    }

    public void testRemoveZeroUnits() {
        army.addUnits(WarUnitType.Paladin, 3);
        assertTrue(army.removeUnits(WarUnitType.Paladin, 0));
        assertEquals((Integer)3, army.getUnitCount(WarUnitType.Paladin));
    }

    public void testMoveUnitsToSquad() {
        army.addUnits(WarUnitType.Infantry, 10);
        Squad squad = new Squad();
        assertTrue(army.moveUnitsToSquad(WarUnitType.Infantry, 5, squad));
        assertEquals((Integer)5, army.getUnitCount(WarUnitType.Infantry));
        assertEquals(5, squad.units.get(WarUnitType.Infantry).intValue());
    }

    public void testMoveInsufficientUnitsToSquad() {
        army.addUnits(WarUnitType.Knight, 3);
        Squad squad = new Squad();
        assertFalse(army.moveUnitsToSquad(WarUnitType.Knight, 5, squad));
        assertEquals((Integer)3, army.getUnitCount(WarUnitType.Knight));
    }

    public void testRecoverUnitsFromSquad() {
        army.addUnits(WarUnitType.Cavalry, 5);
        Squad squad = new Squad();
        army.moveUnitsToSquad(WarUnitType.Cavalry, 3, squad);
        assertTrue(army.recoverUnitsFromSquad(WarUnitType.Cavalry, 2, squad));
        assertEquals((Integer)4, army.getUnitCount(WarUnitType.Cavalry));
    }

    public void testRecoverInsufficientUnitsFromSquad() {
        army.addUnits(WarUnitType.Paladin, 5);
        Squad squad = new Squad();
        army.moveUnitsToSquad(WarUnitType.Paladin, 3, squad);
        assertFalse(army.recoverUnitsFromSquad(WarUnitType.Paladin, 5, squad));
    }
}
