package game.weapons;

public class BroadSword extends WeaponItem {
    public static final int DAMAGE = 30;
    public static final int HIT_RATE = 50;

    /**
     * A heavy, two‐handed broadsword.
     *  • damage = 20
     *  • hitRate = 75%
     *  • verb = "slashes"
     */
    public BroadSword() {
        super("Broadsword\uD83D\uDDE1\uFE0F", 'B', DAMAGE, "slashes", HIT_RATE);
    }

   
}
