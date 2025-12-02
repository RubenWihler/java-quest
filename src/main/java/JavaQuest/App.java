package JavaQuest;

import java.io.IOException;

import JavaQuest.Game.GameConfig;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Inputs.*;
import JavaQuest.Game.Rendering.Renderer;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        var config = new GameConfig();
        config.mapHeight = 20;
        config.mapWidth = 20;
        config.playerCount = 2;

        var renderer = Renderer.init();
        var inputManager = InputManager.init();
        var gm = GameManager.init();
        var game = gm.newGame(config);

        try {
            game.start();
        } catch (Exception e) {
            Log.logException(e);
        }

        renderer.destroy();
    }
}
