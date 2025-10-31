package JavaQuest.Game.Rendering;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.LinearLayout.GrowPolicy;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.*;

import JavaQuest.Exceptions.AlredyInitializedException;
import JavaQuest.Exceptions.NotInitializedException;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Resources.ResourceType;
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
        // try{
        // }
        // catch(Exception e){
        //     System.err.println(e);
        // }
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

