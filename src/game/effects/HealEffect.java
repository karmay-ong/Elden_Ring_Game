package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

/**
 * An effect that heals the buyer when a purchase is made
 *
 * @author Kian Lok Chin
 */
public class HealEffect implements PurchaseEffect {
    /**
     * The amount of health to restore to the buyer
     */
    private int healAmount;

    /**
     * Constructor for the HealEffect
     *
     * @param healAmount The amount of health to restore to the buyer
     */
    public HealEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    /**
     * Applies the heal effect to the buyer
     *
     * @param buyer The actor who made the purchase
     * @param merchant The merchant who sold the item
     * @param map The game map where the transaction occurred
     */
    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.heal(healAmount);
    }
}
