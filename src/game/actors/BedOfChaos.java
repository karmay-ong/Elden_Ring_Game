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
import game.behaviours.AttackBehaviour;
import game.behaviours.BehaviourSelector;
import game.behaviours.GrowBehaviour;
import game.behaviours.OrderedSelector;
import game.bosscomponents.Branch;
import game.bosscomponents.Growable;
import game.bosscomponents.Leaf;
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

    /**
     * Selector to decide which behaviour to perform.
     */
    private BehaviourSelector selector;

    private Random rand = new Random();

    /**
     * Constructs a new Bed of Chaos actor with initial behaviours.
     * The behaviour selection is by ordered.
     * Sets the name, display character, and hitpoints.
     */
    public BedOfChaos() {
        super("Bed of Chaos\uD83D\uDE08\uD83C\uDF33", 'T', BED_OF_CHAOS_HITPOINTS);
        this.selector = new OrderedSelector();
        behaviours = new TreeMap<>();
        this.behaviours.put(ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour(Condition.ALWAYS));
        this.behaviours.put(GROW_BEHAVIOUR_PRIORITY, new GrowBehaviour(this));
    }

    /**
     * Constructs a new Bed of Chaos actor with initial behaviours.
     * The behaviour selection method can be defined.
     * Sets the name, display character, and hitpoints.
     */
    public BedOfChaos(BehaviourSelector selector) {
        super("Bed of Chaos\uD83D\uDE08\uD83C\uDF33", 'T', BED_OF_CHAOS_HITPOINTS);
        this.selector = selector;
        behaviours = new TreeMap<>();
        this.behaviours.put(ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour(Condition.ALWAYS));
        this.behaviours.put(GROW_BEHAVIOUR_PRIORITY, new GrowBehaviour(this));
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

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (selector == null) {
            selector = new OrderedSelector();
        }
        Action action = selector.selectAction(this, map, behaviours);
        if (action != null) {
            return action;
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
        result += " it grows a " + newComponent + "\n" + newComponent.grow(actor);

        for (Growable component: components) {
            result += "\n" + component.grow(actor);
        }

        components.add(newComponent);
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

