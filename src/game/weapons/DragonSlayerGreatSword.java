package game.weapons;


public class DragonSlayerGreatSword extends WeaponItem {
    public static final int DAMAGE = 70;
    public static final int HIT_RATE = 75;

    /**
     * A massive sword that eats dragons for breakfast.
     *  • damage = 35
     *  • hitRate = 60%
     *  • verb = "cleaves"
     */
    public DragonSlayerGreatSword() {
        super("Dragonslayer Greatsword⚔\uFE0F", 'D', DAMAGE, "cleaves", HIT_RATE);
    }

}