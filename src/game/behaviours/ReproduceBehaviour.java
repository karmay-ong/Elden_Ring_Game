// game/behaviours/ReproduceBehaviour.java
package game.behaviours;

import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.SpawnAction;
import game.actors.creatures.SpiritGoat;
import game.grounds.Inheritree;

/**
 * Behaviour that spawns a new SpiritGoat if an Inheritree is adjacent.
 */
public class ReproduceBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Only SpiritGoat has this behaviour attached
        Location here = map.locationOf(actor);
        boolean treeNearby = here.getExits().stream()
                .map(e -> e.getDestination().getGround())
                .anyMatch(g -> g instanceof Inheritree);
        if (!treeNearby) return null;
        for (var exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor()) {
                return new SpawnAction(new SpiritGoat(), dest);
            }
        }
        return null;
    }
}
