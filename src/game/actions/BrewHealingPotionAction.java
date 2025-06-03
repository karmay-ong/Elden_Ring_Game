package game.actions;

import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.potions.Pouch;
import game.potions.HealingPotion;

/**
 * Action for brewing a Healing Potion.
 *
 * @author Kian Lok Chin
 */
public class BrewHealingPotionAction extends Action {

    private Pouch pouch;
    private List<Status> requiredCapabilities;

    /**
     * Constructor for BrewHealingPotionAction
     *
     * @param pouch The pouch to brew with
     * @param requiredCapabilities The capabilities needed for the recipe
     */
    public BrewHealingPotionAction(Pouch pouch, List<Status> requiredCapabilities) {
        this.pouch = pouch;
        this.requiredCapabilities = requiredCapabilities;
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
        if (pouch.consumeIngredients(actor, requiredCapabilities)) {
            actor.addItemToInventory(new HealingPotion());
            return actor + " brewed a Healing Potion\uD83E\uDDEA\uD83D\uDC9C";
        } else {
            return "Failed to brew Healing Potion";
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
        return "Make Healing Potion\uD83E\uDDEA\uD83D\uDC9C (1x BLESSED item + 1x DRINKABLE item)";
    }
}
