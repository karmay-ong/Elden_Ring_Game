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
     * Returns true if it is a rainy day, can be Acid Rain, Alkaline Rain and Diamond Rain.
     * This is important to handle logic of extinguish the flame in all flammable items.
     */
    public boolean isRaining(){
        return false;
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

