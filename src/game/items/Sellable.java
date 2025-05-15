package game.items;

import game.effects.Effect;

import java.util.List;

/**
 * An interface for items that can be sold by merchants
 *
 * @author Kian Lok Chin
 */
public interface Sellable {
    /**
     * Returns a list of effects that should be applied when this item is sold
     *
     * @return A list of purchase effects to apply when the item is sold
     */
    List<Effect> soldEffects();
}
