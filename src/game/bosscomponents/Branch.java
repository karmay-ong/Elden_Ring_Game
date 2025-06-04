package game.bosscomponents;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Branch component that can grow and deal damage.
 * A Branch can recursively grow more Branches or Leaves as children.
 * Implements the Growable interface.
 *
 * @author Kar May Ong
 */
public class Branch implements Growable {
    /**
     * Childs (growable parts) attached to this Branch.
     */
    private List<Growable> child = new ArrayList<>();

    /**
     * Branch's default damage point.
     */
    private static final int BASE_DAMAGE_POINT = 3;

    private final Random rand = new Random();

    /**
     * Grows the branch by adding either a new Branch or Leaf child.
     * Recursively grows the newly added component.
     *
     * @param actor the actor responsible for the growth
     * @return a description of the growth process
     */
    @Override
    public String grow(Actor actor) {
        String output = actor + " is growing...\n";
        Growable component;
        if (rand.nextBoolean()) {
            component = new Branch();
        } else {
            component = new Leaf();
        }
        child.add(component);
        output += " it grows a " + component + "\n" + component.grow(actor);
        return output;
    }

    /**
     * Calculates the total damage points of this branch, including its children.
     *
     * @return the combined damage points of the branch and its children
     */
    @Override
    public int getDamagePoint() {
        int result = BASE_DAMAGE_POINT;
        for (Growable component : child) {
            result += component.getDamagePoint();
        }
        return result;
    }

    /**
     * Returns a string representation of the Branch.
     *
     * @return the string "Branch" with an emoji
     */
    @Override
    public String toString() {
        return "Branch\uD83E\uDEB5";
    }
}
