package JavaQuest.Game;

import java.io.IOException;

import JavaQuest.Exceptions.AlredyInitializedException;
import JavaQuest.Exceptions.NotInitializedException;

public final class GameManager {
    private static GameManager instance;

    private Game current_game;

    public static GameManager init() {

        if (GameManager.instance != null){
            throw new AlredyInitializedException();
        }

        GameManager.instance = new GameManager();
        return GameManager.instance;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            throw new NotInitializedException();
        }

        return instance;
    }

    public static Game getCurrentGame(){
        return GameManager.getInstance().current_game;
    }

    public Game newGame(GameConfig config) throws IOException{
        this.current_game = new Game(config);
        return this.current_game;
    }
}
