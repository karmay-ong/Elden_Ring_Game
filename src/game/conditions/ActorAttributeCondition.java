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
     * The actor to check
     */
    private Actor actor;
    /**
     * The actor attribute to check
     */
    private  Enum<?> attribute;

    /**
     * The threshold value to compare against
     */
    private int threshold;

    /**
     * The operator to use for comparison (e.g., LESS_THAN, GREATER_THAN)
     */
    private Operators comparison;

    /**
     * Creates a condition based on a specific actor's attribute comparison.
     *
     * @param actor      the specific actor whose attribute will be checked
     * @param attribute  the attribute to check (e.g., BaseActorAttributes.HEALTH)
     * @param threshold  the threshold value to compare against
     * @param comparison the type of comparison to perform (from Operators enum)
     */
    public ActorAttributeCondition(Actor actor, Enum<?> attribute, int threshold, Operators comparison) {
        this.actor = actor;
        this.attribute = attribute;
        this.threshold = threshold;
        this.comparison = comparison;
    }

    /**
     * Creates a condition that will check the attribute of any actor at a given location.
     * This constructor sets the actor field to null, meaning the isSatisfied method will
     * use the actor at the provided location when evaluating the condition.
     *
     * @param attribute  the attribute to check (e.g., BaseActorAttributes.HEALTH)
     * @param threshold  the threshold value to compare against
     * @param comparison the type of comparison to perform (from Operators enum)
     */
    public ActorAttributeCondition( Enum<?> attribute, int threshold, Operators comparison) {
        this.attribute = attribute;
        this.threshold = threshold;
        this.comparison = comparison;
    }


    /**
     * Checks if the specified actor satisfies the attribute condition.
     * Gets the actor's attribute value and compares it to the threshold
     * using the specified comparison operator.
     *
     * Note: This implementation ignores the location parameter as it
     * operates on a specific actor provided in the constructor.
     *
     * @param location the location (not used in this implementation)
     * @return true if the condition is satisfied, false otherwise
     */
    @Override
    public boolean isSatisfied(Location location) {
        Actor target;
        if(actor == null){
            target = location.getActor();
        }
        else{
            target = actor;
        }
        int attributeValue = target.getAttribute(attribute);

        return switch (comparison) {
            case LESS_THAN -> attributeValue < threshold;
            case GREATER_THAN -> attributeValue > threshold;
            case EQUAL_TO -> attributeValue == threshold;
            case LESS_OR_EQUAL_TO -> attributeValue <= threshold;
            case GREATER_OR_EQUAL_TO -> attributeValue >= threshold;
        };
    }
}