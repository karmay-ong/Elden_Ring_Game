package game.potions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.HealingEffect;

/**
 * A healing potion that restores health.
 *
 * @author Kian Lok Chin
 */
public class HealingPotion extends Potion {

    private static final int DRINK_HEAL_AMOUNT = 20;
    private static final int THROW_HEAL_AMOUNT = 10;
    private static final int HEAL_EFFECT_DURATION = 5;

    /**
     * Constructor for HealingPotion
     */
    public HealingPotion() {
        super("Healing Potion\uD83E\uDDEA\uD83D\uDC9C", 'H');
    }

    /**
     * Drink the healing potion, applying a strong healing effect.
     *
     * @param drinker The actor drinking the potion
     * @return The healing effect
     */
    @Override
    public StatusEffect drink(Actor drinker) {
        return new HealingEffect(DRINK_HEAL_AMOUNT, HEAL_EFFECT_DURATION);
    }

    /**
     * Create a healing effect for the target actor when thrown.
     *
     * @param target The actor to create the effect for
     * @return The status effect to apply
     */
    @Override
    protected StatusEffect createEffect(Actor target) {
        return new HealingEffect(THROW_HEAL_AMOUNT, HEAL_EFFECT_DURATION);
    }
}
