package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RotAction;

/**
 * A behaviour that causes an actor to rot away after a specified number of turns
 *
 * @author Kian Lok Chin
 */
public class RottingBehaviour implements Behaviour {
    /**
     * Counter for tracking remaining turns before the actor rots
     */
    private int countdown;

    /**
     * Constructor for the RottingBehaviour
     *
     * @param initialTimer number of turns before the actor rots away
     */
    public RottingBehaviour(int initialTimer) {
        this.countdown = initialTimer;
    }

    /**
     * Decrements the timer each turn; when it expires, returns a RotAction to remove the actor.
     *
     * @param actor The actor that has this behaviour
     * @param map The game map the actor is on
     * @return A RotAction if the countdown has reached zero, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        countdown--;
        if (countdown <= 0) {
            return new RotAction(actor);
        }
        return null;
    }
}

