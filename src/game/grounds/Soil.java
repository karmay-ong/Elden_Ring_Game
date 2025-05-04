package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * A class representing the soil in the valley.
 * The soil is represented by '.' on the game map and is capable of having plants grown on it.
 * It has the PLANTABLE capability, allowing seeds to be planted on this ground type.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 */
public class Soil extends Ground {

    /**
     * Constructor for the Soil class.
     * Initializes the soil with a display character '.' and adds the PLANTABLE capability
     * which allows it to be used for planting seeds.
     */
    public Soil() {
        super('.', "Soil");
        this.addCapability(Status.PLANTABLE);
    }
}
