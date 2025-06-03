package game.effects;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

/**
 * Abstract base class for weather effects applied to the player.
 *
 * Subclasses define specific weather effects and implement logic to determine
 * if the effect is applicable and how it is applied.
 *
 * @author Lim Chi Jian
 */
public abstract class WeatherEffect {

    /**
     * Checks if the weather effect should be applied based on the current weather.
     *
     * @param weather the current weather type
     * @return true if the effect applies to the given weather, false otherwise
     */
    public abstract boolean isApplicable(WeatherType weather);

    /**
     * Defines the actual effect to apply to the player.
     *
     * @param player the player affected by the weather
     * @param map the current game map context
     */
    protected abstract void applyEffect(Player player, GameMap map);

    /**
     * Public entry point for applying the weather effect to a player.
     *
     * @param actor the player affected
     * @param map the game map context
     */
    public final void apply(Player actor, GameMap map) {
        applyEffect(actor, map);
    }

    /**
     * Returns the color representation of this object.
     *
     * @return a string representing the color, typically used for UI or display purposes.
     */
    public abstract String getColor();

    /**
     * Returns the emoji representation of this object.
     *
     * @return a string containing an emoji symbol that visually represents this object.
     */
    public abstract String getEmoji();

}

