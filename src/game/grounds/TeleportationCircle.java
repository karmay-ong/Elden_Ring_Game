package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;

/**
 * A special ground that allows actors to teleport between different maps.
 */
public class TeleportationCircle extends Ground {
    private Location destination;

    /**
     * Constructor for TeleportationCircle
     */
    public TeleportationCircle() {
        super('A', "Teleportation Circle");
    }

    /**
     * Sets the destination for this teleportation circle
     *
     * @param destination the location to teleport to
     */
    public void setDestination(Location destination) {
        this.destination = destination;
    }

    /**
     * Returns a list of allowable actions for this ground, including teleportation if a destination is set.
     * The actor will be teleported to an available adjacent tile next to the destination circle.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (destination != null) {
            // Look for an available adjacent tile near the destination circle
            for (Exit exit : destination.getExits()) {
                Location targetLocation = exit.getDestination();
                if (targetLocation.canActorEnter(actor)) {
                    actions.add(new MoveActorAction(targetLocation, "to " + destination.map().toString()));
                    return actions; // Return as soon as we find a valid spot
                }
            }
        }
        return actions;
    }
}