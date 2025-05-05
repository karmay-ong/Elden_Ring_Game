package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Producible {
    void produce(Actor producer, GameMap map);
    boolean canProduce(Actor producer, GameMap map);
}
