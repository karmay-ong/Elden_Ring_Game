package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Producible;

/**
 * An action that allows a Producible actor to produce offspring or items.
 */
public class ProduceAction extends Action {

    private Producible producer;

    /**
     * Constructor for ProduceAction.
     * @param producer the actor that implements Producible
     */
    public ProduceAction(Producible producer) {
        this.producer = producer;
    }

    /**
     * Executes the produce action by checking if the producer can produce and calling its produce method.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of the action result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        producer.produce(actor, map);
        return actor + " has produced offspring or laid an egg.";
    }

    /**
     * Returns a short description of the action to appear in menus.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attempts to produce";
    }
}
