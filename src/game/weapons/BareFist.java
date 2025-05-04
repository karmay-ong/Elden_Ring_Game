package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target. It is the default weapon used by actors when
 * no other weapon is equipped.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 */
public class BareFist extends IntrinsicWeapon {

    /**
     * Constructor for the BareFist class.
     * Initializes the bare fist with 25 damage, "punches" as the verb,
     * and 50% hit rate.
     */
    public BareFist() {
        super(25, "punches", 50);
    }

    /**
     * Returns a string representation of the bare fist weapon.
     * Includes a fist emoji for visual representation.
     *
     * @return a string representation of the bare fist
     */
    @Override
    public String toString() {
        return "Bare Fist\uD83D\uDC4A\uD83C\uDFFC";
    }
}
