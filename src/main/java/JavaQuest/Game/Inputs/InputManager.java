package JavaQuest.Game.Inputs;

import JavaQuest.Exceptions.*;
import JavaQuest.Game.Core.Army.ArmyUi;
import JavaQuest.Game.Core.Map.Build;
import JavaQuest.Game.Core.Market.Market;
import JavaQuest.Game.Rendering.Renderer;
import JavaQuest.Log;

public class InputManager {
    private static InputManager instance;

    public static InputManager getInstance(){
        if (instance == null){
            throw new NotInitializedException(); 
        }

        return instance;
    }

    public static InputManager init(){
        if (instance != null){
            throw new AlredyInitializedException();
        }

        instance = new InputManager();
        return instance;
    }

    public void processInput(Character keyc){
        switch(keyc){
            //Tile Navigation WASD
            case 'w': 
            case 'a':
            case 's': 
            case 'd': processTileNavigation(keyc); break;

            //shortcut
            case 'm': Market.showMarketUi(); break;
            case 'b': Build.showBuildUi(); break;
            case 'r': ArmyUi.showRecruitment(); break;
            case 'f': ArmyUi.showArmyTileAction(); break;
            default: break;
        }
    }

    private void processTileNavigation(Character keyc){
        int x = 0, y = 0;

        var mapElement = Renderer.getUi().mapElement;
        var selectedTile = mapElement.getSelectedTile();

        if (selectedTile != null){
            x = selectedTile.getX();
            y = selectedTile.getY();
        }

        switch(keyc){
            case 'd': x += 1; break;
            case 'a': x -= 1; break;
            case 'w': y -= 1; break;
            case 's': y += 1; break;
            default: break;
        }

        mapElement.selectTile(x, y);
    }
}
