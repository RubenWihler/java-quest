package JavaQuest.Game.Core.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import JavaQuest.Game.Core.Map.Tiles.Plain;
import JavaQuest.Game.Core.Map.Tiles.Forest;
import JavaQuest.Game.Core.Map.Tiles.Montain;
import JavaQuest.Game.Core.Map.Tiles.Water;
import JavaQuest.Game.Core.Map.Tiles.Desert;

import java.util.Random;

public class MapBuilderTest extends TestCase {

    private MapBuilder builder;

    public MapBuilderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MapBuilderTest.class);
    }

    protected void setUp() {
        builder = new MapBuilder();
    }

    public void testMapBuilderBuild() {
        Map map = builder.build();
        assertNotNull(map);
    }

    public void testMapBuilderGeneratePlain() {
        Map map = builder.generateTiles(new Random(42), 5, 5, MapBuilder.generatorPlain).build();
        assertEquals(5, map.getWidth());
        assertEquals(5, map.getHeight());

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                Tile tile = map.getAt(x, y);
                assertEquals(x, tile.getX());
                assertEquals(y, tile.getY());
                assertTrue(tile instanceof Plain);
            }
        }
    }

    public void testMapBuilderGenerateRandom() {
        Map map = builder.generateTiles(new Random(42), 10, 10, MapBuilder.generatorRandom).build();
        assertEquals(10, map.getWidth());
        assertEquals(10, map.getHeight());

        int plainCount = 0;
        int forestCount = 0;
        int montainCount = 0;
        int waterCount = 0;
        int desertCount = 0;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Tile tile = map.getAt(x, y);
                assertEquals(x, tile.getX());
                assertEquals(y, tile.getY());

                if (tile instanceof Plain) plainCount++;
                else if (tile instanceof Forest) forestCount++;
                else if (tile instanceof Montain) montainCount++;
                else if (tile instanceof Water) waterCount++;
                else if (tile instanceof Desert) desertCount++;
            }
        }

        assertEquals(100, plainCount + forestCount + montainCount + waterCount + desertCount);
    }

    public void testMapBuilderGenerateRandomConsistent() {
        Random rand1 = new Random(42);
        Random rand2 = new Random(42);

        Map map1 = builder.generateTiles(rand1, 5, 5, MapBuilder.generatorRandom).build();
        Map map2 = builder.generateTiles(rand2, 5, 5, MapBuilder.generatorRandom).build();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(map1.getAt(x, y).getBiome(), map2.getAt(x, y).getBiome());
            }
        }
    }

    public void testMapBuilderCustomGenerator() {
        Map map = builder.generateTiles(new Random(), 3, 3, rand -> x -> y -> new Plain(x, y)).build();
        assertEquals(3, map.getWidth());
        assertEquals(3, map.getHeight());
        assertEquals(Biome.Plain, map.getAt(0, 0).getBiome());
    }

    public void testMapBuilderDimensions() {
        Map map = builder.generateTiles(new Random(), 7, 9, MapBuilder.generatorRandom).build();
        assertEquals(7, map.getWidth());
        assertEquals(9, map.getHeight());
    }

    public void testMapBuilderChaining() {
        Map map = new MapBuilder()
            .generateTiles(new Random(42), 4, 4, MapBuilder.generatorPlain)
            .build();

        assertNotNull(map);
        assertEquals(4, map.getWidth());
        assertEquals(4, map.getHeight());
    }
}
