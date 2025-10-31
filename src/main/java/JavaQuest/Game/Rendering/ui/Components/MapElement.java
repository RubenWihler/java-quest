package JavaQuest.Game.Rendering.ui.Components;

import java.util.HashMap;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Interactable.FocusChangeDirection;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import JavaQuest.Game.Core.Player;
import JavaQuest.Game.Core.Map.Map;
import JavaQuest.Game.Core.Map.Tile;
import JavaQuest.Game.Rendering.Renderer;

public class MapElement extends Panel {
    private Map map;
    private HashMap<Tile, TileElement> elements;
    private Tile selectedTile;
    private TileElement selectedTileElement;

    public Tile getSelectedTile(){
        return this.selectedTile;
    }

    public void setSelectedTile(Tile tile){
        if (selectedTileElement != null) 
            selectedTileElement.unselect();

        this.selectedTile = tile;

        if (elements.containsKey(tile)){
            elements.get(tile).select();
        }

        Renderer.getUi().updateTile(tile);
    }

    public MapElement(){
        super();
        this.elements = new HashMap<>();
    }

    public void selectNext(char key){
        int x = 0, y = 0;

        if (selectedTileElement != null){
            x = selectedTileElement.x;
            y = selectedTileElement.y;
        }

        switch(key){
            case 'd': x += 1; break;
            case 'a': x -= 1; break;
            case 'w': y -= 1; break;
            case 's': y += 1; break;
            default: break;
        }

        if (x < 0 || x > 19 || y < 0 || y > 19) {
            return;
        }

        setSelectedTile(map.getAt(x, y));
    }

    public void update(Map map){
        this.map = map;

        elements.clear();
        this.removeAllComponents();
        var rows = map.getTiles();
        // String mapString = "";

        for (int y = 0; y < rows.size(); y++){
            var row = rows.get(y);

            for(int x = 0; x < row.size(); x++){
                Tile tile = row.get(x);
                TileElement ele = new TileElement(tile, x, y);

                this.addComponent(ele);

                if (tile == selectedTile){
                    // Renderer.getUi().window.setFocusedInteractable(ele);
                    this.selectedTileElement = ele;
                    ele.select();
                }

                elements.put(tile, ele);
            }
        }
    }
}
