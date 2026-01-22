package JavaQuest.Game.Rendering.ui.Components;

import java.util.List;
import java.util.Map;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Army.ArmyHandler;
import JavaQuest.Game.Core.Army.WarUnitType;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Rendering.ui.UiHandler;

public final class ArmyElement extends Panel {
    public Map<WarUnitType, Label> unitsLabels;
    private Label totalPowerLabel;
    private Label totalTileLabel;
    private Label totalBuildLabel;

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

        totalPowerLabel = new Label("total power: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);

        totalTileLabel = new Label("tile owned: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);

        totalBuildLabel = new Label("build owned: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);
    }

    public void update(Player player, List<List<Tile>> tiles){
        ArmyHandler ah = player.getArmyHandler();

        unitsLabels.forEach((type, lbl) -> {
            lbl.setText(type.toString() + ": " + ah.getUnitCount(type));
        });

        var totalPower = ah.getUnits().entrySet().stream()
            .map(e -> e.getKey().getBehavior().getPower(null) * e.getValue())
            .reduce(Integer::sum);

        var totalTileOwned = tiles.stream()
            .flatMap(row -> row.stream())
            .filter(tile -> tile.getOwner() == player)
            .count();

        var totalBuildOwned = tiles.stream()
            .flatMap(row -> row.stream())
            .filter(tile -> tile.getOwner() == player && tile.getBuild() != null)
            .count();

        totalPowerLabel.setText("total power: " + totalPower.get());
        totalTileLabel.setText("tile owned: " + totalTileOwned);
        totalBuildLabel.setText("build owned: " + totalBuildOwned);
    }

}
