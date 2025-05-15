package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Interface representing entities that are capable of producing offspring or items.
 * This interface is intended to be implemented by actors that have reproduction capabilities
 * @author YOUSSEF HASSANEIN
 */

public interface Producible {
    /**
     * Perform the production action, creating offspring or items on the game map.
     *
     * @param producer the actor performing the production
     * @param map the game map the actor is located on
     */
    void produce(Actor producer, GameMap map);
}
