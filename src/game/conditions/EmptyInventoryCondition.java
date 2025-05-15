package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if an actor at a specific location has an empty inventory.
 * This can be used to trigger events, behaviors, or dialogue when an actor has no items.
 *
 * @author Kian Lok Chin
 */
public class EmptyInventoryCondition implements Condition {

    /**
     * Checks if the actor at the specified location has an empty inventory.
     * Retrieves the actor at the location and examines their item inventory.
     *
     * @param location the location containing the actor to check
     * @return true if the actor has no items in their inventory, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        Actor actor = location.getActor();
        return actor.getItemInventory().isEmpty();
    }
}
