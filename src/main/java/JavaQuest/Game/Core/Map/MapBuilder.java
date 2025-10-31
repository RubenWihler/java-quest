package JavaQuest.Game.Core.Map;

import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;

import JavaQuest.Game.Core.Map.Tiles.*;

import java.util.stream.Collectors;

public final class MapBuilder {
    private Map map;

    public MapBuilder(){
        this.map = new Map();
    }

    public MapBuilder generateTiles(
        Random rand, 
        int width, 
        int height, 
        Function<Random, Function<Integer, Function<Integer, Tile>>> generator
    ){
        this.map.setTiles(IntStream.range(0, height)
            .mapToObj(y -> IntStream.range(0, width)
                .mapToObj(x -> generator.apply(rand).apply(x).apply(y))
                .collect(Collectors.toList()))
            .collect(Collectors.toList()));

        return this;
    }

    public static Function<Random, Function<Integer, Function<Integer, Tile>>> generatorPlain =
        _ -> x -> y -> new Plain(x, y);

    public static Function<Random, Function<Integer, Function<Integer, Tile>>> generatorRandom =
        rand -> (x -> (y -> {
            switch(rand.nextInt(9)){
                case 0: 
                case 1: 
                case 2: 
                case 3: return new Plain(x, y);
                case 4: 
                case 5: return new Forest(x, y);
                case 6: return new Montain(x, y);
                case 7: return new Water(x, y);
                case 8: return new Desert(x, y);
                default: return new Plain(x, y);
            }
        }));

    public Map build(){
        return this.map;
    }
}
