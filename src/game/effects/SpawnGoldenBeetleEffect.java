package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Merchant;
import game.actors.creatures.GoldenBeetle;

/**
 * An effect that spawns a Golden Beetle near the target actor when a purchase is made
 *
 * @author Kian Lok Chin
 */
public class SpawnGoldenBeetleEffect implements PurchaseEffect {

    /**
     * The target actor near which the Golden Beetle will spawn
     */
    private Actor target;

    /**
     * Constructor for the SpawnGoldenBeetleEffect with a specified target
     *
     * @param target The actor near which the Golden Beetle will spawn
     */
    public SpawnGoldenBeetleEffect(Actor target){
        this.target = target;
    }

    /**
     * Default constructor for the SpawnGoldenBeetleEffect
     * When no target is specified, the buyer will be used as the target
     */
    public SpawnGoldenBeetleEffect(){}

    /**
     * Applies the effect by spawning a Golden Beetle in an adjacent location to the target
     * If no target was specified in the constructor, the buyer becomes the target
     *
     * @param buyer The actor who made the purchase
     * @param merchant The merchant who sold the item
     * @param map The game map where the transaction occurred
     */
    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        if (target == null) {
            target = buyer;
        }
        Location here = map.locationOf(target);
        GoldenBeetle goldenBeetle = new GoldenBeetle();
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(goldenBeetle)) {
                dest.addActor(goldenBeetle);
                break;
            }
        }
    }
}