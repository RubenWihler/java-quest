package JavaQuest.Game.Core.Map;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public final class MapBuilder {
    private Map map;

    public MapBuilder(){
        this.map = new Map();
    }

    public MapBuilder generateTiles(int width, int height, Supplier<Tile> generator){
        this.map.setTiles(IntStream.range(0, height)
            .mapToObj(_ -> IntStream.range(0, width)
                .mapToObj(_ -> generator.get())
                .collect(Collectors.toList()))
            .collect(Collectors.toList()));

        return this;
    }

    public Map build(){
        return this.map;
    }
}
