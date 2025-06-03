package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actors.Status;

/**
 * The meat of a Golden Beetle, a rare ingredient for potions.
 *
 * @author Kian Lok Chin
 */
public class GoldenBeetleMeat extends Meat{

    /**
     * Constructor for GoldenBeetleMeat.
     */
    public GoldenBeetleMeat() {
        super("Golden Beetle Meat", 'B', true);
        this.addCapability(Status.CURSED);
    }

}
