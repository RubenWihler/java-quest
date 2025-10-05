package JavaQuest.Game.Rendering.ui;

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
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.*;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.Tile;

public class UiHandler {
    private Screen screen;
    private MultiWindowTextGUI gui;

    BasicWindow window;
    Panel topPanel;
    Panel leftPanel;
    Panel rightPanel;
    Panel mapPanel;
    Panel tilePanel;
    Panel actionPanel;
    Panel utilityPanel;

    private static LayoutData layoutFillGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData layoutEndGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData layoutCenterGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow);
    private static LayoutData layoutFill = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.None);
    private static LayoutData layoutStart = LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.None);
    private static LayoutData layoutEnd = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.None);
    private static LayoutData layoutStartGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.CanGrow);

    public UiHandler() throws IOException {
        this.screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        var theme = new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.DEFAULT);

        this.gui = new MultiWindowTextGUI(screen);
        this.gui.setTheme(theme);

        this.window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));
    }

    public UiHandler initContainers(){
        topPanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
            .setPreferredSize(new TerminalSize(
                screen.getTerminalSize().getColumns() - 4,
                screen.getTerminalSize().getRows() - 4
            ));

        leftPanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(45,80))
            .setLayoutData(layoutStart);
            
        rightPanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(80,80))
            .setLayoutData(layoutFillGrow);

        utilityPanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.HORIZONTAL))
            .setPreferredSize(new TerminalSize(80,60))
            .setLayoutData(layoutFillGrow);

        actionPanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(80,30))
            .setLayoutData(layoutEnd);

        mapPanel = new Panel()
            .setLayoutData(layoutFillGrow)
            .setPreferredSize(new TerminalSize(52,42))
            .setLayoutManager(new GridLayout(20)
                .setHorizontalSpacing(0)
                .setVerticalSpacing(0)
                .setBottomMarginSize(1)
                .setTopMarginSize(1)
                .setRightMarginSize(1)
                .setLeftMarginSize(1)
            );

        tilePanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(42,14))
            .setLayoutData(layoutEnd);

        topPanel
            .addComponent(leftPanel)
            .addComponent(new Separator(Direction.VERTICAL).setLayoutData(layoutFill))
            .addComponent(rightPanel);

        leftPanel
            .addComponent(mapPanel)
            .addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(layoutFill))
            .addComponent(tilePanel);

        rightPanel
            .addComponent(utilityPanel)
            .addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(layoutFill))
            .addComponent(actionPanel);

        window.setComponent(topPanel);

        return this;
    }

    public UiHandler initTile(){
        new Label("Tile editor:")
            .setLayoutData(layoutCenterGrow)
            .addStyle(SGR.BOLD)
            .addStyle(SGR.UNDERLINE)
            .addTo(tilePanel);

        new Label("Biome: [BIOME_NAME]\nOwner: [PLAYER-NAME]\nBuild: [BUILD]")
            .setLayoutData(layoutCenterGrow)
            .addTo(tilePanel);

        // new Label("Owner: [PLAYER-NAME]")
        //     .setLayoutData(layoutCenterGrow)
        //     .addTo(tilePanel);
        //
        // new Label("Build: [BUILD]")
        //     .setLayoutData(layoutCenterGrow)
        //     .addTo(tilePanel);

        return this;
    }

    public UiHandler initAction(){
        new Label("Action:")
            .setLayoutData(layoutCenterGrow)
            .addStyle(SGR.BOLD)
            .addStyle(SGR.UNDERLINE)
            .addTo(actionPanel);

        return this;
    }

    public UiHandler initUtility(){
        new Label("Utility:")
            .setLayoutData(layoutCenterGrow)
            .addStyle(SGR.BOLD)
            .addStyle(SGR.UNDERLINE)
            .addTo(utilityPanel);

        return this;

    }

    public UiHandler enable(){
        gui.addWindow(window);
        return this;
    }

    public UiHandler refresh() throws IOException {
        gui.updateScreen();
        screen.doResizeIfNecessary();
        screen.refresh();
        return this;
    }

    public UiHandler pollEvents() throws IOException {
        KeyStroke key = screen.readInput();

        if (key != null){
            //gerer les inputs
        }

        return this;
    }

    public void destroy() throws IOException {
        screen.stopScreen();
    }

    public UiHandler updateMap(Map map){
        mapPanel.removeAllComponents();
        var rows = map.getTiles();
        // String mapString = "";

        for (int y = 0; y < rows.size(); y++){
            var row = rows.get(y);

            for(int x = 0; x < row.size(); x++){
                Tile tile = row.get(x);
                Player owner = tile.getOwner();

                ANSI colorBg = tile.getColor();
                ANSI colorFg = (owner != null) ? owner.getColor() : ANSI.WHITE;
                Theme theme = new SimpleTheme(colorFg, colorBg);

                //setting tile color to their owner player color

                // mapString += tile.getSymbol();
                Panel tilePanel = new Panel();
                tilePanel.setLayoutData(GridLayout.createLayoutData(Alignment.FILL, Alignment.FILL))
                    .setPreferredSize(new TerminalSize(2, 1))
                    .setTheme(theme);

                tilePanel.addComponent(new Label(tile.getSymbol()));

                mapPanel.addComponent(tilePanel);
            }

            // mapString += "\n";
        }

        return this;
    }

    public UiHandler updateTile(){

        return this;
    }
}
