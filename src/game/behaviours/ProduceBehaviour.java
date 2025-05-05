package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ProduceAction;
import game.actors.Producible;

/**
 * A behaviour that allows an actor to perform a production action if possible.
 */

public class ProduceBehaviour implements Behaviour {
    private Producible producer;

    /**
     * Creates a ProduceBehaviour with the given producer.
     *
     * @param producer the object that handles production logic
     */
    public ProduceBehaviour(Producible producer) {
        this.producer = producer;
    }

    /**
     * Returns a ProduceAction if the actor is allowed to produce; otherwise, returns null.
     *
     * @param actor the actor performing the behaviour
     * @param map   the map the actor is on
     * @return a ProduceAction or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (producer.canProduce(actor, map)) {
            return new ProduceAction(producer);
        }
        return null;
    }
}
