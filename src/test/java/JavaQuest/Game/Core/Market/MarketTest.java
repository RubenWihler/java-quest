package JavaQuest.Game.Core.Market;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.googlecode.lanterna.TextColor.ANSI;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Resources.ResourceType;

public class MarketTest extends TestCase {

    public MarketTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MarketTest.class);
    }

    public void testMarketGetPrices() {
        var prices = Market.getAvailableItems();
        assertNotNull(prices);
        assertTrue(prices.containsKey(ResourceType.Food));
        assertTrue(prices.containsKey(ResourceType.Metal));
        assertTrue(prices.containsKey(ResourceType.Stone));
        assertTrue(prices.containsKey(ResourceType.Wood));
    }

    public void testMarketFoodPrice() {
        var prices = Market.getAvailableItems();
        assertEquals(10, prices.get(ResourceType.Food).intValue());
    }

    public void testMarketMetalPrice() {
        var prices = Market.getAvailableItems();
        assertEquals(30, prices.get(ResourceType.Metal).intValue());
    }

    public void testMarketStonePrice() {
        var prices = Market.getAvailableItems();
        assertEquals(5, prices.get(ResourceType.Stone).intValue());
    }

    public void testMarketWoodPrice() {
        var prices = Market.getAvailableItems();
        assertEquals(5, prices.get(ResourceType.Wood).intValue());
    }

    public void testMarketTryBuySuccess() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 50);

        assertTrue(Market.tryBuyItem(player, ResourceType.Food, 3));
        assertEquals(20, player.getResourceHandler().get(ResourceType.Gold));
        assertEquals(3, player.getResourceHandler().get(ResourceType.Food));
    }

    public void testMarketTryBuyMultipleResources() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 100);

        assertTrue(Market.tryBuyItem(player, ResourceType.Wood, 5));
        assertTrue(Market.tryBuyItem(player, ResourceType.Wood, 10));

        assertEquals(15, player.getResourceHandler().get(ResourceType.Wood));
    }

    public void testMarketTryBuyInsufficientGold() {
        //On ne peut pas tester car quand l'or est insuffisant : ouvre un dialogue GUI
        // Player player = new Player(1, "Test", ANSI.RED);
        // player.getResourceHandler().add(ResourceType.Gold, 5);
        //
        // assertFalse(Market.tryBuyItem(player, ResourceType.Metal, 1));
        // assertEquals(5, player.getResourceHandler().get(ResourceType.Gold));
        // assertEquals(0, player.getResourceHandler().get(ResourceType.Metal));
    }

    public void testMarketTryBuyExactlyEnoughGold() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 30);

        assertTrue(Market.tryBuyItem(player, ResourceType.Metal, 1));
        assertEquals(0, player.getResourceHandler().get(ResourceType.Gold));
        assertEquals(1, player.getResourceHandler().get(ResourceType.Metal));
    }

    public void testMarketTryBuyZeroQuantity() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 10);

        assertTrue(Market.tryBuyItem(player, ResourceType.Food, 0));
        assertEquals(10, player.getResourceHandler().get(ResourceType.Gold));
    }

    public void testMarketTryBuyLargeQuantity() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 500);

        assertTrue(Market.tryBuyItem(player, ResourceType.Stone, 50));
        assertEquals(250, player.getResourceHandler().get(ResourceType.Gold));
        assertEquals(50, player.getResourceHandler().get(ResourceType.Stone));
    }

    public void testMarketTryBuyCumulative() {
        Player player = new Player(1, "Test", ANSI.RED);
        player.getResourceHandler().add(ResourceType.Gold, 100);

        assertTrue(Market.tryBuyItem(player, ResourceType.Wood, 10));
        assertEquals(50, player.getResourceHandler().get(ResourceType.Gold));
        assertEquals(10, player.getResourceHandler().get(ResourceType.Wood));

        assertTrue(Market.tryBuyItem(player, ResourceType.Wood, 10));
        assertEquals(0, player.getResourceHandler().get(ResourceType.Gold));
        assertEquals(20, player.getResourceHandler().get(ResourceType.Wood));
    }
}
