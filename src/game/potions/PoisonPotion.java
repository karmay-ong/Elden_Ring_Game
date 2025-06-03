package game.potions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.PoisonEffect;

/**
 * A poison potion that damages actors.
 *
 * @author Kian Lok Chin
 */
public class PoisonPotion extends Potion {

    private static final int DRINK_DAMAGE_AMOUNT = 15;
    private static final int THROW_DAMAGE_AMOUNT = 5;
    private static final int DAMAGE_EFFECT_DURATION = 5;

    /**
     * Constructor for PoisonPotion
     */
    public PoisonPotion() {
        super("Poison Potion\uD83E\uDDEA\uD83D\uDDA4", 'P');
    }

    /**
     * Drink the poison potion, applying a strong poison effect.
     *
     * @param drinker The actor drinking the potion
     * @return The poison effect
     */
    @Override
    public StatusEffect drink(Actor drinker) {
        return new PoisonEffect(DRINK_DAMAGE_AMOUNT, DAMAGE_EFFECT_DURATION);
    }

    /**
     * Create a poison effect for the target actor when thrown.
     *
     * @param target The actor to create the effect for
     * @return The status effect to apply
     */
    @Override
    protected StatusEffect createEffect(Actor target) {
        return new PoisonEffect(THROW_DAMAGE_AMOUNT, DAMAGE_EFFECT_DURATION);
    }
}
