package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.conditions.Condition;

/**
 * A behaviour that enables an actor to automatically attack any adjacent actor
 * when a specific condition is satisfied.
 * Scans all exits around the actor's current location; if another actor is present
 * and the condition is met, returns an AttackAction targeting that actor.
 *
 * @author Lim Chi Jian
 * @author Kian Lok Chin
 */
public class AttackBehaviour implements Behaviour {
    /**
     * The condition that must be satisfied for the attack to occur
     */
    private Condition condition;

    /**
     * Constructor for the AttackBehaviour.
     *
     * @param condition the condition that must be satisfied for the attack to occur
     */
    public AttackBehaviour (Condition condition){
        this.condition = condition;
    }

    /**
     * Returns an AttackAction against the first actor found in adjacent locations
     * if the specified condition is satisfied. Uses the actor's intrinsic weapon for the attack.
     *
     * @param actor the actor performing the attack
     * @param map the game map where the actor is located
     * @return an AttackAction if an adjacent actor is found and condition is met, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Actor target = destination.getActor();

            if (target != null) {
                if (condition.isSatisfied(here)) {
                    return new AttackAction(target, actor.getIntrinsicWeapon());
                }
            }
        }
        return null;
    }
}
