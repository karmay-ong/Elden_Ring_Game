package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.WaterBucket;

/**
 * Action for collecting water from a pond.
 *
 * @author Kian Lok Chin
 */
public class CollectWaterAction extends Action {

    /**
     * Execute the action to collect water.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new WaterBucket());
        return actor + " collected a bucket of water.";
    }

    /**
     * Returns a description of this action for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " collects water from the pond\uD83E\uDEA3";
    }
}
