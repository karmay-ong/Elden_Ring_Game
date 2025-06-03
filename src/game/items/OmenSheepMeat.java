package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.Status;

/**
 * Meat dropped by Omen Sheep when they die.
 *
 * @author Kian Lok Chin
 */
public class OmenSheepMeat extends Item implements  Eatable {

    /**
     * Constructor for OmenSheepMeat
     */
    public OmenSheepMeat() {
        super("Omen Sheep Meat", 'M', true);
        addCapability(Status.BLESSED);
    }

    /**
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
     * Returns the list of actions that other actors can perform on this item.
     * Adds an EatAction, allowing the item to be consumed.
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
