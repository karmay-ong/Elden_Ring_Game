package game.potions;

import java.util.*;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.BrewCrazyPotionAction;
import game.actions.BrewHealingPotionAction;
import game.actions.BrewPoisonPotionAction;
import game.actors.Status;
import edu.monash.fit2099.engine.actions.Action;

/**
 * A pouch that can brew potions when given the right ingredients.
 *
 * @author Kian Lok Chin
 */
public class Pouch {

    /**
     * Check if the actor has all required ingredients for a potion with specific requirements.
     *
     * @param actor The actor to check
     * @param requiredCapabilities The list of capabilities needed
     * @return true if the actor has all ingredients, false otherwise
     */
    private boolean hasRequiredIngredients(Actor actor, List<Status> requiredCapabilities) {
        // Get a copy of the required capabilities
        List<Status> remainingRequirements = new ArrayList<>(requiredCapabilities);

        // Check if each item in inventory can satisfy one requirement
        for (Item item : actor.getItemInventory()) {
            for (int i = 0; i < remainingRequirements.size(); i++) {
                Status requiredCapability = remainingRequirements.get(i);
                if (item.hasCapability(requiredCapability)) {
                    remainingRequirements.remove(i);
                    break; // Each item can only satisfy one requirement
                }
            }
        }

        // If all requirements are satisfied, the list will be empty
        return remainingRequirements.isEmpty();
    }

    /**
     * Consume ingredients from the actor's inventory based on required capabilities.
     *
     * @param actor The actor brewing the potion
     * @param requiredCapabilities The capabilities needed for the recipe
     * @return true if ingredients were successfully consumed
     */
    public boolean consumeIngredients(Actor actor, List<Status> requiredCapabilities) {
        // Get a copy of the required capabilities
        List<Status> remainingRequirements = new ArrayList<>(requiredCapabilities);
        List<Item> itemsToRemove = new ArrayList<>();

        // Match items to requirements
        for (Item item : actor.getItemInventory()) {
            for (int i = 0; i < remainingRequirements.size(); i++) {
                Status requiredCapability = remainingRequirements.get(i);
                if (item.hasCapability(requiredCapability)) {
                    itemsToRemove.add(item);
                    remainingRequirements.remove(i);
                    break; // Each item can only satisfy one requirement
                }
            }

            // If all requirements are satisfied, stop looking
            if (remainingRequirements.isEmpty()) {
                break;
            }
        }

        // Check if all requirements were met
        if (!remainingRequirements.isEmpty()) {
            return false;
        }

        // Remove the used items
        for (Item item : itemsToRemove) {
            actor.removeItemFromInventory(item);
        }

        return true;
    }

    /**
     * Get a list of brewing actions available to the actor based on their inventory.
     *
     * @param actor The actor to check
     * @return A list of brewing actions
     */
    public List<Action> getBrewingActions(Actor actor) {
        List<Action> actions = new ArrayList<>();

        // Check for Poison Potion
        List<Status> poisonReq = Arrays.asList(Status.CURSED, Status.DRINKABLE);
        if (hasRequiredIngredients(actor, poisonReq)) {
            actions.add(new BrewPoisonPotionAction(this, poisonReq));
        }

        // Check for Healing Potion
        List<Status> healingReq = Arrays.asList(Status.BLESSED, Status.DRINKABLE);
        if (hasRequiredIngredients(actor, healingReq)) {
            actions.add(new BrewHealingPotionAction(this, healingReq));
        }

        // Check for Crazy Potion
        List<Status> crazyReq = Arrays.asList(Status.BLESSED, Status.BLESSED, Status.BLESSED,
                Status.DRINKABLE, Status.DRINKABLE);
        if (hasRequiredIngredients(actor, crazyReq)) {
            actions.add(new BrewCrazyPotionAction(this, crazyReq));
        }

        return actions;
    }
}
