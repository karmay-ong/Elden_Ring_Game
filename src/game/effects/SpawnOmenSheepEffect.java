package game.effects;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.OmenSheep;

/**
 * An effect that spawns an Omen Sheep near the target actor when a purchase is made.
 * The Omen Sheep will be placed in an adjacent location to the target actor if possible.
 *
 * <p>This effect is typically used to spawn an Omen Sheep near an actor after a purchase action or interaction.</p>
 *
 * @author Kian Lok Chin
 */
public class SpawnOmenSheepEffect implements Effect {

    /**
     * The target actor near which the Omen Sheep will spawn.
     */
    private Actor target;

    /**
     * Constructor for the SpawnOmenSheepEffect with a specified target.
     * This creates an effect that will attempt to spawn an Omen Sheep near the given target actor.
     *
     * @param target The actor near which the Omen Sheep will spawn.
     */
    public SpawnOmenSheepEffect(Actor target) {
        this.target = target;
    }

    /**
     * Applies the effect by spawning an Omen Sheep in an adjacent location to the target actor.
     * The Omen Sheep is placed in the first available adjacent location that does not contain an actor
     * and where the sheep can enter.
     *
     * @param buyer The actor who made the purchase.
     * @param map The game map where the transaction occurred.
     */
    @Override
    public void apply(Actor buyer, GameMap map) {
        spawnOmenSheepAdjacentTo(target, map);
        new Display().println("An Omen Sheep is spawned near " + target);
    }

    /**
     * Attempts to spawn an Omen Sheep in an adjacent location to the given actor.
     * The sheep is placed in the first available adjacent location that does not contain an actor
     * and where the sheep can enter.
     *
     * @param target The actor near whom the Omen Sheep should be spawned.
     * @param map The game map to locate the actor and place the sheep.
     */
    private void spawnOmenSheepAdjacentTo(Actor target, GameMap map) {
        Location here = map.locationOf(target);
        OmenSheep omenSheep = new OmenSheep();
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(omenSheep)) {
                dest.addActor(omenSheep);
                break;
            }
        }
    }
}
