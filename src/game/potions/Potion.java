package game.potions;

import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.actions.BrewPotionAction;
import game.actions.DrinkPotionAction;
import game.actions.ThrowPotionAction;
import game.actors.Status;

/**
 * Abstract class representing potions that can be drunk or thrown.
 */
public abstract class Potion extends Item {

    /**
     * Constructor for Potion
     *
     * @param name The name of the potion
     * @param displayChar The character to display for this potion
     */
    public Potion(String name, char displayChar) {
        super(name, displayChar, true);
    }

    /**
     * Drink the potion, applying its effect to the drinker.
     *
     * @param drinker The actor drinking the potion
     * @return The status effect applied by drinking
     */
    public abstract StatusEffect drink(Actor drinker);

    /**
     * Create a status effect for the given actor.
     * Each potion type will implement this to create the appropriate effect.
     *
     * @param target The actor to create the effect for
     * @return The status effect to apply
     */
    protected abstract StatusEffect createEffect(Actor target);

    /**
     * Get the required ingredients to brew this potion.
     * Each potion type will implement this to specify its recipe.
     *
     * @return A list of required Status capabilities
     */
    public abstract List<Status> getRequiredIngredients();

    /**
     * Create a brewing action for this potion.
     *
     * @param pouch The pouch used for brewing
     * @return The brewing action
     */
    public Action getBrewingAction(Pouch pouch){
        return new BrewPotionAction(pouch,this);
    };

    /**
     * Throw/drop the potion, applying its effect to surrounding actors.
     *
     * @param location The location where the potion is thrown
     * @param map The game map
     */
    public void throwPotion(Location location, GameMap map) {
        applyToSurroundingActors(location, map);
    }

    /**
     * Creates a new instance of this potion type
     * @return A new potion instance
     */
    public abstract Potion createNewInstance();


    /**
     * Apply an effect to all actors in adjacent tiles.
     *
     * @param location The center location
     * @param map The game map
     */
    private void applyToSurroundingActors(Location location, GameMap map) {
        // Apply to actor at this location if any
        if (location.containsAnActor()) {
            Actor target = map.getActorAt(location);
            target.addStatusEffect(createEffect(target));
        }

        // Apply to all actors in adjacent tiles
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = map.getActorAt(destination);
                target.addStatusEffect(createEffect(target));
            }
        }
    }

    /**
     * Returns the list of allowable actions for this item.
     * Adds the DrinkPotionAction and ThrowPotionAction to the default allowable actions.
     *
     * @param owner the actor who owns or is interacting with the item
     * @param map the current game map
     * @return an ActionList containing allowable actions for this item
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        actions.add(new DrinkPotionAction(this));
        actions.add(new ThrowPotionAction(this));
        return actions;
    }
}
