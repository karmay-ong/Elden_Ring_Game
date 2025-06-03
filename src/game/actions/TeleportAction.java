package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.TeleportationCircle;


public class TeleportAction extends Action {

    private final TeleportationCircle destinationCircle;


    public TeleportAction(TeleportationCircle destinationCircle) {
        this.destinationCircle = destinationCircle;
    }


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


}