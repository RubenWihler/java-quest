package JavaQuest.Game.Core.Market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Log;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Rendering.Renderer;

public class Market {
    public static Map<ResourceType, Integer> getAvailableItems(){
        //pour l'instant le prix des items est tjr le meme
        return Map.of(
            ResourceType.Food, 10,
            ResourceType.Metal, 30,
            ResourceType.Stone, 5,
            ResourceType.Wood, 5
        );
    }

    public static Boolean tryBuyItem(Player player, ResourceType ressource, int quantity){
        Integer unit_price = getAvailableItems().get(ressource);
    
        if (unit_price == null) {
            Log.logError("Ressource not Available in market !");
            return false;
        }

        int total_price = unit_price * quantity;
        var resh = player.getResourceHandler();

        if (!resh.sub(ResourceType.Gold, total_price)) {
            Renderer.getUi().dialog(
                "Not enough money", 
                "You dont have enough gold to proccess payment !", 
                Arrays.asList(MessageDialogButton.OK));

            return false;
        }

        return resh.add(ressource, quantity);
    }

    public static void showMarketUi(){
        var actions = new HashMap<String, Runnable>();

        getAvailableItems().forEach((rtype, cost) -> {
            var sb = new StringBuilder();
            sb.append(rtype);
            sb.append(":");

            for (int i = 0; i < 20 - (rtype.toString().length() + cost.toString().length()); i++)
                sb.append(" ");

            sb.append(cost);
            sb.append(" ");
            sb.append(ResourceType.Gold.getSymbol());

            actions.put(sb.toString(), new Runnable() {
                @Override
                public void run() {
                    showItemBuyUi(rtype);
                }
            });
        });

        Renderer.getUi().dialog_action("Market", "Select an item to buy", actions);
    }

    private static void showItemBuyUi(ResourceType rtype){
        Integer quantity = Renderer.getUi().dialog_input_int(
            "Purchase " + rtype.toString(),
            "Select the quantity of " + rtype.toString() + " \n" + 
                "unit cost: " + getAvailableItems().get(rtype).toString() + " gold");

        //si appyyer sur cancel -> reafiche le market
        if (quantity == null) {
            showMarketUi();
            return;
        }

        var player = GameManager.getCurrentGame().getRound().getCurrentPlayer();
        if (!tryBuyItem(player, rtype, quantity)){
            showItemBuyUi(rtype);
            return;
        }

        showMarketUi();
    }
}
