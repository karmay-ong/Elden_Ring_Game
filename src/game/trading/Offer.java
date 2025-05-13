// game/trading/Offer.java
package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.effects.PurchaseEffect;
import game.items.Sellable;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private Sellable prototype;
    private int price;
    private List<PurchaseEffect> effects;

    public Offer(Sellable prototype, int price, List<PurchaseEffect> effects) {
        this.prototype = prototype;
        this.price     = price;
        this.effects = new ArrayList<>();
        if (effects != null) {
            this.effects.addAll(effects);
        }
        this.effects.addAll(prototype.soldEffects());
    }

    public int getPrice() {
        return price;
    }

    public Item getPrototype() {
        return (Item) prototype;
    }

    public void applyEffect(Actor buyer, Merchant merchant, GameMap map) {
        for (PurchaseEffect e : effects) {
            e.apply(buyer, merchant ,map);
        }
    }

    @Override
    public String toString() {
        // Define ANSI color codes locally within the method
        String ANSI_RESET  = "\u001B[0m";
        String ANSI_BLUE   = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_GREEN  = "\u001B[32m";

        // 🛡️ Offer header in blue, weapon name in yellow, price in green with coin emoji
        return String.format(
                "%s🛡️  Offer:%s %s“%s”%s  %s💰 %d Gold%s",
                ANSI_BLUE,                 // start blue
                ANSI_RESET,                // reset
                ANSI_YELLOW,               // start yellow
                prototype,
                ANSI_RESET,                // reset
                ANSI_GREEN,                // start green
                price,                     // weapon price
                ANSI_RESET                 // final reset
        );
    }

}
