package JavaQuest.Game.Rendering.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.LinearLayout.GrowPolicy;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.gui2.WindowListenerAdapter;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;

import JavaQuest.Log;
import JavaQuest.Game.Game;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Core.Market.Market;
import JavaQuest.Game.Rendering.Renderer;
import JavaQuest.Game.Rendering.ui.Components.GameInfoElement;
import JavaQuest.Game.Rendering.ui.Components.MapElement;

public class UiHandler {
    private Terminal term;
    private Screen screen;
    private MultiWindowTextGUI gui;

    public BasicWindow window;
    public Panel topPanel;
    public Panel leftPanel;
    public Panel rightPanel;
    public Panel tilePanel;
    public Panel actionPanel;
    public Panel utilityPanel;
    public Panel resourcePanel;
    public MapElement mapElement;
    public GameInfoElement gameInfoElement;
    public ActionListBox actionLbox;
    public Button testBtn;

    public static LayoutData layoutFillGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.CanGrow);
    public static LayoutData layoutEndGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.CanGrow);
    public static LayoutData layoutCenterGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Center, LinearLayout.GrowPolicy.CanGrow);
    public static LayoutData layoutFill = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.None);
    public static LayoutData layoutStart = LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.None);
    public static LayoutData layoutEnd = LinearLayout.createLayoutData(LinearLayout.Alignment.End, LinearLayout.GrowPolicy.None);
    public static LayoutData layoutStartGrow = LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning, LinearLayout.GrowPolicy.CanGrow);

    public UiHandler() throws IOException {
        term = new DefaultTerminalFactory()
            .createTerminal();

        // term.setMouseCaptureMode(MouseCaptureMode.CLICK_RELEASE_DRAG);
        screen = new TerminalScreen(term);
        screen.startScreen();

        gui = new MultiWindowTextGUI(screen);
        gui.setTheme(new SimpleTheme(ANSI.WHITE, ANSI.DEFAULT));

        this.window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));
        window.setTitle("Java Quest");

        window.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
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
            .setPreferredSize(new TerminalSize(110,30))
            .setLayoutData(layoutEnd);

        mapElement = (MapElement)new MapElement()
            .setLayoutData(layoutFillGrow)
            .setPreferredSize(new TerminalSize(52,42))
            .setLayoutManager(new GridLayout(20)
                .setHorizontalSpacing(0)
                .setVerticalSpacing(0)
                .setBottomMarginSize(1)
                .setTopMarginSize(1)
                .setRightMarginSize(1)
                .setLeftMarginSize(3)
            );

        gameInfoElement = (GameInfoElement)new GameInfoElement()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(80,30))
            .setLayoutData(layoutFillGrow);

        resourcePanel = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(10))
            .setPreferredSize(new TerminalSize(80,30))
            .setLayoutData(layoutFillGrow);


        tilePanel = new Panel()
            .setLayoutManager(new GridLayout(2)
                .setHorizontalSpacing(5)
                .setVerticalSpacing(1)
                .setBottomMarginSize(1)
                .setTopMarginSize(2)
                .setRightMarginSize(1)
                .setLeftMarginSize(5))
            .setPreferredSize(new TerminalSize(42,14))
            .setLayoutData(layoutEnd);

        actionLbox =  new ActionListBox()
            .setLayoutData(layoutFillGrow)
            .setPreferredSize(new TerminalSize(80, 48));

        // actionLbox.setTheme(LanternaThemes.getRegisteredTheme("default"));
        actionLbox.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));

        topPanel
            .addComponent(leftPanel)
            .addComponent(new Separator(Direction.VERTICAL).setLayoutData(layoutFill))
            .addComponent(rightPanel);

        leftPanel
            .addComponent(mapElement)
            .addComponent(tilePanel.withBorder(Borders.singleLine("Tile Editor")));

        rightPanel
            .addComponent(utilityPanel)
            // .addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(layoutFill))
            .addComponent(actionPanel);

        utilityPanel
            .addComponent(resourcePanel.withBorder(Borders.singleLine("Resources")))
            .addComponent(gameInfoElement.withBorder(Borders.singleLine("Game Infos")));

        actionPanel
            .addComponent(actionLbox.withBorder(Borders.singleLine("Actions")));

        window.setComponent(topPanel);

        return this;
    }

    public UiHandler initTile(){
        // new Label("Tile editor:")
        //     .setLayoutData(layoutCenterGrow)
        //     .addStyle(SGR.BOLD)
        //     .addStyle(SGR.UNDERLINE)
        //     .addTo(tilePanel);

        // new Label("Biome: [BIOME_NAME]\nOwner: [PLAYER-NAME]\nBuild: [BUILD]")
        //     .setLayoutData(layoutCenterGrow)
        //     .addTo(tilePanel);

        // new Label("Owner: [PLAYER-NAME]")
        //     .setLayoutData(layoutCenterGrow)
        //     .addTo(tilePanel);
        //
        // new Label("Build: [BUILD]")
        //     .setLayoutData(layoutCenterGrow)
        //     .addTo(tilePanel);

        return this;
    }

    public UiHandler initAction() throws IOException {
        // new Label("Action:")
        //     .setLayoutData(layoutCenterGrow)
        //     .addStyle(SGR.BOLD)
        //     .addStyle(SGR.UNDERLINE)
        //     .addTo(actionPanel);

        var test = new Runnable() {
            @Override
            public void run() {
                Log.logError("not implemented yet");
            }
        };

        var market_action = new Runnable() {
            @Override
            public void run() {
                Market.showMarketUi();
            }
        };

        var finish_turn_action = new Runnable() {
            @Override
            public void run() {
                GameManager.getCurrentGame().getRound().setPlayerTurnFinished();
            }
        };

        actionLbox.addItem("Invest Army", test);
        actionLbox.addItem("Conquer", test);
        actionLbox.addItem("Market", market_action);
        actionLbox.addItem("Build", test);
        actionLbox.addItem("Finish turn", finish_turn_action);

        return this;
    }

    public UiHandler initUtility(){
        // new Label("Utility:")
        //     .setLayoutData(layoutCenterGrow)
        //     .addStyle(SGR.BOLD)
        //     .addStyle(SGR.UNDERLINE)
        //     .addTo(utilityPanel);

        new Label("Wood: 0\nStone: 0\nMetal: 0\nFood: 0\nGold: 0\n [Pas encore mis a jours enlevez pas de points SVPPP]\n")
            .setLayoutData(layoutCenterGrow)
            .addTo(resourcePanel);

        var test = new Panel()
            .setLayoutManager(new LinearLayout(Direction.VERTICAL))
            .setPreferredSize(new TerminalSize(80,30))
            .setLayoutData(layoutFillGrow);

        testBtn = new Button("test").addTo(test);
        new Button("test2").addTo(test);

        
        utilityPanel.addComponent(test.withBorder(Borders.singleLine("Army")));

        return this;
    }

    public UiHandler enable(){
        gui.addWindow(window);
        gui.setActiveWindow(window);
        return this;
    }

    public UiHandler refresh() throws IOException {
        gui.updateScreen();
        screen.doResizeIfNecessary();
        screen.refresh();
        return this;
    }

    static int processInputTick = 0;

    public UiHandler pollEvents() throws IOException {
        KeyStroke key = screen.readInput();
        if (key == null) return this;

        Character keyc = key.getCharacter();

        if (keyc != null) {
            Renderer.getUi().mapElement.selectNext(keyc);

            switch(keyc){
                case 'y': dialog_input("Nom Joueur 1", "entrer le nom du joueur 1"); break;
                case 'i': Log.logError("Test du system de logging"); break;
                default: break;
            }

        }

        gui.handleInput(key);

        return this;
    }

    public void destroy() throws IOException {
        screen.stopScreen();
        // term.setMouseCaptureMode(null);
    }

    public UiHandler updateGameInfo(Game game){
        gameInfoElement.update(game);
        return this;
    }

    public UiHandler updateMap(Map map){
        mapElement.update(map);
        return this;
    }

    public UiHandler updateTile(Tile tile){
        tilePanel.removeAllComponents();

        // new Label("Tile editor:")
        //             .setLayoutData(layoutCenterGrow)
        //             .addStyle(SGR.BOLD)
        //             .addStyle(SGR.UNDERLINE)
        //             .addTo(tilePanel);

        Player owner = tile.getOwner();
        ANSI colorFg = (owner != null) ? owner.getColor() : ANSI.WHITE;
        ANSI colorBg = tile.getColor();

        String cordStr  = tile.getX() + "x " + tile.getY() + "y";
        String biomeStr = tile.getBiome();
        String ownerStr = (tile.getOwner() != null) ? tile.getOwner().getName() : "None";

        new Panel()
            .setLayoutData(layoutFill)
            .setTheme(new SimpleTheme(colorFg, colorBg))
            .setPreferredSize(new TerminalSize(18,10))
            .addTo(tilePanel);

        new Label("\n\n\nCoord: " + cordStr + "\nBiome: " + biomeStr + "\nOwner: " + ownerStr + "\nBuild: None")
            .setLayoutData(layoutFillGrow)
            .setPreferredSize(new TerminalSize(20,15))
            .addTo(tilePanel);

        return this;
    }

    public MessageDialogButton dialog(String title, String desc, List<MessageDialogButton> btns){
        var builder = new MessageDialogBuilder()
            .setTitle(title)
            .setText(desc);

        btns.forEach((btn) -> builder.addButton(btn));

        var dial = builder.build();
        dial.setTheme(LanternaThemes.getRegisteredTheme("bigsnake"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("conqueror"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("defrost"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("blaster"));
        return dial.showDialog(gui);
    }

    public String dialog_input(String title, String text){
        return new TextInputDialogBuilder()
            .setTitle(title)
            .setDescription(text)
            .setValidationPattern(Pattern.compile("[a-zA-Z0-9]*"), "Les characters speciaux ne sont pas autorise")
            .build()
            .showDialog(gui);
    }

    public Integer dialog_input_int(String title, String text){
        Pattern pattern = Pattern.compile("[\"-\"]*[0-9]*");

        String out = new TextInputDialogBuilder()
            .setTitle(title)
            .setDescription(text)
            .setValidationPattern(pattern, "Nombre invalid !")
            .build()
            .showDialog(gui);

        //si a appyer sur cancel
        if (out == null) return null;

        try { return Integer.parseInt(out); }
        catch (Exception e) {
            // Log.logError(e.getMessage());
            return null;
        }
    }


    public void dialog_action(String title, String desc, HashMap<String, Runnable> actions){
        var builder =  new ActionListDialogBuilder()
            .setTitle(title)
            .setDescription(desc);

        actions.forEach((lbl, runnable) -> {
            builder.addAction(lbl, runnable);
        });

        var dial = builder.build();
        // dial.setTheme(LanternaThemes.getRegisteredTheme("bigsnake"));
        dial.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("conqueror"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("defrost"));
        // dial.setTheme(LanternaThemes.getRegisteredTheme("blaster"));
        dial.showDialog(gui);
    }
}
