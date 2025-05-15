package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.GoldenBeetle;

/**
 * An effect that spawns a Golden Beetle near the target actor when a purchase is made.
 * The beetle is placed in an adjacent location to the target actor if possible.
 *
 * @author Kian Lok Chin
 */
public class SpawnGoldenBeetleEffect implements Effect {

    /**
     * The target actor near which the Golden Beetle will spawn.
     */
    private Actor target;

    /**
     * Constructor for the SpawnGoldenBeetleEffect with a specified target.
     * This creates an effect that will attempt to spawn a Golden Beetle near the given target actor.
     *
     * @param target The actor near which the Golden Beetle will spawn
     */
    public SpawnGoldenBeetleEffect(Actor target) {
        this.target = target;
    }

    /**
     * Applies this effect to the given actor, attempting to spawn a Golden Beetle adjacent to the target actor.
     *
     * @param actor The actor to apply the effect to (in this case, it is not used directly in the method)
     * @param map The game map where the beetle will be spawned
     */
    @Override
    public void apply(Actor actor, GameMap map) {
        spawnGoldenBeetleAdjacentTo(actor, map);
        new Display().println("A Golden Beetle is spawned near " + actor);
    }

    /**
     * Attempts to spawn a Golden Beetle in an adjacent location to the given actor.
     * The beetle is placed in the first available adjacent location that does not contain an actor
     * and where the beetle can enter.
     *
     * @param target The actor near whom the Golden Beetle should be spawned
     * @param map The game map to locate the actor and place the beetle
     */
    private void spawnGoldenBeetleAdjacentTo(Actor target, GameMap map) {
        Location here = map.locationOf(target);
        GoldenBeetle beetle = new GoldenBeetle();
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(beetle)) {
                dest.addActor(beetle);
                break;
            }
        }
    }
}
