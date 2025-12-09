package JavaQuest.Game.Rendering.ui.Components;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import JavaQuest.Game.Game;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Resources.ResourceHandler;
import JavaQuest.Game.Core.Resources.ResourceType;
import JavaQuest.Game.Rendering.ui.UiHandler;

public final class RessourceElement extends Panel {
    public Map<ResourceType, Label> ressourceLabels;

    public RessourceElement(){
        super();

        ressourceLabels = Map.of(
            ResourceType.Food, new Label("Food: 0")
            .setLayoutData(UiHandler.layoutCenter)
            .addTo(this),

            ResourceType.Wood, new Label("Wood: 0")
            .setLayoutData(UiHandler.layoutCenter)
            // .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this),

            ResourceType.Stone, new Label("Stone: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            // .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this),

            ResourceType.Metal, new Label("Metal: 0")
            .setLayoutData(UiHandler.layoutCenter)
            // .setLayoutData(UiHandler.layoutEnd)
            .addTo(this),

            ResourceType.Gold, new Label("Gold: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            // .setLayoutData(UiHandler.layoutEnd)
            .addTo(this)
        );
    }

    public void update(ResourceHandler rh){
        ressourceLabels.forEach((r, lbl) -> {
            lbl.setText(r.getSymbol() + " " + r.toString() + ": " + rh.get(r));
        });
    }

}
