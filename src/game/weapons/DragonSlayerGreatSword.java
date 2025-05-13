package game.weapons;


import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.effects.MaxHealthEffect;
import game.items.Sellable;
import game.effects.PurchaseEffect;

import java.util.ArrayList;
import java.util.List;

public class DragonSlayerGreatSword extends WeaponItem implements Sellable {
    public static final int DAMAGE = 70;
    public static final int HIT_RATE = 75;

    /**
     * A massive sword that eats dragons for breakfast.
     *  • damage = 35
     *  • hitRate = 60%
     *  • verb = "cleaves"
     */
    public DragonSlayerGreatSword(){
        super("Dragonslayer Greatsword⚔\uFE0F", 'D', DAMAGE, "cleaves", HIT_RATE);
    }

    @Override
    public List<PurchaseEffect> soldEffects() {
        List<PurchaseEffect> effects = new ArrayList<>();
        effects.add(new MaxHealthEffect(15, ActorAttributeOperations.INCREASE));
        return effects;
    }
}