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
import game.actors.Monologue;
import game.conditions.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * An abstract Actor that can engage in conversation by providing monologues.

 * Stores a list of Monologue objects, each with an associated Condition that
 * determines its eligibility. Allows other actors to listen and initiates
 * behaviours when not engaged in conversation.
 *
 * @author Lim Chi Jian
 */
public abstract class ConversationalActor extends Actor {
    private List<Monologue> monologues = new ArrayList<>();
    private Random rand = new Random();

    protected Map<Integer, Behaviour> behaviours;

    /**
     * Constructs a new ConversationalActor with the specified name, display character,
     * and hit points, then initializes its monologues.
     *
     * @param name        the name of this actor
     * @param displayChar the character representing this actor in the display
     * @param hitPoints   the starting hit points of this actor
     */
    public ConversationalActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Abstract method for initializing this actor's monologues.
     * Implementations should use addMonologue(String) or
     * addMonologue(String, Condition) to populate the monologues list.
     *
     * @param listener the actor that will be listening to this conversational actor
     */
    protected abstract void initMonologues(Actor listener);

    /**
     * Adds a monologue with no condition (always eligible).
     *
     * @param text the text of the monologue
     */
    protected void addMonologue(String text) {
        monologues.add(new Monologue(text, Condition.ALWAYS));
    }

    /**
     * Adds a monologue that is only eligible when the specified condition holds.
     *
     * @param text      the text of the monologue
     * @param condition the condition under which this monologue is eligible
     */
    protected void addMonologue(String text, Condition condition) {
        monologues.add(new Monologue(text, condition));
    }

    /**
     * Selects a random eligible monologue based on the listener and game map context.
     *
     * @param listener the actor listening to this conversational actor
     * @param map      the game map where the conversation occurs
     * @return a random eligible monologue text, or a default message if none are available
     */
    public String getRandomMonologue(Actor listener, GameMap map) {
        List<Monologue> eligible = new ArrayList<>();
        for (Monologue m : monologues) {
            if (m.isEligible(listener, map, this)) {
                eligible.add(m);
            }
        }
        if (eligible.isEmpty()) {
            return "";
        }
        return eligible.get(rand.nextInt(eligible.size())).getText();
    }

    /**
     * Returns the list of actions that another actor can perform on this conversational actor,
     * including listening and attacking.
     * Initializes monologues if they haven't been initialized yet.
     *
     * @param otherActor the actor interacting with this conversational actor
     * @param direction  the direction of the other actor relative to this actor
     * @param map        the game map
     * @return the ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (monologues.isEmpty()){
            initMonologues(otherActor);
        }
        actions.add(new ListenAction(this, direction));
        actions.add(new AttackAction(this, otherActor.getIntrinsicWeapon()));
        return actions;
    }

    /**
     * Chooses and returns the next action for this actor, based on its behaviours.
     * Defaults to doing nothing if no behaviour returns a non-null action.
     *
     * @param actions    the list of actions currently available (ignored)
     * @param lastAction the last action performed by this actor (ignored)
     * @param map        the game map
     * @param display    the display interface
     * @return the chosen Action, or a DoNothingAction if no behaviours apply
     */
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