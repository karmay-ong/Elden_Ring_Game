package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;

/**
 * Abstract base class for all meat items in the game.
 * Provides common functionality for edible meat items.
 *
 * @author Kian Lok Chin
 */
public abstract class Meat extends Item implements Eatable {

    /**
     * Constructor for Meat items.
     *
     * @param name the name of the meat item
     * @param displayChar the character to display on the map
     * @param portable true if the item can be picked up
     */
    public Meat(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Default implementation for eating meat.
     * Removes this item from the actor's inventory when eaten.
     * Subclasses can override this method to add specific effects.
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
     * Subclasses can override this method to add additional actions.
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