package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * A behavior selector that tries behaviors in priority order (lowest number first).
 */
public class OrderedSelector implements BehaviourSelector {
    /**
     * Selects an action by trying behaviors in priority order.
     * Returns the first valid action found, or null if no valid actions exist.
     *
     * @param actor the actor whose behaviors are being selected from
     * @param map the game map
     * @param behaviours the map of available behaviors
     * @return the first valid action found, or null if none are valid
     */
    @Override
    public Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null) {
                return action;
            }
        }
        return null;
    }
}