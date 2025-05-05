// game/items/OmenSheepEgg.java
package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.EatEggAction;
import game.actors.Ability;
import game.actors.Player;
import game.actors.creatures.OmenSheep;

/**
 * Egg laid by OmenSheep. Hatches after 3 turns unless picked up.
 */
public class OmenSheepEgg extends Item {
    private int hatchTimer = 3;

    public OmenSheepEgg() {
        super("Omen Sheep Egg", '0', true);
    }

    /** Called by the map each turn to attempt hatching. */
    public void tick(Location location) {
        if (--hatchTimer <= 0 && !location.containsAnActor()) {
            location.addActor(new OmenSheep());
            location.removeItem(this);
        }
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList list = new ActionList();
        Actor actor = location.getActor();
        if (actor != null && actor.hasCapability(Ability.EAT_EGG)) {
            list.add(new EatEggAction(this));
        }
        return list;
    }
}
