package game.conditions;

import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that defines conditions that can be checked at a specific location.
 * Conditions are used throughout the game to determine when certain actions, behaviors,
 * or events should occur based on the state of a location and its contents.
 *
 * @author Kian Lok Chin
 */
public interface Condition {
    /**
     * Checks whether this condition is satisfied at the specified location.
     *
     * @param location the location to check the condition against
     * @return true if the condition is satisfied, false otherwise
     */
    boolean isSatisfied(Location location);

    /**
     * A constant condition that is always satisfied, regardless of location.
     * Can be used as a default or fallback condition when a condition is required
     * but no specific check is needed.
     */
    Condition ALWAYS = (location) -> true;
}
