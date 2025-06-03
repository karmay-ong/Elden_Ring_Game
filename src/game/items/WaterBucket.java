package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CreatePondAction;
import game.actors.Status;

/**
 * A bucket of water collected from a pond.
 *
 * @author Kian Lok Chin
 */
public class WaterBucket extends Item {

    /**
     * Constructor for WaterBucket
     */
    public WaterBucket() {
        super("Bucket of Water", 'W', true);
        addCapability(Status.DRINKABLE);
    }

    /**
     * Returns the list of allowable actions for this item.
     * Adds the CreatePondAction to the default allowable actions.
     *
     * @param owner the actor who owns or is interacting with the item
     * @param map the current game map
     * @return an ActionList containing allowable actions for this item
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        actions.add(new CreatePondAction(this));
        return actions;
    }
}
