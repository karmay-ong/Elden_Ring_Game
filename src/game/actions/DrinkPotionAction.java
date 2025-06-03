package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.potions.Potion;

/**
 * Action for drinking a potion.
 *
 * @author Kian Lok Chin
 */
public class DrinkPotionAction extends Action {

    private Potion potion;

    /**
     * Constructor for DrinkPotionAction
     *
     * @param potion The potion to drink
     */
    public DrinkPotionAction(Potion potion) {
        this.potion = potion;
    }

    /**
     * Execute the action to drink the potion.
     *
     * @param actor The actor drinking the potion
     * @param map The game map
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        StatusEffect effect = potion.drink(actor);
        actor.addStatusEffect(effect);
        actor.removeItemFromInventory(potion);
        return actor + " drank a " + potion;
    }

    /**
     * Returns a description of this action for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + potion;
    }
}
