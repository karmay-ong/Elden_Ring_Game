package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface representing effects that can be applied to actors in the game.
 * Effects can modify actor attributes, provide benefits, apply penalties, or trigger
 * special actions when they are applied. They can be attached to items, locations,
 * or triggered by specific game events.
 *
 * @author Kian Lok Chin
 */
public interface Effect {
    /**
     * Applies this effect to the specified actor.
     * The implementation determines what changes or actions occur to the actor.
     * The game map parameter provides context and can be used for effects that
     * interact with the environment or other actors.
     *
     * @param actor the actor to apply the effect to
     * @param map the game map where the actor is located
     */
    void apply(Actor actor, GameMap map);
}
