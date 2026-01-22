package JavaQuest.Game.Core.Map.Builds;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import JavaQuest.Game.Core.Resources.ResourceType;

public class BuildTest extends TestCase {

    public BuildTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BuildTest.class);
    }

    public void testFarmName() {
        Farm farm = new Farm();
        assertEquals("Farm", farm.name);
    }

    public void testFarmSymbol() {
        Farm farm = new Farm();
        assertEquals("⛺", farm.symbol);
    }

    public void testFarmDefenseBonus() {
        Farm farm = new Farm();
        assertEquals(0, farm.defenseBonus);
    }

    public void testFarmResourceBonus() {
        Farm farm = new Farm();
        assertNotNull(farm.resourcesBonus);
        assertEquals(5, farm.resourcesBonus.get(ResourceType.Food).intValue());
    }

    public void testFarmResourceCost() {
        Farm farm = new Farm();
        assertNotNull(farm.resourcesCost);
        assertEquals(10, farm.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(5, farm.resourcesCost.get(ResourceType.Stone).intValue());
    }

    public void testSawmillName() {
        Sawmill sawmill = new Sawmill();
        assertEquals("Sawmill", sawmill.name);
    }

    public void testSawmillSymbol() {
        Sawmill sawmill = new Sawmill();
        assertEquals("⛴ ", sawmill.symbol);
    }

    public void testSawmillDefenseBonus() {
        Sawmill sawmill = new Sawmill();
        assertEquals(0, sawmill.defenseBonus);
    }

    public void testSawmillResourceBonus() {
        Sawmill sawmill = new Sawmill();
        assertNotNull(sawmill.resourcesBonus);
        assertEquals(10, sawmill.resourcesBonus.get(ResourceType.Wood).intValue());
    }

    public void testSawmillResourceCost() {
        Sawmill sawmill = new Sawmill();
        assertNotNull(sawmill.resourcesCost);
        assertEquals(10, sawmill.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(5, sawmill.resourcesCost.get(ResourceType.Stone).intValue());
    }

    public void testMineName() {
        Mine mine = new Mine();
        assertEquals("Mine", mine.name);
    }

    public void testMineSymbol() {
        Mine mine = new Mine();
        assertEquals("⛏ ", mine.symbol);
    }

    public void testMineDefenseBonus() {
        Mine mine = new Mine();
        assertEquals(0, mine.defenseBonus);
    }

    public void testMineResourceBonus() {
        Mine mine = new Mine();
        assertNotNull(mine.resourcesBonus);
        assertEquals(10, mine.resourcesBonus.get(ResourceType.Stone).intValue());
        assertEquals(5, mine.resourcesBonus.get(ResourceType.Metal).intValue());
    }

    public void testMineResourceCost() {
        Mine mine = new Mine();
        assertNotNull(mine.resourcesCost);
        assertEquals(20, mine.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(10, mine.resourcesCost.get(ResourceType.Stone).intValue());
        assertEquals(5, mine.resourcesCost.get(ResourceType.Metal).intValue());
    }

    public void testMarketName() {
        Market market = new Market();
        assertEquals("Market", market.name);
    }

    public void testMarketSymbol() {
        Market market = new Market();
        assertEquals("⚖ ", market.symbol);
    }

    public void testMarketDefenseBonus() {
        Market market = new Market();
        assertEquals(0, market.defenseBonus);
    }

    public void testMarketResourceBonus() {
        Market market = new Market();
        assertNotNull(market.resourcesBonus);
        assertEquals(3, market.resourcesBonus.get(ResourceType.Gold).intValue());
    }

    public void testMarketResourceCost() {
        Market market = new Market();
        assertNotNull(market.resourcesCost);
        assertEquals(50, market.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(30, market.resourcesCost.get(ResourceType.Stone).intValue());
        assertEquals(15, market.resourcesCost.get(ResourceType.Metal).intValue());
    }

    public void testOutpostName() {
        Outpost outpost = new Outpost();
        assertEquals("Outpost", outpost.name);
    }

    public void testOutpostSymbol() {
        Outpost outpost = new Outpost();
        assertEquals("♜ ", outpost.symbol);
    }

    public void testOutpostDefenseBonus() {
        Outpost outpost = new Outpost();
        assertEquals(50, outpost.defenseBonus);
    }

    public void testOutpostResourceBonus() {
        Outpost outpost = new Outpost();
        assertNull(outpost.resourcesBonus);
    }

    public void testOutpostResourceCost() {
        Outpost outpost = new Outpost();
        assertNotNull(outpost.resourcesCost);
        assertEquals(100, outpost.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(100, outpost.resourcesCost.get(ResourceType.Stone).intValue());
        assertEquals(50, outpost.resourcesCost.get(ResourceType.Metal).intValue());
    }

    public void testBarricadeName() {
        Barricade barricade = new Barricade();
        assertEquals("Barricade", barricade.name);
    }

    public void testBarricadeSymbol() {
        Barricade barricade = new Barricade();
        assertEquals("⛼ ", barricade.symbol);
    }

    public void testBarricadeDefenseBonus() {
        Barricade barricade = new Barricade();
        assertEquals(10, barricade.defenseBonus);
    }

    public void testBarricadeResourceBonus() {
        Barricade barricade = new Barricade();
        assertNull(barricade.resourcesBonus);
    }

    public void testBarricadeResourceCost() {
        Barricade barricade = new Barricade();
        assertNotNull(barricade.resourcesCost);
        assertEquals(20, barricade.resourcesCost.get(ResourceType.Wood).intValue());
        assertEquals(10, barricade.resourcesCost.get(ResourceType.Stone).intValue());
        assertEquals(3, barricade.resourcesCost.get(ResourceType.Metal).intValue());
    }
}
