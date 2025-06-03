package game.grounds;


import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;


import java.util.ArrayList;
import java.util.List;


public class TeleportationCircle extends Ground {
    private final List<TeleportationCircle> destinations = new ArrayList<>();
    private Location currentLocation;

    public TeleportationCircle() {
        super('A', "Teleportation Circle🌀");
    }

    public void setLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public void addDestination(TeleportationCircle destinationCircle) {
        if (!destinations.contains(destinationCircle)) {
            destinations.add(destinationCircle);
        }
    }

    public void addBidirectionalConnection(TeleportationCircle otherCircle) {
        this.addDestination(otherCircle);
        otherCircle.addDestination(this);
    }

    public void removeDestination(TeleportationCircle destinationCircle) {
        destinations.remove(destinationCircle);
    }

    public void removeBidirectionalConnection(TeleportationCircle otherCircle) {
        this.removeDestination(otherCircle);
        otherCircle.removeDestination(this);
    }

    public List<TeleportationCircle> getDestinations() {
        return new ArrayList<>(destinations);
    }
}
