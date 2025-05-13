package game.actors;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents a condition that determines whether a monologue is eligible
 * to be spoken, based on the listener, game map, and speaker context.
 *
 *  * @author Lim Chi Jian
 *  * @version ver1.0.0
 */
public interface Condition {
    /**
     * Tests whether the condition holds for the given context.
     *
     * @param listener the actor listening to the speaker
     * @param map      the game map where the interaction occurs
     * @param speaker  the actor speaking the monologue
     * @return true if the condition is met; false otherwise
     */
    boolean test(Actor listener, GameMap map, Actor speaker);
    /**
     * Always-true condition for unconditional monologues.
     */
    Condition ALWAYS = (listener, map, speaker) -> true;
}