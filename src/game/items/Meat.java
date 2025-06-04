package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.Status;

/**
 * Meat class that can be created with a name and status.
 * All meat items are portable and use 'M' as display character.
 *
 * @author Kian Lok Chin
 */
public class Meat extends Item implements Eatable {

    /**
     * Constructor for Meat items.
     *
     * @param name the name of the meat item
     * @param status the status capability to add to this meat
     */
    public Meat(String name, Status status) {
        super(name, 'M', true);
        addCapability(status);
    }

    /**
     * Default implementation for eating meat.
     * Removes this item from the actor's inventory when eaten.
     *
     * @param actor the actor consuming the item
     * @param map the game map where the actor is located
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Returns the list of actions that other actors can perform on this meat item.
     * By default, adds an EatAction allowing the item to be consumed.
     *
     * @param otherActor the actor interacting with the item
     * @param location the location of the item
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new EatAction(this));
        return actions;
    }
}