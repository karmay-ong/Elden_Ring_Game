package game.time;

/**
 * Time system that notifies registered listeners on each tick (time unit).
 *
 * This class manages a single listener, such as the EnvironmentalStatusSystem,
 * and calls its update method when time advances.
 *
 * @author Lim Chi Jian
 */
public class TimeSystem {

    private static EnvironmentalStatusSystem listener;

    /**
     * Registers a TimeListener to be notified on each tick.
     *
     * @param listener the EnvironmentalStatusSystem to register
     */
    public static void register(EnvironmentalStatusSystem listener) {
        TimeSystem.listener = listener;
    }

    /**
     * Called to advance time by one tick and notify the registered listener.
     */
    public static void tickAll() {
        if (listener != null) {
            listener.timeChanged();
        }
    }
}
