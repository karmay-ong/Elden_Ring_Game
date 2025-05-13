package game.weapons;

import game.effects.HealEffect;
import game.items.Sellable;
import game.effects.PurchaseEffect;

import java.util.ArrayList;
import java.util.List;

public class BroadSword extends WeaponItem implements Sellable {
    public static final int DAMAGE = 30;
    public static final int HIT_RATE = 50;

    /**
     * A heavy, two‐handed broadsword.
     *  • damage = 20
     *  • hitRate = 75%
     *  • verb = "slashes"
     */
    public BroadSword() {
        super("Broadsword\uD83D\uDDE1\uFE0F", 'B', DAMAGE, "slashes", HIT_RATE);
    }
    @Override
    public List<PurchaseEffect> soldEffects() {
        List<PurchaseEffect> effects = new ArrayList<>();
        effects.add(new HealEffect(20));
        return effects;
    }
   
}
