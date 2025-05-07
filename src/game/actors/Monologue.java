package game.actors;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class Monologue {
    private String text;
    private Condition condition;

    public Monologue(String text, Condition condition) {
        this.text = text;
        this.condition = condition;
    }
    public boolean isEligible(Actor listener, GameMap map, Actor speaker) {
        return condition.test(listener, map, speaker);
    }
    public String getText() {
        return text;
    }
}