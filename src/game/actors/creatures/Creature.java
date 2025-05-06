package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for all creatures in the game.
 * Creatures are actors that follow a set of behaviors and can be cured.
 *
 * @author Kian Lok Chin
 */
public abstract class Creature extends Actor{
    /**
     * Map of behaviors that determine the creature's actions during its turn
     * The integer key represents the priority of the behavior
     */
    protected Map<Integer, Behaviour> behaviours;

    /**
     * Constructor for the Creature class.
     *
     * @param name the name of the creature
     * @param displayChar the character that will represent the creature in the display
     * @param hitPoints the creature's starting hit points
     */
    public Creature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours = new TreeMap<>();
    }

    /**
     * Determines what action the creature will take during its turn.
     * Iterates through the creature's behaviors in priority order until one returns a valid action.
     * If no behavior returns an action, the creature does nothing.
     *
     * @param actions collection of possible actions
     * @param lastAction the action performed last turn
     * @param map the game map the creature is on
     * @param display the display where the creature is rendered
     * @return the action to be performed
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

    /**
     * Returns a list of allowable actions that can be performed on this creature by another actor.
     * All creatures can be attacked. If the other actor has items with the CURE ability,
     * they can also cure this creature.
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