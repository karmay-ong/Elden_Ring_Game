package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action class for making actors rot away
 *
 * @author Kian Lok Chin
 */
public class RotAction extends Action {
    /**
     * The actor that will rot away
     */
    private Actor target;

    /**
     * Constructor for the RotAction
     *
     * @param target the actor that should rot away
     */
    public RotAction(Actor target) {
        this.target = target;
    }

    /**
     * Executes the rot action, making the target actor unconscious
     *
     * @param actor The actor performing the action
     * @param map The game map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return target.unconscious(map);
    }

    /**
     * Returns a description of this action suitable for the menu
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " rots away";
    }
}

