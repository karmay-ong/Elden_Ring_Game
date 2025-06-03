package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.Status;

/**
 * The meat of a Golden Beetle, a rare ingredient for potions.
 *
 * @author Kian Lok Chin
 */
public class GoldenBeetleMeat extends Item implements  Eatable{

    /**
     * Constructor for GoldenBeetleMeat.
     */
    public GoldenBeetleMeat() {
        super("Golden Beetle Meat", 'B', true);
        this.addCapability(Status.CURSED);
    }

    /**
     * Defines what happens when an actor eats the GoldenBeetleMeat.
     * It is simply removed from the inventory.
     *
     * @param actor the actor eating the item
     * @param map the current game map
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Returns a list of allowable actions for another actor interacting with this item.
     * Adds the EatAction to the list of available actions.
     *
     * @param otherActor the actor performing the action
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
