package JavaQuest.Game.Rendering.ui;

import java.io.IOException;

import com.googlecode.lanterna.gui2.*;

public class UiBuilder {
    private UiHandler ui;

    public UiBuilder() throws IOException {
        ui = new UiHandler()
            .initContainers()
            .initAction();
    }

    public UiHandler Build(){
        return this.ui.enable();
    }
}
