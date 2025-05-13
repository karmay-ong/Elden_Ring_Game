package game.actors;

import game.trading.Offer;

/**
 * An interface for actors that can act as merchants in the game
 *
 * @author Kian Lok Chin
 */
public interface Merchant {
    /**
     * Adds a new offer to the merchant's available offers
     *
     * @param offer The offer to be added to the merchant
     */
    void addOffer(Offer offer);
}
