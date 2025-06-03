package game.items;

import game.actors.Status;

/**
 * Meat dropped by Omen Sheep when they die.
 *
 * @author Kian Lok Chin
 */
public class OmenSheepMeat extends Meat {

    /**
     * Constructor for OmenSheepMeat
     */
    public OmenSheepMeat() {
        super("Omen Sheep Meat", 'M', true);
        addCapability(Status.BLESSED);
    }

}
