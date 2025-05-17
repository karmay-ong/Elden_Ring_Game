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
     * A predefined condition that always returns true regardless of the location.
     * Can be used as a default condition when an action should always be available.
     */
    Condition ALWAYS = (location) -> true;
}
