package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.conversationalActors.ConversationalActor;


/**
 * An action that allows an actor to listen to a ConversationalActor, triggering
 * a formatted monologue display. The monologue is rendered in a colored text box.
 *
 * @author Lim Chi Jian
 * @version ver1.0.0
 */
public class ListenAction extends Action {
    private ConversationalActor speaker;
    private String direction;

    /**
     * Constructs a ListenAction targeting the given speaker and direction.
     *
     * @param speaker   the ConversationalActor to listen to
     * @param direction the direction of the speaker
     */
    public ListenAction(ConversationalActor speaker, String direction) {
        this.speaker = speaker;
        this.direction = direction;
    }

    /**
     * Executes the listening action: retrieves a random eligible monologue,
     * formats it into a colored ASCII box, prints it to the console, and
     * returns the boxed text.
     *
     * @param actor the actor performing the action (the listener)
     * @param map   the game map of the interaction
     * @return the formatted monologue text including color codes
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String monologue = speaker.getRandomMonologue(actor, map);
        String color = "\u001B[96m";
        String reset = "\u001B[0m";
        String[] lines = monologue.split("\n");

        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }
        int boxWidth = maxLength + 8;
        String border = "+" + "-".repeat(boxWidth) + "+";
        StringBuilder boxedMonologue = new StringBuilder(border + "\n");

        for (String line : lines) {
            int padding = boxWidth - line.length();
            int leftPad = padding / 2;
            int rightPad = padding - leftPad;
            boxedMonologue.append(" ".repeat(leftPad) + line + " ".repeat(rightPad) + "\n");
        }
        boxedMonologue.append(border);
        return color + boxedMonologue + reset;
    }

    /**
     * Provides a description for display in the action menu.
     *
     * @param actor the actor choosing this action
     * @return a string describing the listen action with the speaker and direction
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Listen to " + speaker + " at " + direction;
    }
}
