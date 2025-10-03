package JavaQuest.Game.Inputs;

import JavaQuest.Exceptions.*;
import java.util.*;

public class InputManager {
    private Scanner scanner;

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

    public InputManager(){
        this.scanner = new Scanner(System.in);
    }

    public static String readLine(){
        return getInstance().scanner.nextLine();
    }
}
