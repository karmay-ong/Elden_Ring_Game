package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RotAction;

public class RottingBehaviour implements Behaviour {
    private int countdown;

    /**
     * @param initialTimer number of turns before the actor rots away
     */
    public RottingBehaviour(int initialTimer) {
        this.countdown = initialTimer;
    }

    /**
     * Decrements the timer each turn; when it expires, returns a RotAction to remove the actor.
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
