package game.items;

import game.actors.Status;

/**
 * Meat dropped by Spirit Goat when they die.
 *
 * @author Kian Lok Chin
 */
public class SpiritGoatMeat extends Meat{

    /**
     * Constructor for SpiritGoatMeat
     */
    public SpiritGoatMeat() {
        super("Spirit Goat Meat", 'G', true);
        addCapability(Status.BLESSED);
    }

}
