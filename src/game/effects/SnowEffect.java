package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Ability;
import game.actors.Player;

/**
 * Represents the effects of snow weather on the player.
 *
 * Snow decreases the player's body temperature over time unless they are protected.
 * If the player's temperature falls outside the safe range, they become unconscious.
 *
 * @author Lim Chi Jian
 */
public class SnowEffect extends WeatherEffect {
    private final String emoji = "❄️";
    private final String color = "\u001B[37m";
    private static final int TEMP_REDUCTION_PER_TICK = 1;
    private static final int MIN_SAFE_TEMPERATURE = 12;
    private static final int MAX_SAFE_TEMPERATURE = 50;

    /**
     * Returns the color associated with this object.
     *
     * @return a string representing the color of the object
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Returns the emoji representing this object.
     *
     * @return a string containing the emoji symbol
     */
    @Override
    public String getEmoji() {
        return emoji;
    }


    /**
     * Applies the snow effect to the player.
     * If the player has the ABLE_WARM capability, no temperature reduction occurs.
     * Otherwise, the player's temperature is reduced, and if it reaches unsafe levels,
     * the player becomes unconscious.
     *
     * @param player the player affected by snow
     * @param map the game map context
     */
    @Override
    protected void applyEffect(Player player, GameMap map) {
        Display display = new Display();

        if (player.hasCapability(Ability.ABLE_WARM)) {
            display.println("The heavy snow rages, but " + player + " is shielded from the cold.");
            return;
        }

        player.cold(TEMP_REDUCTION_PER_TICK);
        display.println("Heavy snow rages, temperature reduced by " + TEMP_REDUCTION_PER_TICK + ".");

        if (player.getTemperature() <= MIN_SAFE_TEMPERATURE || player.getTemperature() >= MAX_SAFE_TEMPERATURE) {
            display.println(player.unconscious(map));
        }
    }

    /**
     * Indicates whether this snow instance is acid rain.
     * Always returns false, since snow is not considered acid rain.
     *
     * @return false, indicating this snow is not acid rain.
     */
    public boolean isAcidRain() {
        return false;
    }

    /**
     * Returns a textual representation of the weather condition.
     *
     * @return the string "Snow", indicating snowy weather.
     */
    @Override
    public String toString() {
        return "Snow";
    }
}
