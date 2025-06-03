package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.potions.Potion;

/**
 * Action for throwing a potion.
 *
 * @author Kian Lok Chin
 */
public class ThrowPotionAction extends Action {

    private Potion potion;

    /**
     * Constructor for ThrowPotionAction
     *
     * @param potion The potion to throw
     */
    public ThrowPotionAction(Potion potion) {
        this.potion = potion;
    }

    /**
     * Execute the action to throw the potion.
     *
     * @param actor The actor throwing the potion
     * @param map The game map
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        potion.throwPotion(map.locationOf(actor), map);
        actor.removeItemFromInventory(potion);
        return actor + " threw a " + potion;
    }

    /**
     * Returns a description of this action for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " throws " + potion;
    }
}
