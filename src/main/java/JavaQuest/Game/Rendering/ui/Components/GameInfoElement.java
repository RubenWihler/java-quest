package JavaQuest.Game.Rendering.ui.Components;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import JavaQuest.Game.Game;
import JavaQuest.Game.GameManager;
import JavaQuest.Game.Rendering.ui.UiHandler;

public final class GameInfoElement extends Panel {
    public Label lblRound;
    public Label lblCurrentPlayer;
    public Label lblSeed;

    public GameInfoElement(){
        super();

        this.lblRound = new Label("Round: 0")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);
        
        this.lblCurrentPlayer = new Label("Player: CACA")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);

        this.lblSeed = new Label("Seed: ")
            .setLayoutData(UiHandler.layoutCenterGrow)
            .addTo(this);
    }

    public void update(Game game){
        lblRound.setText("Round: " + game.getRound().getID());
        lblCurrentPlayer.setText("Player: " + game.getRound().getCurrentPlayer().getName());
        lblSeed.setText("Seed: " + game.getSeed());
    }

}
