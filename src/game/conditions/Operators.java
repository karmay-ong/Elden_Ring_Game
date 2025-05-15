package game.conditions;

/**
 * Enumeration of comparison operators used in various conditions throughout the game.
 * These operators define how values should be compared (less than, greater than, etc.)
 * and are used in conditions like BalanceCondition and ActorAttributeCondition.
 *
 * @author Kian Lok Chin
 */
public enum Operators {
    /**
     * Less than operator (<)
     * Returns true when the first value is less than the second value.
     */
    LESS_THAN,

    /**
     * Less than or equal to operator (<=)
     * Returns true when the first value is less than or equal to the second value.
     */
    LESS_OR_EQUAL_TO,

    /**
     * Greater than operator (>)
     * Returns true when the first value is greater than the second value.
     */
    GREATER_THAN,

    /**
     * Greater than or equal to operator (>=)
     * Returns true when the first value is greater than or equal to the second value.
     */
    GREATER_OR_EQUAL_TO,

    /**
     * Equal to operator (==)
     * Returns true when the first value is equal to the second value.
     */
    EQUAL_TO
}
