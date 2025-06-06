package game.potions;

import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.actors.Status;
import game.effects.PoisonEffect;
import game.actions.BrewPotionAction;

/**
 * A poison potion that damages actors.
 */
public class PoisonPotion extends Potion {
    private static final int DRINK_DAMAGE_AMOUNT = 15;
    private static final int THROW_DAMAGE_AMOUNT = 5;
    private static final int DAMAGE_EFFECT_DURATION = 5;

    /**
     * The required ingredients to brew this potion
     */
    private static final List<Status> REQUIRED_INGREDIENTS = Arrays.asList(
            Status.CURSED, Status.DRINKABLE
    );

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
        return new PoisonPotion();
    }
}
