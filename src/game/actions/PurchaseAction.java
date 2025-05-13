package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.trading.Offer;


public class PurchaseAction extends Action {
    private Merchant merchant;
    private Offer offer;

    public PurchaseAction(Merchant merchant, Offer offer) {
        this.merchant = merchant;
        this.offer    = offer;
    }

    @Override
    public String execute(Actor farmer, GameMap map) {
        if (farmer.getBalance() < offer.getPrice()) {
            return farmer + " doesn’t have enough Runes to buy " + offer + ".";
        }
        farmer.deductBalance(offer.getPrice());
        Item weapon = offer.getPrototype();
        farmer.addItemToInventory(weapon);
        offer.applyEffect(farmer, merchant, map);
        return farmer + " bought a " + offer + " from " + merchant + ".";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + offer + " from " + merchant;
    }
}
