package JavaQuest.Game.Core.Actions;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import JavaQuest.Game.Core.Player;

public abstract class PlayerAction {
    public Player player;
    public abstract boolean execute();

    public abstract boolean parseParams(List<String> params);

    public static final Map<String, Supplier<PlayerAction>> ACTIONS = Map.of(
        "Conquer", ConquerAction::new
    );
}
