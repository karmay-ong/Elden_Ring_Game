package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Eatable;


public class EatAction extends Action {

    private Actor actor;
    private Eatable eatable;

    public EatAction(Actor actor, Eatable eatable) {
        this.eatable = eatable;
        this.actor = actor;
    }

    /**
     * Executes the EatAction.
     * Removes the item from the actor's inventory and increases the actor's maximum health
     * if the actor is the Farmer.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "\uD83C\uDF7D\uFE0F" + actor + " ate " + eatable + ".";
        eatable.eat(actor, map);
        return result;
    }

    /**
     * Returns a description of the action suitable for use in a menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player eats the egg"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + eatable.toString();
    }
}
