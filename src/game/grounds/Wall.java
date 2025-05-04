package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor.
 * The wall is represented by '#' on the game map and serves as a barrier
 * that blocks movement for all actors in the game.
 *
 * @author Riordan D. Alfredo
 */
public class Wall extends Ground {

    /**
     * Constructor for the Wall class.
     * Initializes the wall with a display character '#' and the name "Wall".
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * Determines whether an actor can enter this ground.
     * Always returns false for walls, as they are impassable barriers.
     *
     * @param actor the actor attempting to enter
     * @return false, indicating that no actor can enter a wall
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
