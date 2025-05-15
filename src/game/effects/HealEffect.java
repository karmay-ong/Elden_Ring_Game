package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that heals an actor by a specified amount.
 * This can be used in various contexts such as when consuming items,
 * completing actions, or as a result of purchases.
 *
 * @author Kian Lok Chin
 */
public class HealEffect implements Effect {
    /**
     * The amount of health to restore to the actor
     */
    private final int healAmount;

    /**
     * Creates a new healing effect with the specified heal amount.
     *
     * @param healAmount the amount of health to restore when applied
     */
    public HealEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    /**
     * Applies the healing effect to the specified actor.
     * Increases the actor's health by the heal amount.
     *
     * @param actor the actor to heal
     * @param map the game map (not used in this implementation)
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        actor.heal(healAmount);
        new Display().println(actor + "'s health is increased by " + healAmount);
    }

}
