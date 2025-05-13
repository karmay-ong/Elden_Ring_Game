package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.trading.Offer;


/**
 * Action class for purchasing items from merchants
 *
 * @author Kian Lok Chin
 */
public class PurchaseAction extends Action {
    /**
     * The merchant selling the item
     */
    private Merchant merchant;

    /**
     * The offer being purchased
     */
    private Offer offer;

    /**
     * Constructor for the PurchaseAction
     *
     * @param merchant The merchant selling the item
     * @param offer The offer being purchased
     */
    public PurchaseAction(Merchant merchant, Offer offer) {
        this.merchant = merchant;
        this.offer    = offer;
    }

    /**
     * Executes the purchase action if the farmer has enough Runes.
     * Deducts the price from the farmer's balance and adds the item to their inventory.
     *
     * @param farmer The actor performing the purchase
     * @param map The game map the actor is on
     * @return A string describing the result of the purchase
     */
    @Override
    public String execute(Actor farmer, GameMap map) {
        if (farmer.getBalance() < offer.getPrice()) {
            return farmer + " doesn't have enough Runes to buy " + offer + ".";
        }
        farmer.deductBalance(offer.getPrice());
        Item weapon = offer.getPrototype();
        farmer.addItemToInventory(weapon);
        offer.applyEffect(farmer, merchant, map);
        return farmer + " bought a " + offer + " from " + merchant + ".";
    }

    /**
     * Returns a description of this action suitable for the menu
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + offer + " from " + merchant;
    }
}

