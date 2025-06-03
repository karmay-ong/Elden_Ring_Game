package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RandomSelector implements BehaviourSelector {
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
