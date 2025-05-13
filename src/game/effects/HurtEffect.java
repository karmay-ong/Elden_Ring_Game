package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

/**
 * An effect that applies damage to the buyer when a purchase is made
 *
 * @author Kian Lok Chin
 */
public class HurtEffect implements PurchaseEffect {
    /**
     * The amount of damage to inflict on the buyer
     */
    private int damage;

    /**
     * Constructor for the HurtEffect
     *
     * @param damage The amount of damage to inflict on the buyer
     */
    public HurtEffect(int damage) {
        this.damage = damage;
    }

    /**
     * Applies the hurt effect to the buyer
     *
     * @param buyer The actor who made the purchase
     * @param merchant The merchant who sold the item
     * @param map The game map where the transaction occurred
     */
    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.hurt(damage);
    }
}