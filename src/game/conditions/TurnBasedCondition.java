package game.conditions;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that becomes satisfied after a specified number of turns have elapsed.
 * The condition maintains an internal counter that increments each time it's checked,
 * and becomes satisfied when the counter reaches the required number of turns.
 * Once satisfied, the counter automatically resets, creating a cyclical pattern.
 *
 * @author Kian Lok Chin
 */
public class TurnBasedCondition implements Condition {
    /**
     * Counter tracking how many turns have passed since last satisfaction or initialization
     */
    private int currentTurns = 0;

    /**
     * The number of turns required before the condition becomes satisfied
     */
    private int requiredTurns;

    /**
     * Creates a new turn-based condition with a specified cycle length.
     *
     * @param requiredTurns the number of turns that must pass before the condition is satisfied
     */
    public TurnBasedCondition(int requiredTurns) {
        this.requiredTurns = requiredTurns;
    }

    /**
     * Checks if the required number of turns has passed and manages the turn counter.
     * Each call to this method increments the turn counter, regardless of the location parameter.
     * The location parameter is not used in this implementation.
     *
     * When the turn counter reaches or exceeds the required turns:
     * - The counter is reset to 0
     * - The method returns true
     * Otherwise, the method returns false.
     *
     * @param location the location parameter (not used in this implementation)
     * @return true if the required number of turns has passed, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        currentTurns++;
        if (currentTurns >= requiredTurns) {
            currentTurns = 0;
            return true;
        }
        return false;
    }
}
