package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * Interface representing items that can be consumed
 * @author YOUSSEF HASSANEIN
 */
public interface Eatable {
    /**
     * Defines the effect of consuming the item.
     *
     * @param actor the actor consuming the item
     * @param map the game map where the actor is located
     */
    void eat(Actor actor, GameMap map);
}
