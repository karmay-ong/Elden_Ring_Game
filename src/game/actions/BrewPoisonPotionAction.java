package game.actions;

import java.util.List;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.potions.Pouch;
import game.potions.PoisonPotion;

/**
 * Action for brewing a Poison Potion.
 *
 * @author Kian Lok Chin
 */
public class BrewPoisonPotionAction extends Action {

    private Pouch pouch;
    private List<Status> requiredCapabilities;

    /**
     * Constructor for BrewPoisonPotionAction
     *
     * @param pouch The pouch to brew with
     * @param requiredCapabilities The capabilities needed for the recipe
     */
    public BrewPoisonPotionAction(Pouch pouch, List<Status> requiredCapabilities) {
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
            actor.addItemToInventory(new PoisonPotion());
            return actor + " brewed a Poison Potion\uD83E\uDDEA\uD83D\uDDA4";
        } else {
            return "Failed to brew Poison Potion";
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
        return "Make Poison Potion\uD83E\uDDEA\uD83D\uDDA4 (1x CURSED item + 1x DRINKABLE item)";
    }
}
