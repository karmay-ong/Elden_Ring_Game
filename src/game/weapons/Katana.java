package game.weapons;


import game.effects.HurtEffect;
import game.items.Sellable;
import game.effects.PurchaseEffect;

import java.util.ArrayList;
import java.util.List;

public class Katana extends WeaponItem implements Sellable {
    public static final int DAMAGE = 50;
    public static final int HIT_RATE = 60;

    /**
     * A swift katana for quick strikes.
     *  • damage = 15
     *  • hitRate = 90%
     *  • verb = "cuts"
     */
    public Katana() {
        super("Katana⚔", 'K', DAMAGE, "cuts", HIT_RATE);
    }

    @Override
    public List<PurchaseEffect> soldEffects() {
        List<PurchaseEffect> effects = new ArrayList<>();
        effects.add(new HurtEffect(20));
        return effects;
    }

}
