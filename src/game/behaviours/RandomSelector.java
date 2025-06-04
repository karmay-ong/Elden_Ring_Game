package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A behavior selector that randomly chooses a behavior to try.
 * If the chosen behavior is not valid, returns null instead of trying others.
 */
public class RandomSelector implements BehaviourSelector {
    private final Random random = new Random();

    /**
     * Selects an action by randomly choosing a behavior to try.
     * If the chosen behavior's action is not valid, returns null.
     *
     * @param actor the actor whose behaviors are being selected from
     * @param map the game map
     * @param behaviours the map of available behaviors
     * @return the action from the randomly chosen behavior if valid, otherwise null
     */
    @Override
    public Action selectAction(Actor actor, GameMap map, Map<Integer, Behaviour> behaviours) {
        if (behaviours.isEmpty()) {
            return null;
        }

        // Convert behaviors to list for random selection
        List<Behaviour> behaviourList = new ArrayList<>(behaviours.values());

        // Select random behavior
        Behaviour selectedBehaviour = behaviourList.get(random.nextInt(behaviourList.size()));

        // Try only the selected behavior
        return selectedBehaviour.getAction(actor, map);
    }
}