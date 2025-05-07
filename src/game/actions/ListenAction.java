package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.conversationalActors.ConversationalActor;

public class ListenAction extends Action {
    private ConversationalActor speaker;
    private String direction;

    public ListenAction(ConversationalActor speaker, String direction) {
        this.speaker = speaker;
        this.direction = direction;
    }

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
        System.out.println(color + boxedMonologue + reset);

        return actor + " listens to " + speaker + ".";
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Listen to " + speaker + " at " + direction;
    }
}
