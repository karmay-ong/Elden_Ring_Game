package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

/**
 * An interface for effects that can be applied when a purchase is made
 *
 * @author Kian Lok Chin
 */
public interface PurchaseEffect {
    /**
     * Applies the effect to the buyer after a purchase is completed
     *
     * @param buyer The actor who made the purchase
     * @param merchant The merchant who sold the item
     * @param map The game map where the transaction occurred
     */
    void apply(Actor buyer, Merchant merchant, GameMap map);
}
