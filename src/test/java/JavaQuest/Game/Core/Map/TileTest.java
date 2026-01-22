package JavaQuest.Game.Core.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Water;
import JavaQuest.Game.Core.Map.Tiles.Desert;
import JavaQuest.Game.Core.Map.Builds.Farm;
import JavaQuest.Game.Core.Map.Builds.Outpost;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Resources.ResourceHandler;

public class TileTest extends TestCase {

    private Plain tile;

    public TileTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TileTest.class);
    }

    protected void setUp() {
        tile = new Plain(5, 10);
    }

    public void testTileGetXY() {
        assertEquals(5, tile.getX());
        assertEquals(10, tile.getY());
    }

    public void testTileGetBiome() {
        assertEquals(Biome.Plain, tile.getBiome());
    }

    public void testTileSetOwner() {
        Player player = new Player(1, "Test", ANSI.RED);
        tile.setOwner(player);
        assertEquals(player, tile.getOwner());
    }

    public void testTileSetBuild() {
        Farm farm = new Farm();
        assertTrue(tile.setBuild(farm));
        assertEquals(farm, tile.getBuild());
    }

    public void testTileGetDefenseNoBuild() {
        assertEquals(0, tile.getDefense());
    }

    public void testTileGetDefenseWithBuild() {
        tile.setBuild(new Outpost());
        assertTrue(tile.getDefense() > 0);
    }

    public void testTileGetTotalPower() {
        tile.getSquad().addUnits(JavaQuest.Game.Core.Army.WarUnitType.Infantry, 5);
        assertEquals(5, tile.getTotalPower());
    }

    public void testTileCollectResources() {
        ResourceHandler rh = new ResourceHandler();
        tile.collectResources(rh);
    }

    public void testTileCollectResourcesWithBuild() {
        tile.setBuild(new Farm());
        ResourceHandler rh = new ResourceHandler();
        tile.collectResources(rh);
    }

    public void testTileGetSymbolNoOwnerNoBuild() {
        assertEquals("", tile.getSymbol());
    }

    public void testTileGetSymbolWithOwner() {
        Player player = new Player(1, "Test", ANSI.RED);
        tile.setOwner(player);
        assertEquals("╳╳", tile.getSymbol());
    }

    public void testTileGetSymbolWithBuild() {
        tile.setBuild(new Farm());
        assertNotNull(tile.getSymbol());
    }

    public void testTileGetSquad() {
        assertNotNull(tile.getSquad());
    }

    public void testTileSetSquad() {
        JavaQuest.Game.Core.Army.Squad squad = new JavaQuest.Game.Core.Army.Squad();
        tile.setSquad(squad);
        assertEquals(squad, tile.getSquad());
    }
}
