package game.actors;

/**
 * Enum representing special abilities that can be possessed by actors, items, or other game elements.
 * These abilities define special capabilities that can be checked using the hasCapability method.
 *
 * <p>Current abilities include:
 * <ul>
 *   <li>CURE: The ability to cure or heal other entities</li>
 * </ul>
 *
 * <p>Example usage: An item with healing properties would have the CURE ability attached to it,
 * which can be checked before performing healing actions.
 *
 * @author Kian Lok Chin
 */
public enum Ability {
    /**
     * Represents the ability to cure or heal other entities.
     * This ability is typically given to items that can be used for healing purposes.
     */
    CURE
}
