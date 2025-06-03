package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * BehaviourSelector implementation that selects an action from a random behaviour.
 * Randomly shuffles behaviours and returns an action from one randomly selected behaviour.
 *
 * @author Pemudi Hiruni Halgahawatta Liyanaarachchi
 */

public class RandomSelector implements BehaviourSelector {
    /**
     * Selects an action from a randomly chosen behaviour.
     *
     * @param actor the actor performing the action
     * @param map the game map the actor is on
     * @param behaviours a map of behaviours
     * @return an Action from a randomly selected behaviour, or null if no behaviours exist
     */

    @Override
    public Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours) {
        if (behaviours.isEmpty()) {
            return null;
        }
        List<Behaviour> shuffledBehaviours = new ArrayList<>(behaviours.values());
        Collections.shuffle(shuffledBehaviours);

        Behaviour selectedBehaviour = shuffledBehaviours.get(0);
        return selectedBehaviour.getAction(actor, map);
    }
}
