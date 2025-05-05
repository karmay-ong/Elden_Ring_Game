// game/actions/SpawnAction.java
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Spawns an offspring Actor at a given location.
 */
public class SpawnAction extends Action {
    private final Actor baby;
    private final Location loc;

    public SpawnAction(Actor baby, Location loc) {
        this.baby = baby;
        this.loc = loc;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        loc.addActor(baby);
        return baby + " is born";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " spawns a " + baby;
    }
}
