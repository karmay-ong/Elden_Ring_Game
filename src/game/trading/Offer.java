package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;
import game.items.Sellable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an offer that can be made by a merchant to sell items
 *
 * @author Kian Lok Chin
 */
public class Offer {
    /**
     * The prototype of the item being offered for sale
     */
    private Sellable prototype;

    /**
     * The price of the offer in gold/runes
     */
    private int price;

    /**
     * List of effects to apply when the offer is purchased
     */
    private List<Effect> effects;

    /**
     * Constructor for the Offer
     *
     * @param prototype The item prototype being offered for sale
     * @param price The price of the offer in gold/runes
     * @param effects Additional effects to apply when the offer is purchased
     */
    public Offer(Sellable prototype, int price, List<Effect> effects) {
        this.prototype = prototype;
        this.price     = price;
        this.effects = new ArrayList<>();
        if (effects != null) {
            this.effects.addAll(effects);
        }
        this.effects.addAll(prototype.soldEffects());
    }

    /**
     * Returns the price of the offer
     *
     * @return The price in gold/runes
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the item prototype being offered
     *
     * @return The item prototype as an Item
     */
    public Item getPrototype() {
        return (Item) prototype;
    }

    /**
     * Applies all purchase effects associated with this offer
     *
     * @param buyer The actor who purchased the offer
     * @param map   The game map where the transaction occurred
     */
    public void applyEffect(Actor buyer, GameMap map) {
        for (Effect e : effects) {
            e.apply(buyer,map);
        }
    }

    /**
     * Returns a string representation of the offer with color formatting
     *
     * @return A formatted string describing the offer with its name and price
     */
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
