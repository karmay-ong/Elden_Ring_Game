package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.potions.Pouch;
import game.potions.Potion;

import java.util.HashMap;
import java.util.Map;

/**
 * Action for brewing a potion.
 * @author Kian Lok Chin
 */
public class BrewPotionAction extends Action {
    private Pouch pouch;
    private Potion potionType;

    /**
     * Constructor for BrewPotionAction
     *
     * @param pouch The pouch to brew with
     * @param potionType The type of potion to brew
     */
    public BrewPotionAction(Pouch pouch, Potion potionType) {
        this.pouch = pouch;
        this.potionType = potionType;
    }

    /**
     * Execute the action to brew the potion.
     *
     * @param actor The actor brewing the potion
     * @param map The game map
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (pouch.consumeIngredients(actor, potionType.getRequiredIngredients())) {
            Potion newPotion = potionType.createNewInstance();
            actor.addItemToInventory(newPotion);
            return actor + " brewed a " + newPotion;
        } else {
            return "Failed to brew " + potionType;
        }
    }

    /**
     * Returns a description of this action for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Make " + potionType + " (" + describeIngredients(potionType) + ")";
    }

    /**
     * Helper method to create a readable description of required ingredients
     *
     * @param potion The potion to describe
     * @return A string describing the ingredients
     */
    private String describeIngredients(Potion potion) {
        StringBuilder description = new StringBuilder();

        // Count occurrences of each Status
       Map<Status, Integer> counts = new HashMap<>();
        for (Status status : potion.getRequiredIngredients()) {
            counts.put(status, counts.getOrDefault(status, 0) + 1);
        }

        // Build description
        boolean first = true;
        for (java.util.Map.Entry<Status, Integer> entry : counts.entrySet()) {
            if (!first) {
                description.append(" + ");
            }
            description.append(entry.getValue()).append("x ").append(entry.getKey()).append(" item");
            if (entry.getValue() > 1) {
                description.append("s");
            }
            first = false;
        }

        return description.toString();
    }
}
