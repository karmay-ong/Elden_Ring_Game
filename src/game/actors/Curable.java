package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for entities that can be cured or healed.
 * Any class implementing this interface can be targeted by curing actions.
 *
 * @author Kian Lok Chin
 */
public interface Curable {

    /**
     * Performs the curing action on the implementing entity.
     * The specific healing effect depends on the entity's implementation.
     *
     * @param actor the actor performing the cure
     * @param map the game map where the entity is located
     * @param cureItem the item used to perform the cure
     */
    void cure(Actor actor, GameMap map, Item cureItem);
}
