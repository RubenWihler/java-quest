package JavaQuest.Game.Inputs;

import JavaQuest.Exceptions.*;
import JavaQuest.Game.Rendering.Renderer;

import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

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

    public static String readLine(){
        return null;
        // return getInstance().scanner.nextLine();
    }
}
