package game.actors.conversationalActors;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import java.util.TreeMap;

public class Sellen extends ConversationalActor {
    public final static int SELLEN_HITPOINTS = 150;

    public Sellen() {
        super("Sellen\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDE92", 's', SELLEN_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new WanderBehaviour());
    }

    @Override
    protected void initMonologues() {
        addMonologue("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        addMonologue("You sense it too, don’t you? The Glintstone hums, even now.");
    }
}