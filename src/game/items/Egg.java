package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.creatures.Creature;


/**
 * Abstract base for all eggs that can hatch into different creatures
 * under customizable conditions.  Eggs are EATABLE and pause hatching
 * while carried.
 * @author YOUSSEF HASSANEIN
 */
public abstract class Egg extends Item implements Eatable {

    public Egg(String name) {
        super(name, '0', true);
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Called each turn when on the ground: reset pickedUp and attempt hatch
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
    protected void hatch(Location currentLocation) {
        if (!currentLocation.containsAnActor()) {
            Creature hatchCreature = createHatchling();
            currentLocation.addActor(hatchCreature);
            currentLocation.removeItem(this);
        }
    }

    protected abstract Creature createHatchling();

    /**
     * Subclasses define their own hatching condition.
     * @param currentLocation current ground Location
     * @return true to hatch
     */
    protected abstract boolean shouldHatch(Location currentLocation);

    @Override
    public ActionList allowableActions(Actor otherActor, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new EatAction(otherActor, this));
        return actions;
    }
}