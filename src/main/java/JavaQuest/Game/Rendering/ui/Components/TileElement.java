package JavaQuest.Game.Rendering.ui.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.AbstractInteractableComponent;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.InteractableRenderer;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Rendering.Renderer;

public class TileElement extends Panel {
    protected Tile tile;
    public int x, y;

    public void select(){
        ANSI colorBg = ANSI.RED_BRIGHT;
        ANSI colorFg = (tile.getOwner() != null) ? tile.getOwner().getColor() : ANSI.WHITE;
        this.setTheme(new SimpleTheme(colorFg, colorBg));
    }

    public void unselect(){
        ANSI colorBg = tile.getColor();
        ANSI colorFg = (tile.getOwner() != null) ? tile.getOwner().getColor() : ANSI.WHITE;
        this.setTheme(new SimpleTheme(colorFg, colorBg));
    }
 
    public TileElement(Tile tile, int x, int y){
        super();
        this.tile = tile;
        this.x = x;
        this.y = y;

        Player owner = tile.getOwner();
        ANSI colorBg = tile.getColor();
        ANSI colorFg = (owner != null) ? owner.getColor() : ANSI.WHITE;
        Theme theme = new SimpleTheme(colorFg, colorBg);
        //setting tile color to their owner player color

        this.setLayoutData(GridLayout.createLayoutData(Alignment.FILL, Alignment.FILL))
            .setPreferredSize(new TerminalSize(2, 1))
            .setTheme(theme);

        this.addComponent(new Label(tile.getSymbol()));
        // this.setRenderer(new Button.FlatButtonRenderer());
    }

    // @Override
    // protected void afterEnterFocus(Interactable.FocusChangeDirection direction, Interactable previouslyInFocus) {
    //     this.setTheme(new SimpleTheme(ANSI.DEFAULT, ANSI.RED_BRIGHT));
    // }
    //
    // @Override
    // protected void afterLeaveFocus(Interactable.FocusChangeDirection direction, Interactable previouslyInFocus) {
    //     this.setTheme(new SimpleTheme(ANSI.DEFAULT, tile.getColor()));
    // }
}

