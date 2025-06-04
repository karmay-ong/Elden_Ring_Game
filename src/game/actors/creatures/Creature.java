package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.BehaviourSelector;
import game.behaviours.OrderedSelector;

import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for all creatures in the game.
 * Creatures are actors that follow a set of behaviors and can be cured.
 *
 * @author Kian Lok Chin
 * Edited by: Youssef Hassanein
 */
public abstract class Creature extends Actor {
    /**
     * Map of behaviors that determine the creature's actions during its turn
     * The integer key represents the priority of the behavior
     */
    protected Map<Integer, Behaviour> behaviours;

    /**
     * The strategy for selecting behaviors
     */
    protected BehaviourSelector selector;

    /**
     * Constructor for the Creature class with default ordered selector.
     *
     * @param name the name of the creature
     * @param displayChar the character that will represent the creature in the display
     * @param hitPoints the creature's starting hit points
     */
    public Creature(String name, char displayChar, int hitPoints) {
        this(name, displayChar, hitPoints, new OrderedSelector());
    }

    /**
     * Constructor for the Creature class with custom behavior selector.
     *
     * @param name the name of the creature
     * @param displayChar the character that will represent the creature in the display
     * @param hitPoints the creature's starting hit points
     * @param selector the strategy for selecting behaviors
     */
    public Creature(String name, char displayChar, int hitPoints, BehaviourSelector selector) {
        super(name, displayChar, hitPoints);
        this.behaviours = new TreeMap<>();
        this.selector = selector;
    }

    /**
     * Determines what action the creature will take during its turn.
     * Uses the behavior selector to choose an action from available behaviors.
     *
     * @param actions collection of possible actions
     * @param lastAction the action performed last turn
     * @param map the game map the creature is on
     * @param display the display where the creature is rendered
     * @return the action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action = selector.selectAction(this, map, behaviours);
        if (action != null) {
            return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a list of allowable actions that can be performed on this creature by another actor.
     * All creatures can be attacked.
     *
     * @param otherActor the actor performing actions on this creature
     * @param direction the direction in which the other actor is located
     * @param map the game map where both actors are
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, otherActor.getIntrinsicWeapon()));
        return actions;
    }
}