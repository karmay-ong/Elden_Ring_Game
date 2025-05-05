// game/actions/DropEggAction.java
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.OmenSheepEgg;

/**
 * Action that causes an OmenSheep to drop an egg at its location.
 */
public class DropEggAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).addItem(new OmenSheepEgg());
        return actor + " lays an egg";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg";
    }
}
