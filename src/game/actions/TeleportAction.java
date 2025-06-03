package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.TeleportationCircle;

/**
 * Action class that allows an actor to teleport to a specified teleportation circle.
 * The teleportation will only succeed if the destination teleportation circle is unoccupied.
 *
 * If the destination already contains an actor, the teleportation fails.
 *
 * @author YOUSSEF HASSANEIN
 */
public class TeleportAction extends Action {
    /**
     * The teleportation circle where the actor will be teleported.
     */
    private final TeleportationCircle destinationCircle;

    /**
     * Constructor for TeleportAction.
     *
     * @param destinationCircle The target teleportation circle to teleport to.
     */
    public TeleportAction(TeleportationCircle destinationCircle) {
        this.destinationCircle = destinationCircle;
    }

    /**
     * Executes the teleport action. Moves the actor to the target teleportation circle if it's unoccupied.
     *
     * @param actor The actor performing the teleport action.
     * @param map The game map the actor is currently on.
     * @return A string describing the result of the teleport action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location destination = destinationCircle.getLocation();

        // Check if the destination teleportation circle has a valid location
        if (destination == null) {
            return "Cannot teleport! Destination teleportation circle is not properly placed.";
        }

        // Check if destination is occupied
        if (destination.containsAnActor()) {
            return "Failed to teleport as someone is getting in the way!";
        }

        // Perform the teleportation
        map.removeActor(actor);
        destination.map().addActor(actor, destination);
        return actor + " teleports to " + destination.map().toString() + "!";
    }

    /**
     * Returns a description of this action suitable for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the teleport action.
     */
    @Override
    public String menuDescription(Actor actor) {
        Location destination = destinationCircle.getLocation();
        if (destination != null) {
            return actor + " teleports to " + destination.map().toString() + " at " + destination;
        }
        return actor + " teleports to teleportation circle";
    }
}