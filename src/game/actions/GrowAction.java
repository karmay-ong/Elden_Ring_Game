package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.bossComponents.Growable;

/**
 * Action class that allows an actor to trigger growth on a Growable target.
 * This can be used to simulate the growth of objects like plants or crops in the game world.
 *
 * @author Kar May Ong
 */
public class GrowAction extends Action {
    /**
     * The target that can be grown.
     */
    private Growable target;

    /**
     * Constructor for GrowAction.
     *
     * @param target The growable object that should grow.
     */
    public GrowAction(Growable target) {
        this.target = target;
    }

    /**
     * Executes the grow action by invoking the grow method on the target.
     *
     * @param actor The actor performing the grow action.
     * @param map The game map the actor is on.
     * @return A string describing the result of the grow action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += target.grow(actor);
        return result;
    }

    /**
     * Returns a description of this action suitable for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows";
    }
}
