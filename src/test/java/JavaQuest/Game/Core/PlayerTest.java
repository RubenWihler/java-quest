package JavaQuest.Game.Core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Resources.ResourceHandler;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Core.Army.ArmyHandler;
import JavaQuest.Game.Core.Army.WarUnitType;

public class PlayerTest extends TestCase {

    private Player player;

    public PlayerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PlayerTest.class);
    }

    protected void setUp() {
        player = new Player(1, "TestPlayer", ANSI.RED);
    }

    public void testPlayerCreation() {
        assertEquals(1, player.getId());
        assertEquals("TestPlayer", player.getName());
        assertEquals(ANSI.RED, player.getColor());
    }

    public void testPlayerGetResourceHandler() {
        assertNotNull(player.getResourceHandler());
    }

    public void testPlayerGetArmyHandler() {
        assertNotNull(player.getArmyHandler());
    }

    public void testPlayerInitialResources() {
        ResourceHandler rh = player.getResourceHandler();
        assertEquals(0, rh.get(ResourceType.Food));
        assertEquals(0, rh.get(ResourceType.Wood));
        assertEquals(0, rh.get(ResourceType.Stone));
        assertEquals(0, rh.get(ResourceType.Metal));
        assertEquals(0, rh.get(ResourceType.Gold));
    }

    public void testPlayerInitialArmy() {
        ArmyHandler ah = player.getArmyHandler();
        assertEquals((Integer)0, ah.getUnitCount(WarUnitType.Infantry));
        assertEquals((Integer)0, ah.getUnitCount(WarUnitType.Knight));
        assertEquals((Integer)0, ah.getUnitCount(WarUnitType.Paladin));
        assertEquals((Integer)0, ah.getUnitCount(WarUnitType.Archer));
        assertEquals((Integer)0, ah.getUnitCount(WarUnitType.Cavalry));
    }

    public void testPlayerDifferentIds() {
        Player player1 = new Player(1, "Player1", ANSI.RED);
        Player player2 = new Player(2, "Player2", ANSI.BLUE);
        assertEquals(1, player1.getId());
        assertEquals(2, player2.getId());
    }

    public void testPlayerDifferentNames() {
        Player player1 = new Player(1, "Alice", ANSI.RED);
        Player player2 = new Player(2, "Bob", ANSI.BLUE);
        assertEquals("Alice", player1.getName());
        assertEquals("Bob", player2.getName());
    }

    public void testPlayerDifferentColors() {
        Player player1 = new Player(1, "RedPlayer", ANSI.RED);
        Player player2 = new Player(2, "BluePlayer", ANSI.BLUE);
        assertEquals(ANSI.RED, player1.getColor());
        assertEquals(ANSI.BLUE, player2.getColor());
    }

    public void testPlayerAddResources() {
        player.getResourceHandler().add(ResourceType.Gold, 100);
        assertEquals(100, player.getResourceHandler().get(ResourceType.Gold));
    }

    public void testPlayerAddUnits() {
        player.getArmyHandler().addUnits(WarUnitType.Infantry, 5);
        assertEquals((Integer)5, player.getArmyHandler().getUnitCount(WarUnitType.Infantry));
    }

    public void testPlayerZeroId() {
        Player playerZero = new Player(0, "ZeroPlayer", ANSI.GREEN);
        assertEquals(0, playerZero.getId());
    }
}
