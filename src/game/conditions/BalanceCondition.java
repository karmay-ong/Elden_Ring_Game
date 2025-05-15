package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if an actor's balance (currency) compares to a specified threshold
 * using operations such as less than, greater than, or equal to.
 * This can be used to trigger events or dialogue based on an actor's economic status.
 *
 * @author Kian Lok Chin
 */
public class BalanceCondition implements Condition{
    /**
     * The threshold value to compare the actor's balance against
     */
    private final int threshold;

    /**
     * The operator to use for comparison (e.g., LESS_THAN, GREATER_THAN)
     */
    private final Operators comparison;

    /**
     * Creates a condition based on an actor's balance comparison.
     *
     * @param threshold the threshold value to compare the actor's balance against
     * @param comparison the type of comparison to perform (from Operators enum)
     */
    public BalanceCondition(int threshold, Operators comparison) {
        this.threshold = threshold;
        this.comparison = comparison;
    }

    /**
     * Checks if the actor at the specified location satisfies the balance condition.
     * Retrieves the actor at the location, gets their balance, and compares it
     * to the threshold using the specified comparison operator.
     *
     * @param location the location containing the actor to check
     * @return true if the condition is satisfied, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        Actor actor = location.getActor();
        int actorBalance = actor.getBalance();

        return switch (comparison) {
            case LESS_THAN -> actorBalance < threshold;
            case GREATER_THAN -> actorBalance > threshold;
            case EQUAL_TO -> actorBalance == threshold;
            case LESS_OR_EQUAL_TO -> actorBalance <= threshold;
            case GREATER_OR_EQUAL_TO -> actorBalance >= threshold;
        };
    }
}
