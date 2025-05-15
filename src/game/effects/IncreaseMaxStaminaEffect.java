package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that increases the maximum stamina capacity of an actor.
 * This can be used in various contexts such as consumable items,
 * level-up rewards, or special game events that permanently enhance
 * an actor's endurance potential.
 *
 * @author Kian Lok Chin
 */
public class IncreaseMaxStaminaEffect implements Effect {
    /**
     * The amount by which to increase the maximum stamina
     */
    private final int amount;

    /**
     * Creates a new effect that increases maximum stamina by the specified amount.
     *
     * @param amount the amount by which to increase the maximum stamina capacity
     */
    public IncreaseMaxStaminaEffect(int amount) {
        this.amount = amount;
    }

    /**
     * Applies the maximum stamina increase to the specified actor.
     * Uses the engine's attribute system to permanently increase the
     * maximum value of the actor's STAMINA attribute.
     *
     * @param actor the actor whose maximum stamina will be increased
     * @param map the game map (not used in this implementation)
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, amount);
        new Display().println(actor + "'s maximum stamina is increased by " + amount);
    }
}
