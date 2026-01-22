package JavaQuest.Game.Core.Resources;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResourceHandlerTest extends TestCase {

    private ResourceHandler rh;

    public ResourceHandlerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ResourceHandlerTest.class);
    }

    protected void setUp() {
        rh = new ResourceHandler();
    }

    public void testInitialResourcesZero() {
        assertEquals(0, rh.get(ResourceType.Food));
        assertEquals(0, rh.get(ResourceType.Wood));
        assertEquals(0, rh.get(ResourceType.Stone));
        assertEquals(0, rh.get(ResourceType.Metal));
        assertEquals(0, rh.get(ResourceType.Gold));
    }

    public void testAddResources() {
        assertTrue(rh.add(ResourceType.Food, 10));
        assertEquals(10, rh.get(ResourceType.Food));

        assertTrue(rh.add(ResourceType.Gold, 5));
        assertEquals(5, rh.get(ResourceType.Gold));
    }

    public void testAddMultipleTimes() {
        rh.add(ResourceType.Wood, 5);
        rh.add(ResourceType.Wood, 3);
        assertEquals(8, rh.get(ResourceType.Wood));
    }

    public void testSubtractResources() {
        rh.add(ResourceType.Food, 10);
        assertTrue(rh.sub(ResourceType.Food, 5));
        assertEquals(5, rh.get(ResourceType.Food));
    }

    public void testSubtractInsufficientResources() {
        rh.add(ResourceType.Metal, 3);
        assertFalse(rh.sub(ResourceType.Metal, 5));
        assertEquals(3, rh.get(ResourceType.Metal));
    }

    public void testNegativeAdd() {
        rh.add(ResourceType.Gold, 10);
        assertFalse(rh.add(ResourceType.Gold, -15));
        assertEquals(10, rh.get(ResourceType.Gold));
    }

    public void testSubtractAllResources() {
        rh.add(ResourceType.Stone, 10);
        assertTrue(rh.sub(ResourceType.Stone, 10));
        assertEquals(0, rh.get(ResourceType.Stone));
    }

    public void testMultipleResourceOperations() {
        rh.add(ResourceType.Food, 20);
        rh.add(ResourceType.Wood, 15);
        rh.add(ResourceType.Gold, 10);

        assertTrue(rh.sub(ResourceType.Food, 5));
        assertTrue(rh.sub(ResourceType.Wood, 10));

        assertEquals(15, rh.get(ResourceType.Food));
        assertEquals(5, rh.get(ResourceType.Wood));
        assertEquals(10, rh.get(ResourceType.Gold));
    }

    public void testAddZero() {
        assertTrue(rh.add(ResourceType.Metal, 0));
        assertEquals(0, rh.get(ResourceType.Metal));
    }

    public void testSubtractZero() {
        rh.add(ResourceType.Stone, 10);
        assertTrue(rh.sub(ResourceType.Stone, 0));
        assertEquals(10, rh.get(ResourceType.Stone));
    }
}
