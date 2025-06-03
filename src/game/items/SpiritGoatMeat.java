package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.Status;

/**
 * Meat dropped by Spirit Goat when they die.
 *
 * @author Kian Lok Chin
 */
public class SpiritGoatMeat extends Item implements Eatable {

    /**
     * Constructor for SpiritGoatMeat
     */
    public SpiritGoatMeat() {
        super("Spirit Goat Meat", 'G', true);
        addCapability(Status.BLESSED);
    }

    /**
     * Removes this item from the actor's inventory when consumed.
     *
     * @param actor the actor who is eating the item
     * @param map the map the actor is currently on
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Returns a list of allowable actions for this item, including the option to eat it.
     *
     * @param otherActor the actor interacting with the item
     * @param location the location of the item
     * @return a list of actions the actor can perform on the item
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new EatAction(this));
        return actions;
    }


}
