package game.utils;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Utility class to check if any adjacent square to a Location contains
 * a ground, actor, or item that has a specific capability.
 */
public class AdjacentCapabilityChecker {

    /**
     * Returns true if any immediate neighbor of the given Location has the
     * specified capability on its ground, actor, or items.
     *
     * @param here       the central Location to check around
     * @param capability the capability to look for (any Enum constant)
     * @return true if found on any adjacent ground, actor, or item; false otherwise
     */
    public static boolean hasAdjacentCapability(Location here, Enum<?> capability) {
        for (Exit exit : here.getExits()) {
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