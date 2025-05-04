package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Ability;

/**
 * A class representing a Talisman that an actor can pick up and drop.
 * The Talisman is represented by 'o' on the game map and has the CURE ability,
 * allowing its holder to cure entities like creatures or blighted ground.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 */
public class Talisman extends Item {

    /**
     * Constructor for the Talisman class.
     * Initializes the talisman with name "Talisman", display character 'o',
     * as portable, and adds the CURE capability to it.
     */
    public Talisman() {
        super("Talisman\uD83E\uDDFF", 'o', true);
        this.addCapability(Ability.CURE);
    }
}
