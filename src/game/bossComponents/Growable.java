package game.bossComponents;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for objects that can grow and deal damage.
 *
 * @author Kar May Ong
 */
public interface Growable {

    /**
     * Causes the Growable object to grow, possibly affecting the given actor.
     *
     * @param actor the actor interacting with or affected by the growth
     * @return a string describing the growth effect
     */
    String grow(Actor actor);

    /**
     * Returns the damage points that this Growable object can deal.
     *
     * @return the damage points value
     */
    int getDamagePoint();
}
