package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that applies damage to an actor.
 * This can be used in various contexts such as when consuming items,
 * activating traps, or as a consequence of certain actions.
 *
 * @author Kian Lok Chin
 */
public class HurtEffect implements Effect {
    /**
     * The amount of damage to inflict on the actor
     */
    private int damage;

    /**
     * Creates a new damage effect with the specified damage amount.
     *
     * @param damage the amount of damage to inflict when applied
     */
    public HurtEffect(int damage) {
        this.damage = damage;
    }

    /**
     * Applies the damage effect to the specified actor.
     * Reduces the actor's health by the damage amount.
     *
     * @param target the actor to damage
     * @param map the game map (not used in this implementation)
     */
    public void apply(Actor target, GameMap map) {
        target.hurt(damage);
        new Display().println(target + "'s health is decreased by " + damage);
    }
}
