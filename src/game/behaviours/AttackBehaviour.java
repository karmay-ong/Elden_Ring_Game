package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Condition;

public class AttackBehaviour implements Behaviour {

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

