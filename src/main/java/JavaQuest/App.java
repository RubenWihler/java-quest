package JavaQuest;

import JavaQuest.Game.GameConfig;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Inputs.*;

public class App {
    public static void main( String[] args )
    {
        var config = new GameConfig();
        config.mapHeight = 20;
        config.mapWidth = 20;
        config.playerCount = 2;

        InputManager.init();
        var game = GameManager.init().newGame(config);
        game.start();
    }
}
