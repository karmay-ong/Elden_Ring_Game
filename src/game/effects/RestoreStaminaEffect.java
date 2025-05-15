package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that restores stamina to an actor.
 * Can be used as both a PurchaseEffect and a ConsumptionEffect.
 * When applied, it increases the actor's stamina by a specified amount.
 * This effect can be used in various contexts, such as when the actor consumes an item or purchases an upgrade.
 *
 * @author Kian Lok Chin
 */
public class RestoreStaminaEffect implements Effect {

    /**
     * The amount of stamina to restore to the actor.
     */
    private final int staminaAmount;

    /**
     * Constructor for RestoreStaminaEffect.
     * This creates an effect that will restore a specific amount of stamina to an actor.
     *
     * @param staminaAmount The amount of stamina to restore to the actor.
     */
    public RestoreStaminaEffect(int staminaAmount) {
        this.staminaAmount = staminaAmount;
    }

    /**
     * Applies this effect to the given actor, restoring the specified amount of stamina.
     * This method modifies the actor's stamina attribute using the INCREASE operation.
     *
     * @param actor The actor to apply the effect to
     * @param map The current game map
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaAmount);
        new Display().println(actor + "'s stamina is increased by " + staminaAmount);
    }

}
