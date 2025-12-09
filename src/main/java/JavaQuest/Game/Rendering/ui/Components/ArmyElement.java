package JavaQuest.Game.Rendering.ui.Components;

import java.util.Map;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import JavaQuest.Game.Core.Army.ArmyHandler;
import JavaQuest.Game.Core.Army.WarUnitType;
import JavaQuest.Game.Rendering.ui.UiHandler;

public final class ArmyElement extends Panel {
    public Map<WarUnitType, Label> unitsLabels;

    public ArmyElement(){
        super();

        unitsLabels = Map.of(
            WarUnitType.Infantry, new Label("Infantry: 0")
            .setLayoutData(UiHandler.layoutCenter)
            .addTo(this),

            WarUnitType.Archer, new Label("Archer: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            // .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this),

            WarUnitType.Knight, new Label("Knight: 0")
            .setLayoutData(UiHandler.layoutCenter)
            // .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this),

            WarUnitType.Cavalry, new Label("Cavalry: 0")
            .setLayoutData(UiHandler.layoutCenter)
            // .setLayoutData(UiHandler.layoutEnd)
            .addTo(this),

            WarUnitType.Paladin, new Label("Paladin: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            // .setLayoutData(UiHandler.layoutEnd)
            .addTo(this)
        );
    }

    public void update(ArmyHandler ah){
        unitsLabels.forEach((type, lbl) -> {
            lbl.setText(type.toString() + ": " + ah.getUnitCount(type));
        });
    }

}
