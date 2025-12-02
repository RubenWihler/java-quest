package JavaQuest.Game.Core.Resources;

import java.util.Map;

public enum ResourceType {
    Food,
    Wood,
    Stone,
    Metal,
    Gold;

    public static String getSymbol(ResourceType rtype){
        return getSymbols().get(rtype);
    }

    public static Map<ResourceType, String> getSymbols(){
        return Map.of(
            ResourceType.Food, "♨",
            ResourceType.Wood, "⸙",
            ResourceType.Stone, "❍",
            ResourceType.Metal, "⛁",
            ResourceType.Gold, "⛂"
        );
    }

    public static String toSymbolsString(Map<ResourceType, Integer> res){
        var symbols = getSymbols();
        var sb = new StringBuilder();

        res.forEach((rtype, quantity) -> {
            sb.append(quantity.toString());
            sb.append(symbols.get(rtype));
            sb.append(" ");
        });

        return sb.toString();
    }
}
