package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.UseUmbrellaAction;
import game.actors.Ability;
import game.effects.Effect;
import game.effects.HealEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an umbrella item that actors can open or close.
 *
 * When opened, it grants protection against acid rain and sun by adding
 * the capabilities BLOCK_ACID_RAIN and BLOCK_SUN to the actor.
 * The umbrella can be opened a limited number of times before it breaks and is removed.
 *
 * When sold, the umbrella grants a small healing effect.
 *
 * Author: Lim Chi Jian
 */
public class Umbrella extends Item implements Sellable {

    private boolean isOpen = false;
    private int openCount = 0;
    private static final int HEAL_AMOUNT = 5;

    /**
     * Constructs an Umbrella item with a display character and name.
     */
    public Umbrella() {
        super("Umbrella ☔️", 'u', true);
    }

    /**
     * Returns a list of effects applied when this umbrella is sold.
     * Grants a small healing effect of 5 HP.
     *
     * @return a list of Effects applied on sale
     */
    @Override
    public List<Effect> soldEffects() {
        List<Effect> effects = new ArrayList<>();
        effects.add(new HealEffect(HEAL_AMOUNT));
        return effects;
    }

    /**
     * Returns whether the umbrella is currently open.
     *
     * @return true if open, false if closed
     */
    public boolean getUmbrellaIsOpen() {
        return isOpen;
    }

    /**
     * Toggles the umbrella's open/closed state.
     *
     * If currently closed, opens the umbrella, adds protection capabilities,
     * and increments the open count. If open count exceeds 3, the umbrella breaks.
     *
     * If currently open, closes the umbrella and removes protection capabilities.
     *
     * @param actor the actor using the umbrella
     */
    public void use(Actor actor) {
        Display display = new Display();

        if (openCount >= 3) {
            actor.removeItemFromInventory(this);
            display.println(actor + "'s Umbrella broke and is discarded.");
            return;
        }

        if (!isOpen) {
            isOpen = true;
            openCount++;
            actor.addCapability(Ability.BLOCK_ACID_RAIN);
            actor.addCapability(Ability.BLOCK_SUN);
        } else {
            isOpen = false;
            actor.removeCapability(Ability.BLOCK_ACID_RAIN);
            actor.removeCapability(Ability.BLOCK_SUN);
        }
    }

    /**
     * Returns the allowable actions on the umbrella, allowing the actor to toggle it.
     *
     * @param actor the actor interacting with the umbrella
     * @param map the current game map
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new edu.monash.fit2099.engine.actions.ActionList();
        actions.add(new UseUmbrellaAction(this));
        return actions;
    }

    /**
     * Returns the umbrella's name for display.
     *
     * @return the string "Umbrella ☔️"
     */
    @Override
    public String toString() {
        return "Umbrella ☔️";
    }
}
