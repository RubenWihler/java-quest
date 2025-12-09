package JavaQuest.Game.Core.Resources;

import java.util.Map;

public enum ResourceType {
    Food,
    Wood,
    Stone,
    Metal,
    Gold;

    @Override
    public String toString(){
        return switch(this){
            case Food -> "Food";
            case Wood -> "Wood";
            case Stone -> "Stone";
            case Metal -> "Metal";
            case Gold -> "Gold";
        };
    }

    public String getSymbol(){
        return getSymbols().get(this);
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
