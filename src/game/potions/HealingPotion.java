package game.potions;

import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.actors.Status;
import game.effects.HealingEffect;
import game.actions.BrewPotionAction;

/**
 * A healing potion that restores health.
 */
public class HealingPotion extends Potion {
    private static final int DRINK_HEAL_AMOUNT = 20;
    private static final int THROW_HEAL_AMOUNT = 10;
    private static final int HEAL_EFFECT_DURATION = 5;

    /**
     * The required ingredients to brew this potion
     */
    private static final List<Status> REQUIRED_INGREDIENTS = Arrays.asList(
            Status.BLESSED, Status.DRINKABLE
    );

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
        return new HealingPotion();
    }
}
