package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * BehaviourSelector implementation that selects the first valid action
 * from behaviours ordered by their priority.
 * Behaviours with lower priority keys are checked first.
 *
 * @author Pemudi Hiruni Halgahawatta Liyanaarachchi
 */

public class OrderedSelector implements BehaviourSelector {

    /**
     * Selects the first non-null action from the behaviours in ascending priority order.
     *
     * @param actor the actor performing the action
     * @param map the game map the actor is on
     * @param behaviours a map of priority keys to behaviours
     * @return the first valid Action found, or null if none are valid
     */

    @Override
    public Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours) {
        for (Behaviour behaviour : new TreeMap<>(behaviours).values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null) {
                return action;
            }
        }
        return null;
    }
}




