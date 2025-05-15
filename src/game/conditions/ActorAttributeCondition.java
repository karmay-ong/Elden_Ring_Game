package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if an actor's attribute value compares to a threshold value in a specified way.
 * This condition evaluates the actor at the given location and compares one of their attributes
 * against a threshold using operations like less than, greater than, or equal to.
 * Used for conditions like low health, high stamina, etc.
 *
 * @author Kian Lok Chin
 */
public class ActorAttributeCondition implements Condition {
    /**
     * The actor attribute to check
     */
    private final Enum<?> attribute;

    /**
     * The threshold value to compare against
     */
    private final int threshold;

    /**
     * The operator to use for comparison (e.g., LESS_THAN, GREATER_THAN)
     */
    private final Operators comparison;

    /**
     * Creates a condition based on an actor attribute comparison.
     *
     * @param attribute the attribute to check (e.g., BaseActorAttributes.HEALTH)
     * @param threshold the threshold value to compare against
     * @param comparison the type of comparison to perform (from Operators enum)
     */
    public ActorAttributeCondition(Enum<?> attribute, int threshold, Operators comparison) {
        this.attribute = attribute;
        this.threshold = threshold;
        this.comparison = comparison;
    }

    /**
     * Checks if the actor at the specified location satisfies the attribute condition.
     * Retrieves the actor at the location, gets their attribute value, and compares it
     * to the threshold using the specified comparison operator.
     *
     * @param location the location containing the actor to check
     * @return true if the condition is satisfied, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        Actor actor = location.getActor();
        int attributeValue = actor.getAttribute(attribute);

        return switch (comparison) {
            case LESS_THAN -> attributeValue < threshold;
            case GREATER_THAN -> attributeValue > threshold;
            case EQUAL_TO -> attributeValue == threshold;
            case LESS_OR_EQUAL_TO -> attributeValue <= threshold;
            case GREATER_OR_EQUAL_TO -> attributeValue >= threshold;
        };
    }
}
