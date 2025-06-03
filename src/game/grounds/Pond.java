package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CollectWaterAction;

/**
 * A pond that can be used to collect water for potion making.
 *
 * @author Kian Lok Chin
 */
public class Pond extends Ground {

    /**
     * Constructor for Pond
     */
    public Pond() {
        super('~', "Pond");
    }

    /**
     * Adds a CollectWaterAction to allow actors to collect water from the pond.
     *
     * @param actor The actor at this location
     * @param location The location of the pond
     * @param direction The direction of the pond from the actor
     * @return A list of actions that can be performed on this pond
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        actions.add(new CollectWaterAction());
        return actions;
    }
}
