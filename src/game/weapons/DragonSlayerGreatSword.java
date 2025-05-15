package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.effects.Effect;
import game.effects.IncreaseMaxHealthEffect;
import game.items.Sellable;

import java.util.ArrayList;
import java.util.List;

/**
 * A massive sword specialized for slaying dragons
 *
 * @author Kian Lok Chin
 */
public class DragonSlayerGreatSword extends WeaponItem implements Sellable {
    /**
     * The damage dealt by the dragonslayergreatsword
     */
    public static final int DAMAGE = 70;

    /**
     * The hit rate percentage of the dragonslayergreatsword
     */
    public static final int HIT_RATE = 75;

    /**
     * Constructor for the DragonSlayerGreatSword
     * Creates a dragonslayergreatsword with the following properties:
     *  • damage = 70
     *  • hitRate = 75%
     *  • verb = "cleaves"
     */
    public DragonSlayerGreatSword(){
        super("DragonslayerGreatsword⚔\uFE0F", 'D', DAMAGE, "cleaves", HIT_RATE);
    }

    /**
     * Returns the effects that should be applied when this dragonslayergreatsword is sold
     * The dragonslayergreatsword increases the buyer's maximum health by 15 when purchased
     *
     * @return A list of purchase effects to apply when the dragonslayergreatsword is sold
     */
    @Override
    public List<Effect> soldEffects() {
        List<Effect> effects = new ArrayList<>();
        effects.add(new IncreaseMaxHealthEffect(15));
        return effects;
    }
}
