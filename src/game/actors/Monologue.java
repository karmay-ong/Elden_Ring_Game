package game.actors;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Encapsulates a single line of dialogue (text) together with a Condition
 * that determines whether it is eligible to be spoken.

 *  * @author Lim Chi Jian
 *  * @version ver1.0.0
 */
public class Monologue {
    private String text;
    private Condition condition;

    /**
     * Constructs a new Monologue with the given text and eligibility condition.
     *
     * @param text      the dialogue text
     * @param condition the condition controlling eligibility
     */
    public Monologue(String text, Condition condition) {
        this.text = text;
        this.condition = condition;
    }

    /**
     * Checks whether this monologue is eligible to be spoken in the given context.
     *
     * @param listener the actor listening to the speaker
     * @param map      the game map of the interaction
     * @param speaker  the actor who would speak the monologue
     * @return true if the underlying condition is met; false otherwise
     */
    public boolean isEligible(Actor listener, GameMap map, Actor speaker) {
        return condition.test(listener, map, speaker);
    }

    /**
     * Returns the text of this monologue.
     *
     * @return the dialogue text
     */
    public String getText() {
        return text;
    }
}