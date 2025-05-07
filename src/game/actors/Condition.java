package game.actors;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Condition {
    boolean test(Actor listener, GameMap map, Actor speaker);
    /** Always-true condition for unconditional lines. */
    Condition ALWAYS = (listener, map, speaker) -> true;
}