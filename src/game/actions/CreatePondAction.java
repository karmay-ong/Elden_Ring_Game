package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Pond;
import game.items.WaterBucket;

/**
 * Action to create a pond when a water bucket is dropped.
 *
 * @author Kian Lok Chin
 */
public class CreatePondAction extends Action {

    private WaterBucket waterBucket;

    /**
     * Constructor for CreatePondAction
     *
     * @param waterBucket The water bucket being dropped
     */
    public CreatePondAction(WaterBucket waterBucket) {
        this.waterBucket = waterBucket;
    }

    /**
     * Execute the action to create a pond.
     *
     * @param actor The actor dropping the water bucket
     * @param map The game map
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(waterBucket);
        Location location = map.locationOf(actor);
        location.setGround(new Pond());
        return actor + " poured out water and created a pond";
    }

    /**
     * Returns a description of this action for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " creates a pond\uD83C\uDFDE\uFE0F";
    }
}
