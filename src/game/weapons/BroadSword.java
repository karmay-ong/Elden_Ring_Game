package game.weapons;

import game.effects.HealEffect;
import game.items.Sellable;
import game.effects.PurchaseEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * A heavy, two-handed broadsword weapon that can be sold by merchants
 *
 * @author Kian Lok Chin
 */
public class BroadSword extends WeaponItem implements Sellable {
    /**
     * The damage dealt by the broadsword
     */
    public static final int DAMAGE = 30;

    /**
     * The hit rate percentage of the broadsword
     */
    public static final int HIT_RATE = 50;

    /**
     * Constructor for the BroadSword
     * Creates a broadsword with the following properties:
     *  • damage = 30
     *  • hitRate = 50%
     *  • verb = "slashes"
     */
    public BroadSword() {
        super("Broadsword\uD83D\uDDE1\uFE0F", 'B', DAMAGE, "slashes", HIT_RATE);
    }

    /**
     * Returns the effects that should be applied when this broadsword is sold
     * The broadsword applies a healing effect of 20 HP when purchased
     *
     * @return A list of purchase effects to apply when the broadsword is sold
     */
    @Override
    public List<PurchaseEffect> soldEffects() {
        List<PurchaseEffect> effects = new ArrayList<>();
        effects.add(new HealEffect(20));
        return effects;
    }
}

