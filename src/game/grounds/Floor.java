package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 * The floor is represented by '_' on the game map and is walkable by actors.
 * It provides a basic ground type that is typically used for indoor environments.
 *
 * @author Riordan D. Alfredo
 */
public class Floor extends Ground {

    /**
     * Constructor for the Floor class.
     * Initializes the floor with a display character '_' and the name "Floor".
     */
    public Floor() {
        super('_', "Floor");
    }
}
