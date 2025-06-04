package game.bosscomponents;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Represents a Leaf component that can grow and heal an actor.
 * Implements the Growable interface.
 *
 * @author Kar May Ong
 */
public class Leaf implements Growable {
    /**
     * Default amount a leaf can heal.
     */
    private static final int HEAL_AMOUNT = 5;

    /**
     * Default damage point for a leaf.
     */
    private static final int DAMAGE_POINT = 1;

    /**
     * Grows the leaf by healing the given actor.
     *
     * @param actor the actor to be healed
     * @return a description string indicating the actor is healed
     */
    @Override
    public String grow(Actor actor) {
        actor.heal(HEAL_AMOUNT);
        return actor + " is healed";
    }

    /**
     * Returns the damage points that this leaf can deal.
     *
     * @return the damage points of the leaf
     */
    @Override
    public int getDamagePoint() {
        return DAMAGE_POINT;
    }

    /**
     * Returns a string representation of the Leaf.
     *
     * @return the string "Leaf" with an emoji
     */
    @Override
    public String toString() {
        return "Leaf\uD83C\uDF3F";
    }
}
