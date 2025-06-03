package game.actors;

/**
 * Enum representing different status conditions that can be applied to game elements.
 * These statuses define special conditions that can be checked using the hasCapability method.
 *
 * <p>Current statuses include:
 * <ul>
 *   <li>HOSTILE_TO_ENEMY: Indicates an entity that will engage in combat with enemies</li>
 *   <li>PLANTABLE: Indicates ground that can have seeds planted on it</li>
 *   <li>CURSED: Indicates an entity that is under a curse effect</li>
 * </ul>
 *
 * <p>Example usage: If the player is hostile to enemies, you can attach Status.HOSTILE_TO_ENEMY to the player class.
 *
 * @author Riordan D. Alfredo
 * @author Kian Lok Chin
 * Modified by: Kar May Ong
 * Modified by: YOUSSEF HASSANEIN
 */
public enum Status {
    /**
     * Indicates an entity that is hostile towards enemies.
     * Typically applied to the player and other combat-oriented actors.
     */
    HOSTILE_TO_ENEMY,

    /**
     * Indicates ground that can be planted on.
     * Used to determine valid locations for planting seeds.
     */
    PLANTABLE,

    /**
     * Indicates an entity that is under a curse effect.
     * May have various negative effects depending on implementation.
     */
    CURSED,
    /**
     * Indicates an entity that is under a bless effect.
     * May have various positive effects depending on implementation.
     */
    BLESSED,
    /**
     * Indicates an entity that can be followed by other actors.
     * Typically applied on the player.
     */
    FOLLOWABLE,
    /**
     * Indicates that the item is safe or suitable for drinking.
     */
    DRINKABLE
}
