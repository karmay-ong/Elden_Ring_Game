package game.time;

import edu.monash.fit2099.engine.displays.Display;
import game.effects.WeatherType;

import java.util.Random;

/**
 * The {@code WeatherSystem} is a singleton class that manages dynamic weather changes in the game.
 * It simulates random weather transitions over time and affects gameplay by exposing the current weather.
 * Weather changes every few turns based on a probability threshold and a duration counter.
 * This class is intended to be accessed globally via  #getInstance().
 *
 * @author Lim Chi Jian
 */
public class WeatherSystem {

    /**
     * Singleton instance of the WeatherSystem.
     */
    private static final WeatherSystem instance = new WeatherSystem();

    /**
     * The current weather state.
     */
    private WeatherType currentWeather;

    /**
     * Counter for how long the current weather has lasted.
     */
    private int weatherDuration;

    /**
     * Random generator used for determining weather changes.
     */
    private final Random random = new Random();

    /**
     * The number of turns before weather has a chance to change.
     */
    private static final int WEATHER_DURATION_THRESHOLD = 5;

    /**
     * The probability that the weather will change once the threshold is reached.
     */
    private static final double WEATHER_EVENT_PROBABILITY = 0.6;

    /**
     * Private constructor to ensure singleton access.
     * Randomly initializes the starting weather.
     */
    private WeatherSystem() {
        WeatherType[] allWeathers = WeatherType.values();
        currentWeather = allWeathers[random.nextInt(allWeathers.length)];
        weatherDuration = 0;
    }

    /**
     * Returns the singleton instance of the WeatherSystem.
     *
     * @return the single {@code WeatherSystem} instance
     */
    public static WeatherSystem getInstance() {
        return instance;
    }

    /**
     * Retrieves the current weather and potentially changes it based on the duration and probability.
     * Weather information is displayed with a stylized output.
     *
     * @return the current WeatherType
     */
    public WeatherType getCurrentWeather() {
        Display display = new Display();
        String weatherBorder = "═══════════════════════════════════════════";
        String weatherTitle = "║             WEATHER FORECAST             ║";

        display.println("\u001B[36m" + weatherBorder + "\u001B[0m");
        display.println("\u001B[36m" + weatherTitle + "\u001B[0m");
        display.println("\u001B[36m" + weatherBorder + "\u001B[0m");

        // Weather transition logic
        if (weatherDuration >= WEATHER_DURATION_THRESHOLD) {
            if (random.nextDouble() < WEATHER_EVENT_PROBABILITY) {
                WeatherType[] allWeathers = WeatherType.values();
                WeatherType newWeather;
                do {
                    newWeather = allWeathers[random.nextInt(allWeathers.length)];
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
