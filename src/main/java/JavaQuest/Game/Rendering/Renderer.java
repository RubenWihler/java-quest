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

        try{
            instance = new Renderer();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        return instance;
    }

    public Renderer() throws IOException {
        this.ui = new UiBuilder().Build();

        // this.screen = new DefaultTerminalFactory().createScreen();
        // screen.startScreen();
        //
        // var theme = new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.DEFAULT);
        //
        // var layoutDataFillGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.CanGrow);
        // var layoutDataEndGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.CanGrow);
        // var layoutDataCenterGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow);
        // var layoutDataFill = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.None);
        //
        // this.gui = new MultiWindowTextGUI(screen);
        // this.gui.setTheme(theme);
        //
        // this.window = new BasicWindow();
        // window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));
        //
        // // Layout vertical
        // this.wPanel = new Panel();
        // wPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        // wPanel.setPreferredSize(new TerminalSize(
        //     screen.getTerminalSize().getColumns() - 4,
        //     screen.getTerminalSize().getRows() - 4
        // ));
        // // wPanel.setTheme(new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.BLUE_BRIGHT));
        //
        // this.mainPanel = new Panel();
        // mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        // mainPanel.setLayoutData(layoutDataFillGrow);
        // // mainPanel.setPreferredSize(new TerminalSize(
        // //     80,
        // //     70
        // // ));
        // // mainPanel.setTheme(new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.MAGENTA_BRIGHT));
        //
        // this.mapPanel = new Panel();
        // // mapPanel.setLayoutData(layoutDataFillGrow);
        // mapPanel.setLayoutManager(new GridLayout(20)
        //     .setHorizontalSpacing(0)
        //     .setVerticalSpacing(0)
        //     .setBottomMarginSize(1)
        //     .setTopMarginSize(1)
        //     .setRightMarginSize(1)
        //     .setLeftMarginSize(1)
        // );
        // mapPanel.setPreferredSize(new TerminalSize(
        //     42,
        //     42
        // ));
        // mapPanel.setTheme(new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.DEFAULT));
        //
        // this.playerMenuPanel = new Panel();
        // playerMenuPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        // playerMenuPanel.setLayoutData(layoutDataEndGrow);
        // // playerMenuPanel.setTheme(new SimpleTheme(TextColor.ANSI.WHITE, TextColor.ANSI.CYAN_BRIGHT));
        // playerMenuPanel.setPreferredSize(new TerminalSize(
        //     40,
        //     30
        // ));
        //
        // // Zone UI en bas
        // this.botPanel = new Panel();
        // botPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        // // botPanel.setLayoutData(layoutDataFill);
        // botPanel.setPreferredSize(new TerminalSize(
        //     10,
        //     15
        // ));
        //
        // this.mapLabel = new Label("Ici s'affichera la MAP")
        //         .setForegroundColor(TextColor.ANSI.GREEN)
        //         .addStyle(SGR.BOLD)
        //         .setPreferredSize(new TerminalSize(40, 20))
        //         .setLayoutData(layoutDataCenterGrow); // ex: 40 colonnes, 15 lignes
        //
        // Label dialogue = new Label("Bienvenue aventurier !");
        // TextBox input = new TextBox().setValidationPattern(null); // champ libre
        //
        // botPanel.addComponent(dialogue);
        // botPanel.addComponent(new Button("Attaquer", () -> {
        //     dialogue.setText("Tu attaques !");
        // }));
        // botPanel.addComponent(new Button("Fuir", () -> {
        //     dialogue.setText("Tu fuis lâchement...");
        // }));
        // botPanel.addComponent(input);
        //
        // mapPanel.addComponent(mapLabel);
        //
        // mainPanel.addComponent(mapPanel);
        // mainPanel.addComponent(new Separator(Direction.VERTICAL).setLayoutData(layoutDataFill));
        // mainPanel.addComponent(playerMenuPanel);
        //
        // wPanel.addComponent(mainPanel);
        // wPanel.addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(layoutDataFill));
        // wPanel.addComponent(botPanel);
        //
        // window.setComponent(wPanel);
        //
        // // Afficher la fenêtre
        // gui.addWindow(window);
    }

    public void destroy() throws IOException {
        ui.destroy();
    }

    public void updateRender() throws IOException {
        ui.refresh();
    }

    // public void renderPlayerMenu(Player player) throws IOException{
    //     playerMenuPanel.removeAllComponents();
    //
    //     new Label("----- " + player.getName() + " -----")
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addStyle(SGR.BOLD)
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Resources:")
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Gold: " + player.getResourceHandler().get(ResourceType.Gold))
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Wood: " + player.getResourceHandler().get(ResourceType.Wood))
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Food: " + player.getResourceHandler().get(ResourceType.Food))
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Stone: " + player.getResourceHandler().get(ResourceType.Stone))
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    //
    //     new Label("Metal: " + player.getResourceHandler().get(ResourceType.Metal))
    //         .setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center))
    //         .addTo(playerMenuPanel);
    // }

    // public static void clear() throws IOException {
    // }
}
