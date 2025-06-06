package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a teleportation circle ground tile that allows an actor to teleport to other teleportation circles.
 *
 * Actors stepping on this ground can choose to teleport to any of the connected teleportation circles.
 *
 * @author YOUSSEF HASSANEIN
 */
public class TeleportationCircle extends Ground {
    private final List<TeleportationCircle> destinations = new ArrayList<>();
    private Location currentLocation;

    /**
     * Constructs a TeleportationCircle with a specific display character and name.
     */
    public TeleportationCircle() {
        super('A', "Teleportation Circle🌀");
    }

    /**
     * Sets the current location of this teleportation circle.
     * This should be called when the circle is placed on the map.
     *
     * @param location the location where this teleportation circle is placed
     */
    public void setLocation(Location location) {
        this.currentLocation = location;
    }

    /**
     * Gets the current location of this teleportation circle.
     *
     * @return the location where this teleportation circle is placed
     */
    public Location getLocation() {
        return currentLocation;
    }

    /**
     * Adds a destination teleportation circle that this circle can teleport to.
     * This creates a one-way connection.
     *
     * @param destinationCircle the teleportation circle to be added as a destination
     */
    public void addDestination(TeleportationCircle destinationCircle) {
        if (!destinations.contains(destinationCircle)) {
            destinations.add(destinationCircle);
        }
    }

    /**
     * Adds a bidirectional connection between this circle and another circle.
     * Both circles will be able to teleport to each other.
     *
     * @param otherCircle the teleportation circle to create a bidirectional connection with
     */
    public void addBidirectionalConnection(TeleportationCircle otherCircle) {
        // Add the other circle as a destination for this circle
        this.addDestination(otherCircle);
        // Add this circle as a destination for the other circle
        otherCircle.addDestination(this);
    }

    /**
     * Returns a list of allowable actions, including teleportation actions for each connected teleportation circle.
     *
     * @param actor the actor interacting with the ground
     * @param currentLocation the current location of the actor
     * @param direction the direction of the ground relative to the actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location currentLocation, String direction) {
        ActionList actions = super.allowableActions(actor, currentLocation, direction);
        for (TeleportationCircle destinationCircle : destinations) {
            if (destinationCircle.getLocation() != null) {
                actions.add(new TeleportAction(destinationCircle));
            }
        }
        return actions;
    }
}
