package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.creatures.Creature;
import game.conditions.Condition;
import game.effects.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base for all eggs that can hatch into different creatures
 * under customizable conditions. Eggs are EATABLE and pause hatching
 * while carried.
 * @author YOUSSEF HASSANEIN
 */
public class Egg extends Item implements Eatable {
    private List<Effect> consumptionEffects;
    private Condition condition;
    private Creature hatchling;

    /**
     * Constructor for Egg with condition, hatchling, and consumption effects.
     *
     * @param name the name of the egg
     * @param condition the condition that must be satisfied for the egg to hatch
     * @param hatchling the creature that will hatch from this egg
     * @param consumptionEffects list of effects applied when the egg is consumed
     */
    public Egg(String name, Condition condition, Creature hatchling, List<Effect> consumptionEffects) {
        super(name, '0', true);
        this.condition = condition;
        this.hatchling = hatchling;
        this.consumptionEffects = new ArrayList<>();
        if (consumptionEffects != null) {
            this.consumptionEffects.addAll(consumptionEffects);
        }
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        // Apply all consumption effects
        for (Effect effect : consumptionEffects) {
            effect.apply(actor, map);
        }
    }

    /**
     * Called each turn when on the ground: attempt to hatch
     */
    @Override
    public void tick(Location currentLocation) {
        if (shouldHatch(currentLocation)) {
            hatch(currentLocation);
        }
    }

    /**
     * Remove this egg and spawn the creature
     */
    private void hatch(Location currentLocation) {
        if (!currentLocation.containsAnActor()) {
            currentLocation.addActor(hatchling);
            currentLocation.removeItem(this);
        }
    }

    /**
     * Checks if the egg should hatch based on the provided condition.
     *
     * @param currentLocation current ground Location
     * @return true if the egg should hatch
     */
    private boolean shouldHatch(Location currentLocation) {
        return condition.isSatisfied(currentLocation);
    }


    @Override
    public ActionList allowableActions(Actor otherActor, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new EatAction( this));
        return actions;
    }
}
