package JavaQuest;

import java.util.ArrayList;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import JavaQuest.Game.Rendering.Renderer;

public class Log {
    public static void logError(String msg){
        var btns = new ArrayList<MessageDialogButton>();
        btns.add(MessageDialogButton.Ignore);
        btns.add(MessageDialogButton.Close);

        switch (Renderer.getUi().dialogError("Error", msg, btns)) {
            case MessageDialogButton.Close: throw new RuntimeException(msg);
            case MessageDialogButton.Ignore: break;
            default: break;
        }
    }

    public static void logException(Exception e){
        var sb = new StringBuilder();

        sb.append(e.toString() + "\n");
        sb.append("stack trace:\n");

        for (var ste : e.getStackTrace()) {
            sb.append(ste.toString() + "\n");
        }

        Log.logError(sb.toString());
    }
}
