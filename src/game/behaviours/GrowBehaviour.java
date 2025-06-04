package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GrowAction;
import game.bossComponents.Growable;

/**
 * Behaviour that enables a Growable object to perform a grow action.
 * Always returns a GrowAction for the associated Growable.
 *
 * @author Kar May Ong
 */
public class GrowBehaviour implements Behaviour {
    /**
     * The Growable object this behaviour controls
     */
    private final Growable growable;

    /**
     * Constructor for GrowBehaviour.
     *
     * @param growable the Growable object this behaviour controls
     */
    public GrowBehaviour(Growable growable) {
        this.growable = growable;
    }

    /**
     * Returns a GrowAction for the Growable.
     *
     * @param actor the actor performing the behaviour (usually the growable object itself)
     * @param map the game map
     * @return a GrowAction targeting the growable
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new GrowAction(growable);
    }
}