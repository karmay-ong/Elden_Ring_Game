package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Represents a claw intrinsic weapon used by an actor.
 *
 * This weapon has a fixed hit rate and customizable damage.
 *
 * @author Kar May Ong
 */
public class Claw extends IntrinsicWeapon {

    /** The hit rate percentage of the claw attack. */
    private static final int HIT_RATE = 75;

    /**
     * Constructs a Claw with specified damage.
     *
     * @param damage the damage inflicted by the claw
     */
    public Claw(int damage) {
        super(damage, "smashes", HIT_RATE);
    }
}

