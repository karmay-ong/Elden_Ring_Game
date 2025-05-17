package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
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
    private Enum<?> capability;

    /**
     * The actor to check adjacency from (can be null)
     */
    private Actor actor;


    /**
     * Creates a condition that checks for a capability in locations adjacent to a specific actor.
     *
     * @param actor      the actor to check adjacency from
     * @param capability the capability to look for
     */
    public AdjacentCapabilityCondition(Actor actor, Enum<?> capability) {
        this.capability = capability;
        this.actor = actor;
    }

    /**
     * Creates a condition that checks for a capability in locations adjacent to the provided location.
     *
     * @param capability the capability to look for
     */
    public AdjacentCapabilityCondition(Enum<?> capability) {
        this.capability = capability;
    }

    /**
     * Checks if any entity in adjacent locations has the specified capability.
     * If an actor was specified in the constructor, checks locations adjacent to that actor.
     * Otherwise, checks locations adjacent to the provided location parameter.
     * Examines the ground, actors, and all items in each adjacent location.
     *
     * @param location the central location to check adjacency from (used only if actor is null)
     * @return true if any adjacent entity has the capability, false otherwise
     */

    public boolean isSatisfied(Location location)
    {
        Location here;
        if(actor == null){
            here = location;
        }
        else{
            GameMap map = location.map();
             here =  map.locationOf(actor);
        }
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
