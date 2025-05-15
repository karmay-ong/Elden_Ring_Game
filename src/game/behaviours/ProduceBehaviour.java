package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ProduceAction;
import game.actors.Producible;
import game.conditions.Condition;

/**
 * A behaviour that allows an actor to perform a production action when a specific condition is met.
 * The production only occurs when the provided condition is satisfied at the actor's location.
 *
 * @author Pemudi Hiruni Halgahawatta Liyanaarachchi
 */

public class ProduceBehaviour implements Behaviour {
    /**
     * The object that implements the Producible interface and handles production logic
     */
    private Producible producer;

    /**
     * The condition that must be satisfied for production to occur
     */
    private Condition condition;

    /**
     * Creates a ProduceBehaviour with the given producer and condition.
     *
     * @param producer the object that handles production logic
     * @param condition the condition that must be satisfied for production to occur
     */
    public ProduceBehaviour(Producible producer, Condition condition) {
        this.producer = producer;
        this.condition = condition;
    }

    /**
     * Returns a ProduceAction if the specified condition is satisfied at the actor's location;
     * otherwise, returns null.
     *
     * @param actor the actor performing the behaviour
     * @param map the map the actor is on
     * @return a ProduceAction if the condition is satisfied, or null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (condition.isSatisfied(map.locationOf(actor))) {
            return new ProduceAction(producer);
        }
        return null;
    }
}
