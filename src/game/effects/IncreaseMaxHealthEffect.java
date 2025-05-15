package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that increases the maximum health capacity of an actor.
 * This can be used in various contexts such as consumable items,
 * level-up rewards, or special game events that permanently enhance
 * an actor's health potential.
 *
 * @author Kian Lok Chin
 */
public class IncreaseMaxHealthEffect implements Effect {
    /**
     * The amount by which to increase the maximum health
     */
    private final int amount;

    /**
     * Creates a new effect that increases maximum health by the specified amount.
     *
     * @param amount the amount by which to increase the maximum health capacity
     */
    public IncreaseMaxHealthEffect(int amount) {
        this.amount = amount;
    }

    /**
     * Applies the maximum health increase to the specified actor.
     * Uses the engine's attribute system to permanently increase the
     * maximum value of the actor's HEALTH attribute.
     *
     * @param actor the actor whose maximum health will be increased
     * @param map the game map (not used in this implementation)
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, amount);
        new Display().println(actor + "'s maximum health is increased by " + amount);
    }
}
