package game.potions;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Status;

/**
 * A pouch for brewing potions.
 */
public class Pouch {
    private List<Potion> registeredPotions = new ArrayList<>();

    /**
     * Register a potion with this pouch.
     *
     * @param potion The potion to register
     */
    public void registerPotion(Potion potion) {
        registeredPotions.add(potion);
    }

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

        // Check each registered potion
        for (Potion potion : registeredPotions) {
            if (hasRequiredIngredients(actor, potion.getRequiredIngredients())) {
                actions.add(potion.getBrewingAction(this));
            }
        }

        return actions;
    }
}
