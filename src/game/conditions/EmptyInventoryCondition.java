package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if an actor at a specific location has an empty inventory.
 * This can be used to trigger events, behaviors, or dialogue when an actor has no items.
 *
 * @author Kian Lok Chin
 */
public class EmptyInventoryCondition implements Condition {
    /**
     * The actor whose inventory will be checked
     */
    private Actor actor;

    /**
     * Creates a condition that checks if the specified actor has an empty inventory.
     *
     * @param actor the actor whose inventory will be checked
     */
    public EmptyInventoryCondition(Actor actor){
        this.actor = actor;
    }

    /**
     * Checks if the specified actor has an empty inventory.
     *
     * Note: This implementation ignores the location parameter as it
     * operates on a specific actor provided in the constructor.
     *
     * @param location the location (not used in this implementation)
     * @return true if the actor has no items in their inventory, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        return actor.getItemInventory().isEmpty();
    }
}
