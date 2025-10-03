package JavaQuest.Game.Core.Resources;

import java.util.HashMap;

public class ResourceHandler {
    private HashMap<ResourceType, Integer> resources;

    public ResourceHandler(){
        this.resources = new HashMap<>();
        this.resources.put(ResourceType.Food, 0);
        this.resources.put(ResourceType.Wood, 0);
        this.resources.put(ResourceType.Stone, 0);
        this.resources.put(ResourceType.Metal, 0);
        this.resources.put(ResourceType.Gold, 0);
    }

    public int get(ResourceType type) {
        return this.resources.get(type);
    }

    public boolean add(ResourceType type, int quantity) {

        int newQuantity = this.resources.get(type) + quantity;

        //ne modfi pas la quantite si negatif
        if (newQuantity < 0) {
            return false;
        }

        this.resources.replace(type, newQuantity);
        return true;
    }

    public boolean sub(ResourceType type, int quantity) {
        return this.add(type, -quantity);
    }
}
