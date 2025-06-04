package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.UseTorchAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.GrowBehaviour;
import game.bossComponents.Branch;
import game.bossComponents.Growable;
import game.bossComponents.Leaf;
import game.conditions.Condition;
import game.weapons.Claw;

import java.util.*;

/**
 * Represents the Bed of Chaos actor, which is growable and has multiple behaviours.
 *
 * <p>The Bed of Chaos maintains a collection of behaviours prioritized by keys.
 * It can grow components such as Branches or Leaves, each contributing to its damage.
 * It also provides intrinsic weapons based on its current damage points.</p>
 *
 * @author Kar May Ong
 */
public class BedOfChaos extends Actor implements Growable {
    /**
     * Priority-ordered behaviours for this actor.
     */
    protected Map<Integer, Behaviour> behaviours;

    /**
     * Components (growable parts) attached to the Bed of Chaos.
     */
    private final List<Growable> components = new ArrayList<>();

    /**
     * Initial hitpoints for the Bed of Chaos.
     */
    public static int BED_OF_CHAOS_HITPOINTS = 1000;

    /**
     * Priority key for the AttackBehaviour in the behaviours map.
     * Lower numbers indicate higher priority.
     */
    private static final int ATTACK_BEHAVIOUR_PRIORITY = 1;

    /**
     * Priority key for the GrowBehaviour in the behaviours map.
     * Higher numbers indicate lower priority.
     */
    private static final int GROW_BEHAVIOUR_PRIORITY = 10;

    private Random rand = new Random();

    /**
     * Constructs a new Bed of Chaos actor with initial behaviours.
     * Sets the name, display character, and hitpoints.
     */
    public BedOfChaos() {
        super("Bed of Chaos\uD83D\uDE08\uD83C\uDF33", 'T', BED_OF_CHAOS_HITPOINTS);
        behaviours = new TreeMap<>();
        this.behaviours.put(ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour(Condition.ALWAYS));
        this.behaviours.put(GROW_BEHAVIOUR_PRIORITY, new GrowBehaviour(this));
    }

    /**
     * Executes the next action for this Bed of Chaos based on its behaviours.
     * Behaviours are checked in order of their priority, and the first available action is executed.
     *
     * @param actions    the list of possible actions available
     * @param lastAction the last action performed by this actor
     * @param map        the map the actor is on
     * @param display    the display to print messages
     * @return the chosen {@link Action}, or {@link DoNothingAction} if none available
     */
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
     * Returns the intrinsic weapon used by the Bed of Chaos.
     * The weapon damage is based on the current total damage points.
     *
     * @return the intrinsic {@link IntrinsicWeapon}
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        IntrinsicWeapon weapon = new Claw(this.getDamagePoint());
        this.setIntrinsicWeapon(weapon);
        return weapon;
    }

    /**
     * Causes the Bed of Chaos to grow a new component.
     * The new component is randomly chosen to be either a {@link Branch} or a {@link Leaf}.
     * The new component is added to the components list.
     *
     * @param actor the actor causing the growth
     * @return a message describing the growth process
     */
    public String grow(Actor actor) {
        String result = actor + " is growing...\n";

        Growable newComponent;
        if (rand.nextBoolean()) {
            newComponent = new Branch();
        } else {
            newComponent = new Leaf();
        }
        components.add(newComponent);
        result += " it grows a " + newComponent + "\n" + newComponent.grow(actor);
        return result;
    }

    /**
     * Calculates the total damage points of the Bed of Chaos.
     * This includes base damage plus damage from all grown components.
     *
     * @return the total damage points
     */
    @Override
    public int getDamagePoint() {
        int result = 25;
        for (Growable component : components) {
            result += component.getDamagePoint();
        }
        return result;
    }


    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, otherActor.getIntrinsicWeapon()));
        return actions;
    }
}

