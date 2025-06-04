package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Ability;
import game.actors.Player;

/**
 * Represents the effects of warm, clear weather on the player.
 *
 * During clear weather, the player's stamina is reduced and their body temperature increases,
 * unless they are protected from the sun. If the temperature moves outside the safe range,
 * the player becomes unconscious.
 *
 * @author Lim Chi Jian
 */
public class WarmEffect extends WeatherEffect {
    private final  String emoji = "☀\uFE0F";
    private final  String color = "\u001B[33m";
    private static final int STAMINA_REDUCTION_PER_TICK = 25;
    private static final int TEMP_RESTORATION_PER_TICK = 1;
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
     * Applies the warm weather effect to the player.
     * If the player has the BLOCK_SUN capability, they are protected and no effect occurs.
     * Otherwise, the player's stamina is reduced and body temperature increased.
     * If temperature falls outside the safe range, the player becomes unconscious.
     *
     * @param player the player affected by the warm weather
     * @param map the game map context
     */
    @Override
    protected void applyEffect(Player player, GameMap map) {
        Display display = new Display();

        if (player.hasCapability(Ability.BLOCK_SUN)) {
            display.println("The extreme heat rages, but " + player + " is protected from the sun.");
            return;
        }

        player.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, STAMINA_REDUCTION_PER_TICK);
        player.warm(TEMP_RESTORATION_PER_TICK);
        display.println("Extreme heat, stamina reduced and body temperature increase!");

        if (player.getTemperature() <= MIN_SAFE_TEMPERATURE || player.getTemperature() >= MAX_SAFE_TEMPERATURE) {
            display.println(player.unconscious(map));
        }
    }

    /**
     * Returns the string representation of this object.
     *
     * @return the string "Sunny".
     */
    @Override
    public String toString() {
        return "Sunny";
    }
}


