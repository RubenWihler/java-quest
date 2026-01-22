package JavaQuest.Game.Core.Map.Tiles;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Map.Biome;
import JavaQuest.Game.Core.Map.Builds.Farm;
import JavaQuest.Game.Core.Map.Builds.Outpost;

public class BiomeTest extends TestCase {

    public BiomeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BiomeTest.class);
    }

    public void testPlainBiome() {
        Plain tile = new Plain(0, 0);
        assertEquals(Biome.Plain, tile.getBiome());
        assertEquals(0, tile.getX());
        assertEquals(0, tile.getY());
    }

    public void testPlainColor() {
        Plain tile = new Plain(0, 0);
        assertEquals(ANSI.GREEN_BRIGHT, tile.getColor());
    }

    public void testPlainCoordinates() {
        Plain tile = new Plain(5, 10);
        assertEquals(5, tile.getX());
        assertEquals(10, tile.getY());
    }

    public void testForestBiome() {
        Forest tile = new Forest(0, 0);
        assertEquals(Biome.Forest, tile.getBiome());
    }

    public void testForestColor() {
        Forest tile = new Forest(0, 0);
        assertEquals(ANSI.CYAN_BRIGHT, tile.getColor());
    }

    public void testForestCoordinates() {
        Forest tile = new Forest(3, 7);
        assertEquals(3, tile.getX());
        assertEquals(7, tile.getY());
    }

    public void testMontainBiome() {
        Montain tile = new Montain(0, 0);
        assertEquals(Biome.Forest, tile.getBiome());
    }

    public void testMontainColor() {
        Montain tile = new Montain(0, 0);
        assertEquals(ANSI.BLACK_BRIGHT, tile.getColor());
    }

    public void testMontainCoordinates() {
        Montain tile = new Montain(2, 8);
        assertEquals(2, tile.getX());
        assertEquals(8, tile.getY());
    }

    public void testWaterBiome() {
        Water tile = new Water(0, 0);
        assertEquals(Biome.Water, tile.getBiome());
    }

    public void testWaterColor() {
        Water tile = new Water(0, 0);
        assertEquals(ANSI.BLUE_BRIGHT, tile.getColor());
    }

    public void testWaterCoordinates() {
        Water tile = new Water(4, 6);
        assertEquals(4, tile.getX());
        assertEquals(6, tile.getY());
    }

    public void testWaterCannotBuild() {
        Water tile = new Water(0, 0);
        assertFalse(tile.setBuild(new Farm()));
    }

    public void testDesertBiome() {
        Desert tile = new Desert(0, 0);
        assertEquals(Biome.Desert, tile.getBiome());
    }

    public void testDesertColor() {
        Desert tile = new Desert(0, 0);
        assertEquals(ANSI.YELLOW, tile.getColor());
    }

    public void testDesertCoordinates() {
        Desert tile = new Desert(1, 9);
        assertEquals(1, tile.getX());
        assertEquals(9, tile.getY());
    }

    public void testPlainCanBuild() {
        Plain tile = new Plain(0, 0);
        assertTrue(tile.setBuild(new Farm()));
    }

    public void testForestCanBuild() {
        Forest tile = new Forest(0, 0);
        assertTrue(tile.setBuild(new Outpost()));
    }

    public void testMontainCanBuild() {
        Montain tile = new Montain(0, 0);
        assertTrue(tile.setBuild(new Outpost()));
    }

    public void testDesertCanBuild() {
        Desert tile = new Desert(0, 0);
        assertTrue(tile.setBuild(new Farm()));
    }
}
