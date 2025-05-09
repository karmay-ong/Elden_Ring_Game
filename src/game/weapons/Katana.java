package game.weapons;


public class Katana extends WeaponItem {
    public static final int DAMAGE = 50;
    public static final int HIT_RATE = 60;

    /**
     * A swift katana for quick strikes.
     *  • damage = 15
     *  • hitRate = 90%
     *  • verb = "cuts"
     */
    public Katana() {
        super("Katana⚔", 'K', DAMAGE, "cuts", HIT_RATE);
    }

}
