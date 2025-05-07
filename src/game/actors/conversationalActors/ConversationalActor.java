package game.actors.conversationalActors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.ListenAction;
import game.actors.Condition;
import game.actors.Monologue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * An Actor that can be listened to by other Actors.
 */
public abstract class ConversationalActor extends Actor {
    private List<Monologue> monologues = new ArrayList<>();
    private Random rand = new Random();

    protected Map<Integer, Behaviour> behaviours;


    public ConversationalActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        initMonologues();
    }

    protected abstract void initMonologues();

    protected void addMonologue(String text) {
        monologues.add(new Monologue(text, Condition.ALWAYS));
    }


    protected void addMonologue(String text, Condition condition) {
        monologues.add(new Monologue(text, condition));
    }

    public String getRandomMonologue(Actor listener, GameMap map) {
        List<Monologue> eligible = new ArrayList<>();
        for (Monologue m : monologues) {
            if (m.isEligible(listener, map, this)) {
                eligible.add(m);
            }
        }
        if (eligible.isEmpty()) {
            return "The actor is speechless. Just like how I am suffering from Monash";
        }
        return eligible.get(rand.nextInt(eligible.size())).getText();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ListenAction(this, direction));
        actions.add(new AttackAction(this, otherActor.getIntrinsicWeapon()));
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }
}

