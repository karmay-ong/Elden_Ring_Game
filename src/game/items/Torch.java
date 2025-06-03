package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.UseTorchAction;
import game.actors.Ability;
import game.actors.Status;
import game.effects.WeatherEffect;
import game.time.EnvironmentalStatusSystem;
import game.effects.Effect;
import game.effects.HealEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Torch item that can be used by an actor to provide warmth in cold weather.
 * The torch has a limited number of uses and can be toggled between lit and unlit states.
 *
 * @author Lim Chi Jian
 */
public class Torch extends Item implements Sellable {

    private int count = 0;
    private boolean isOpen = false;
    private static final int MAX_TORCH_USES = 3;
    private static final int SOLD_HEAL_AMOUNT = 5;

    /**
     * Constructs a new Torch item.
     */
    public Torch() {
        super("Torch 🔥", 'f', true);
    }

    /**
     * Allows the actor to toggle the torch's state (lit/unlit).
     * Torch can be used up to 3 times. If acid rain is active, it cannot be lit,
     * and if it is already lit, the rain extinguishes it.
     *
     * @param actor the actor using the torch
     */
    public void use(Actor actor) {
        Display dp = new Display();
        WeatherEffect weather = EnvironmentalStatusSystem.getInstance().getCurrentWeather();

        if (weather.isAcidRain()) {
            if (!isOpen) {
                dp.println("It's raining! " + actor + " can't light the " + this + ".");
                return;
            } else {
                isOpen = false;
                this.removeCapability(Status.FLAMMABLE);
                actor.removeCapability(Ability.ABLE_WARM);
                dp.println("The rain extinguishes " + actor + "'s " + this + "!");
                return;
            }
        }

        if (count >= MAX_TORCH_USES) {
            actor.removeItemFromInventory(this);
            dp.println(actor + " has extinguished the " + this + ".");
            return;
        }

        if (!isOpen) {
            isOpen = true;
            count++;
            this.addCapability(Status.FLAMMABLE);
            actor.addCapability(Ability.ABLE_WARM);
        } else {
            isOpen = false;
            this.removeCapability(Status.FLAMMABLE);
            actor.removeCapability(Ability.ABLE_WARM);
        }
    }

    /**
     * Checks whether the torch is currently lit.
     *
     * @return true if the torch has the {@code FLAMMABLE} status, false otherwise
     */
    public boolean getTorchLit() {
        return hasCapability(Status.FLAMMABLE);
    }

    /**
     * Returns a list of effects applied when the torch is sold.
     * Grants a small healing effect to the player.
     *
     * @return list of effects, including a {@code HealEffect} of 5 HP
     */
    @Override
    public List<Effect> soldEffects() {
        List<Effect> effects = new ArrayList<>();
        effects.add(new HealEffect(SOLD_HEAL_AMOUNT));
        return effects;
    }

    /**
     * Provides the list of allowable actions for the actor regarding this torch.
     * Only includes the {@code UseTorchAction}.
     *
     * @param actor the actor interacting with the torch
     * @param map the game map context
     * @return an {@code ActionList} containing a {@code UseTorchAction}
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new UseTorchAction(this));
        return actions;
    }
}


