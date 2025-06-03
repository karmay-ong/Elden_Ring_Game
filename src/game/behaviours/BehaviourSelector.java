package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * Interface for selecting an action based on a set of behaviours.
 * Implementations decide which behaviour to perform for a given actor and map.
 *
 * @author Pemudi Hiruni Halgahawatta Liyanaarachchi
 */

public interface BehaviourSelector {
    /**
     * Selects an action for the specified actor based on the available behaviours.
     *
     * @param actor the actor performing the action
     * @param map the game map the actor is on
     * @param behaviours a map of priority keys to behaviours
     * @return the chosen action, or null if no action is selected
     */

    Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours);
}

