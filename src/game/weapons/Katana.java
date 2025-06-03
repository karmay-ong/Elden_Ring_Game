package game.weapons;

import game.effects.Effect;
import game.effects.HurtEffect;
import game.items.Sellable;

import java.util.ArrayList;
import java.util.List;

/**
 * A swift Japanese sword known for its cutting power
 *
 * @author Kian Lok Chin
 */
public class Katana extends WeaponItem implements Sellable {
    /**
     * The damage dealt by the katana
     */
    public static final int DAMAGE = 50;

    /**
     * The hit rate percentage of the katana
     */
    public static final int HIT_RATE = 60;

    public static final int SOLD_DAMAGE = 25;

    /**
     * Constructor for the Katana
     * Creates a katana with the following properties:
     *  • damage = 50
     *  • hitRate = 60%
     *  • verb = "cuts"
     */
    public Katana() {
        super("Katana⚔", 'K', DAMAGE, "cuts", HIT_RATE);
    }

    /**
     * Returns the effects that should be applied when this katana is sold
     * The katana deals 20 damage to the buyer when purchased
     *
     * @return A list of purchase effects to apply when the katana is sold
     */
    @Override
    public List<Effect> soldEffects() {
        List<Effect> effects = new ArrayList<>();
        effects.add(new HurtEffect(SOLD_DAMAGE));
        return effects;
    }
}

