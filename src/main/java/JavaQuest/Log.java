package JavaQuest;

import java.util.ArrayList;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Game.Rendering.Renderer;

public class Log {
    public static void logError(String msg){
        var btns = new ArrayList<MessageDialogButton>();
        btns.add(MessageDialogButton.Ignore);
        btns.add(MessageDialogButton.Close);

        switch (Renderer.getUi().dialog("Error", msg, btns)) {
            case MessageDialogButton.Ignore: break;
            case MessageDialogButton.Close: throw new RuntimeException(msg);
            default: break;
        }
    }
}
