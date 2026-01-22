package JavaQuest.Game.Core.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Water;
import JavaQuest.Game.Core.Map.Tiles.Desert;

import java.util.ArrayList;
import java.util.List;

public class MapTest extends TestCase {

    private Map map;

    public MapTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MapTest.class);
    }

    protected void setUp() {
        map = new Map();
        List<List<Tile>> tiles = new ArrayList<>();
        for (int y = 0; y < 5; y++) {
            List<Tile> row = new ArrayList<>();
            for (int x = 0; x < 5; x++) {
                if (y == 0) {
                    row.add(new Plain(x, y));
                } else if (y == 1) {
                    row.add(new Forest(x, y));
                } else if (y == 2) {
                    row.add(new Montain(x, y));
                } else if (y == 3) {
                    row.add(new Water(x, y));
                } else {
                    row.add(new Desert(x, y));
                }
            }
            tiles.add(row);
        }
        map.setTiles(tiles);
    }

    public void testMapGetDimensions() {
        assertEquals(5, map.getWidth());
        assertEquals(5, map.getHeight());
    }

    public void testMapGetAt() {
        Tile tile = map.getAt(2, 1);
        assertNotNull(tile);
        assertEquals(2, tile.getX());
        assertEquals(1, tile.getY());
        assertEquals(Biome.Forest, tile.getBiome());
    }

    public void testMapGetAtCorner() {
        Tile tile = map.getAt(0, 0);
        assertNotNull(tile);
        assertEquals(0, tile.getX());
        assertEquals(0, tile.getY());
    }

    public void testMapGetAtLastPosition() {
        Tile tile = map.getAt(4, 4);
        assertNotNull(tile);
        assertEquals(4, tile.getX());
        assertEquals(4, tile.getY());
    }

    public void testMapGetTiles() {
        List<List<Tile>> tiles = map.getTiles();
        assertNotNull(tiles);
        assertEquals(5, tiles.size());
        assertEquals(5, tiles.get(0).size());
    }

    public void testMapGetFlatTiles() {
        List<Tile> flatTiles = map.getFlatTiles();
        assertNotNull(flatTiles);
        assertEquals(25, flatTiles.size());
    }

    public void testMapGetFlatTilesUnmodifiable() {
        List<Tile> flatTiles = map.getFlatTiles();
        try {
            flatTiles.add(new Plain(0, 0));
            fail("Should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }

    public void testMapSetTiles() {
        List<List<Tile>> newTiles = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            List<Tile> row = new ArrayList<>();
            for (int x = 0; x < 3; x++) {
                row.add(new Plain(x, y));
            }
            newTiles.add(row);
        }
        map.setTiles(newTiles);
        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeight());
    }

    public void testMapDifferentBiomes() {
        assertEquals(Biome.Plain, map.getAt(0, 0).getBiome());
        assertEquals(Biome.Forest, map.getAt(0, 1).getBiome());
        assertEquals(Biome.Forest, map.getAt(0, 2).getBiome());
        assertEquals(Biome.Water, map.getAt(0, 3).getBiome());
        assertEquals(Biome.Desert, map.getAt(0, 4).getBiome());
    }

    public void testMapEmptyMap() {
        Map emptyMap = new Map();
        assertNull(emptyMap.getTiles());
    }
}
