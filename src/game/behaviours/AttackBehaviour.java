package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Condition;

/**
 * A behaviour that enables an actor to automatically attack any adjacent actor.
 * Scans all exits around the actor's current location; if another actor is present,
 * returns an AttackAction targeting that actor.
 *
 * @author Lim Chi Jian
 * @version ver1.0.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction if there is any actor in an adjacent tile.
     *
     * @param actor the actor performing the behaviour
     * @param map   the game map of the interaction
     * @return an AttackAction targeting the first adjacent actor found, or null if none
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Actor target = destination.getActor();

            if (target != null) {
                Condition alwaysCondition = Condition.ALWAYS;
                if (alwaysCondition.test(actor, map, target)) {
                    return new AttackAction(target, actor.getIntrinsicWeapon());
                }
            }
        }
        return null;
    }
}

