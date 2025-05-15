package game.conditions;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that is satisfied when there is an entity with a specific capability
 * in an adjacent location (not at the current location).
 * Checks ground, actors, and items in all adjacent locations for the specified capability.
 *
 * @author Kian Lok Chin
 */
public class AdjacentCapabilityCondition implements Condition{
    /**
     * The capability to check for in adjacent locations
     *
     */
    private final Enum<?> capability;

    /**
     * Creates a new condition that checks for adjacent entities with a specific capability.
     *
     * @param capability the capability to check for in ground, actors, or items
     */
    public AdjacentCapabilityCondition(Enum<?> capability) {
        this.capability = capability;
    }

    /**
     * Checks if any entity in adjacent locations has the specified capability.
     * Examines the ground, actors, and all items in each adjacent location.
     *
     * @param location the central location to check adjacency from
     * @return true if any adjacent entity has the capability, false otherwise
     */
    public boolean isSatisfied(Location location)
    {
        for (Exit exit : location.getExits()) {
            Location dest = exit.getDestination();
            // ground
            if (dest.getGround().hasCapability(capability)) {
                return true;
            }
            // actor (any actor is a GameEntity)
            if (dest.containsAnActor() && dest.getActor().hasCapability(capability)) {
                return true;
            }
            // items
            for (Item item : dest.getItems()) {
                if (item.hasCapability(capability)) {
                    return true;
                }
            }
        }
        return false;
    }
}
