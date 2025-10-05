package JavaQuest.Game.Rendering.ui;

import java.io.IOException;

import com.googlecode.lanterna.gui2.*;

public class UiBuilder {
    private UiHandler ui;

    private static LayoutData layoutFillGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData layoutEndGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData layoutCenterGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData LayoutFill = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.None);

    public UiBuilder() throws IOException {
        ui = new UiHandler()
            .initContainers()
            .initTile()
            .initAction()
            .initUtility();
    }

    public UiHandler Build(){
        return this.ui.enable();
    }
}
