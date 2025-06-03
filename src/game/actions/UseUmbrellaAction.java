package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Umbrella;

/**
 * Action class that allows an actor to use an Umbrella.
 * The umbrella can be opened to shield against acid rain or closed when no longer needed.
 * This action toggles the state of the umbrella.
 *
 * Opening the umbrella provides protection, while closing it removes that protection.
 *
 * @author Lim Chi Jian
 */
public class UseUmbrellaAction extends Action {

    /**
     * The umbrella item being used.
     */
    private final Umbrella umbrella;

    /**
     * Constructor for UseUmbrellaAction.
     *
     * @param umbrella The umbrella item to be opened or closed.
     */
    public UseUmbrellaAction(Umbrella umbrella) {
        this.umbrella = umbrella;
    }

    /**
     * Executes the umbrella usage action. Toggles the umbrella's open state.
     *
     * @param actor The actor performing the action.
     * @param map The game map the actor is on.
     * @return A string describing the result of using the umbrella.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        umbrella.use(actor);

        if (umbrella.getUmbrellaIsOpen()) {
            return actor + " opens the " + umbrella + ", shielding against the acid rain.";
        } else {
            return actor + " closes the " + umbrella + ".";
        }
    }

    /**
     * Returns a description of this action suitable for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the umbrella usage.
     */
    @Override
    public String menuDescription(Actor actor) {
        if (umbrella.getUmbrellaIsOpen()) {
            return actor + " closes the " + umbrella;
        } else {
            return actor + " opens the " + umbrella + " for protection";
        }
    }
}
