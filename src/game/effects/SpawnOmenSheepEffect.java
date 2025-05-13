package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Merchant;
import game.actors.creatures.OmenSheep;

/**
 * An effect that spawns an Omen Sheep near the target actor when a purchase is made
 *
 * @author Kian Lok Chin
 */
public class SpawnOmenSheepEffect implements PurchaseEffect {

    /**
     * The target actor near which the Omen Sheep will spawn
     */
    private Actor target;

    /**
     * Constructor for the SpawnOmenSheepEffect with a specified target
     *
     * @param actor The actor near which the Omen Sheep will spawn
     */
    public SpawnOmenSheepEffect(Actor actor){
        this.target = actor;
    }

    /**
     * Default constructor for the SpawnOmenSheepEffect
     * When no target is specified, the buyer will be used as the target
     */
    public SpawnOmenSheepEffect(){}

    /**
     * Applies the effect by spawning an Omen Sheep in an adjacent location to the target
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
        Location location = map.locationOf(target);
        OmenSheep omenSheep = new OmenSheep();
        for (Exit exit : location.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(omenSheep)) {
                dest.addActor(omenSheep);
                break;
            }
        }
    }
}
