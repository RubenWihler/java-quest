package JavaQuest.Game.Core.Map;

public enum Biome {
    Plain,
    Forest,
    Desert,
    Montain,
    Water;

    public static String getName(Biome biome){
        switch(biome){
            case Desert:    return "Desert";
            case Forest:    return "Forest";
            case Montain:   return "Montain";
            case Plain:     return "Plain";
            case Water:     return "Water";
            default:        return "Unknown";
        }
    }
}
