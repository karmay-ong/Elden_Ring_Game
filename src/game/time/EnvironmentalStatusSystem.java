package game.time;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.effects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton system that manages environmental status effects on the player
 * based on the current weather and time changes.
 *
 * It listens to time changes and applies appropriate weather effects
 * to the player character on the given game map.
 *
 * @author Lim Chi Jian
 */
public class EnvironmentalStatusSystem {

    private static EnvironmentalStatusSystem instance = null;
    private Player player;
    private final GameMap map;
    private final List<WeatherEffect> weatherEffects = new ArrayList<>();

    /**
     * Private constructor to enforce singleton pattern.
     * Registers this system with the TimeSystem and initializes weather effects.
     *
     * @param player the player affected by environmental status effects
     * @param map the game map where the player exists
     */
    private EnvironmentalStatusSystem(Player player, GameMap map) {
        this.player = player;
        this.map = map;

        TimeSystem.register(this);

        weatherEffects.add(new SnowEffect());
        weatherEffects.add(new AcidRainEffect());
        weatherEffects.add(new WarmEffect());
    }

    /**
     * Initializes the singleton instance of EnvironmentalStatusSystem.
     * Throws IllegalStateException if called more than once.
     *
     * @param player the player to be managed by this system
     * @param map the game map
     */
    public static void initialize(Player player, GameMap map) {
        if (instance != null) {
            throw new IllegalStateException("EnvironmentalStatusSystem has been initialized before!");
        }
        instance = new EnvironmentalStatusSystem(player, map);
    }

    /**
     * Called when the time changes, applies relevant weather effects to the player.
     */
    public void timeChanged() {
        WeatherType currentWeather = WeatherSystem.getInstance().getCurrentWeather();
        for (WeatherEffect effect : weatherEffects) {
            if (effect.isApplicable(currentWeather)) {
                new Display().println(effect.getColor() + "Today is a " + effect + " " + effect.getEmoji() + " day." + "\u001B[0m");
                new Display().println("\u001B[36m" + "═══════════════════════════════════════════" + "\u001B[0m");
                effect.apply(player, map);
            }
        }
    }
}


