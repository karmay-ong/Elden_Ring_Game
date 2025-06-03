package game.time;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.effects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private int weatherDuration;
    private final Random random = new Random();
    private Player player;
    private final GameMap map;
    private final List<WeatherEffect> weatherEffects = new ArrayList<>();
    private WeatherEffect currentWeather;

    /**
     * The number of turns before weather has a chance to change.
     */
    private static final int WEATHER_DURATION_THRESHOLD = 5;

    /**
     * The probability that the weather will change once the threshold is reached.
     */
    private static final double WEATHER_EVENT_PROBABILITY = 0.6;

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
        this.weatherDuration = 0;

        weatherEffects.add(new SnowEffect());
        weatherEffects.add(new AcidRainEffect());
        weatherEffects.add(new WarmEffect());
        currentWeather = weatherEffects.get(random.nextInt(weatherEffects.size()));
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
        WeatherEffect currentWeather = getCurrentWeather();
        Display display = new Display();
        display.println(currentWeather.getColor() + "Today is a " + currentWeather + " " + currentWeather.getEmoji() + " day." + "\u001B[0m");
        display.println("\u001B[36m" + "═══════════════════════════════════════════" + "\u001B[0m");
        currentWeather.apply(player, map);
    }

    public static EnvironmentalStatusSystem getInstance() {
        return instance;
    }

    public WeatherEffect getCurrentWeather() {
        Display display = new Display();
        String weatherBorder = "═══════════════════════════════════════════";
        String weatherTitle = "║             WEATHER FORECAST             ║";

        display.println("\u001B[36m" + weatherBorder + "\u001B[0m");
        display.println("\u001B[36m" + weatherTitle + "\u001B[0m");
        display.println("\u001B[36m" + weatherBorder + "\u001B[0m");

        if (weatherDuration >= WEATHER_DURATION_THRESHOLD) {
            if (random.nextDouble() < WEATHER_EVENT_PROBABILITY) {
                currentWeather = weatherEffects.get(random.nextInt(weatherEffects.size()));
                WeatherEffect newWeather;
                do {
                    newWeather = weatherEffects.get(random.nextInt(weatherEffects.size()));

                } while (newWeather == currentWeather);

                currentWeather = newWeather;
                display.println("\u001B[33m" + "⚠ Weather is changing! ⚠" + "\u001B[0m");
            }
            weatherDuration = 0;
        }
        weatherDuration++;
        return currentWeather;
    }
}

