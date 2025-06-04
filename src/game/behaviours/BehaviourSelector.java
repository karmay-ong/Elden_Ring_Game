package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * Interface for different strategies of selecting behaviors for creatures.
 */
public interface BehaviourSelector {
    /**
     * Selects and returns an action from the available behaviors.
     *
     * @param actor the actor whose behaviors are being selected from
     * @param map the game map
     * @param behaviours the map of available behaviors
     * @return the selected action, or null if no action is selected
     */
    Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours);
}