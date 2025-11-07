package JavaQuest.Game.Rendering;

import java.io.IOException;

import JavaQuest.Exceptions.AlredyInitializedException;
import JavaQuest.Exceptions.NotInitializedException;
import JavaQuest.Game.Rendering.ui.UiBuilder;
import JavaQuest.Game.Rendering.ui.UiHandler;

public class Renderer {
    private UiHandler ui;
    private static Renderer instance;

    public static Renderer getInstance() {
        if (instance == null)
            throw new NotInitializedException();
        return instance;
    }

    public static UiHandler getUi(){
        return getInstance().ui;
    }

    public static Renderer init() throws IOException {
        if (instance != null)
            throw new AlredyInitializedException();

        instance = new Renderer();
        return instance;
    }

    public Renderer() throws IOException {
        this.ui = new UiBuilder().Build();
    }

    public void destroy() throws IOException {
        ui.destroy();
    }

    public void updateRender() throws IOException {
        ui.refresh();
    }
}

