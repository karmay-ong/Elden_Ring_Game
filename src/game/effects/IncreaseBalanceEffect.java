package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An effect that increases the balance of an actor.
 * This can be used in various contexts such as consumable items,
 * level-up rewards, or special game events that permanently enhance
 * an actor's balance.
 *
 * <p>This effect is typically used to increase the actor's available balance or currency, which could
 * be spent on purchases or other actions in the game.</p>
 *
 * @author Kian Lok Chin
 */
public class IncreaseBalanceEffect implements Effect {

    /**
     * The amount by which to increase the balance.
     */
    private final int amount;

    /**
     * Creates a new effect that increases the balance by the specified amount.
     *
     * @param amount the amount by which to increase the balance
     */
    public IncreaseBalanceEffect(int amount) {
        this.amount = amount;
    }

    /**
     * Applies the balance increase to the specified actor.
     * This method uses the actor's `addBalance` method to permanently increase the
     * actor's available balance by the specified amount.
     *
     * @param actor the actor whose balance will be increased
     * @param map the game map (not used in this implementation)
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        actor.addBalance(amount);
        new Display().println(actor + "'s balance is increased by " + amount);
    }
}
