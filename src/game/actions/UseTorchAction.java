package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Torch;

/**
 * Action class that allows an actor to use a Torch.
 * The torch can be lit or extinguished depending on its current state.
 * Lighting the torch provides warmth and light, while extinguishing removes it.
 *
 * This action toggles the state of the torch.
 *
 * @author Lim Chi Jian
 */
public class UseTorchAction extends Action {

    /**
     * The torch item being used.
     */
    private final Torch item;

    /**
     * Constructor for UseTorchAction.
     *
     * @param item The torch item to be lit or extinguished.
     */
    public UseTorchAction(Torch item) {
        this.item = item;
    }

    /**
     * Executes the torch usage action. Toggles the torch's lit state.
     *
     * @param actor The actor performing the action.
     * @param map The game map the actor is on.
     * @return A string describing the result of using the torch.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.use(actor);

        if (item.getTorchLit()) {
            return actor + " lights the " + item + ", providing warmth and light.";
        } else {
            return actor + " extinguishes the " + item + ".";
        }
    }

    /**
     * Returns a description of this action suitable for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the torch usage.
     */
    @Override
    public String menuDescription(Actor actor) {
        if (item.getTorchLit()) {
            return actor + " extinguishes the " + item;
        } else {
            return actor + " lights the " + item + " for warmth";
        }
    }
}

