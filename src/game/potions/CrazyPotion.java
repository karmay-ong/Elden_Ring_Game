package game.potions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.CrazyEffect;

/**
 * A crazy potion that drastically increases max health and damage.
 *
 * @author Kian Lok Chin
 */
public class CrazyPotion extends Potion {
    private static final int EFFECT_DURATION = 10;

    /**
     * Constructor for CrazyPotion
     */
    public CrazyPotion() {
        super("CRAZY Potion\uD83E\uDDEA❤\uFE0F", 'C');
    }

    /**
     * Drink the crazy potion, doubling max health and multiplying damage by 10.
     *
     * @param drinker The actor drinking the potion
     * @return The crazy effect
     */
    @Override
    public StatusEffect drink(Actor drinker) {
        return createEffect(drinker);
    }

    /**
     * Create a crazy effect for the target actor.
     *
     * @param target The actor to create the effect for
     * @return The status effect to apply
     */
    @Override
    protected StatusEffect createEffect(Actor target) {
        return new CrazyEffect(EFFECT_DURATION); // Lasts for 10 ticks
    }
}
