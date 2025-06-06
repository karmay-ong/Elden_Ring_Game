package game.potions;

import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.actors.Status;
import game.effects.CrazyEffect;

/**
 * A crazy potion that drastically increases max health and damage.
 */
public class CrazyPotion extends Potion {
    private static final int EFFECT_DURATION = 10;

    /**
     * The required ingredients to brew this potion
     */
    private static final List<Status> REQUIRED_INGREDIENTS = Arrays.asList(
            Status.BLESSED, Status.BLESSED, Status.BLESSED,
            Status.DRINKABLE, Status.DRINKABLE
    );

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

    /**
     * Get the required ingredients to brew this potion.
     *
     * @return A list of required Status capabilities
     */
    @Override
    public List<Status> getRequiredIngredients() {
        return REQUIRED_INGREDIENTS;
    }

    /**
     * Creates a new instance of this potion type
     * @return A new potion instance
     */
    @Override
    public Potion createNewInstance() {
        return new CrazyPotion();
    }
}
