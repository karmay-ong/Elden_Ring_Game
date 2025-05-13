package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

/**
 * An effect that modifies the maximum stamina of the buyer when a purchase is made
 *
 * @author Kian Lok Chin
 */
public class MaxStaminaEffect implements PurchaseEffect {
    /**
     * The amount by which to modify the maximum stamina
     */
    private int amount;

    /**
     * The operation to perform on the maximum stamina (increase or decrease)
     */
    private ActorAttributeOperations operation;

    /**
     * Constructor for the MaxStaminaEffect
     *
     * @param amount The amount by which to modify the maximum stamina
     * @param operation The operation to perform (increase or decrease)
     */
    public MaxStaminaEffect(int amount, ActorAttributeOperations operation) {
        this.amount = amount;
        this.operation = operation;
    }

    /**
     * Applies the maximum stamina modification effect to the buyer
     *
     * @param buyer The actor who made the purchase
     * @param merchant The merchant who sold the item
     * @param map The game map where the transaction occurred
     */
    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, operation, amount);
    }
}
