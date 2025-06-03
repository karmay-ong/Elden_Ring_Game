package game.actors.creatures;

import game.behaviours.WanderBehaviour;
import game.behaviours.FollowBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EatAction;
import game.conditions.AdjacentCapabilityCondition;
import game.actors.Producible;
import game.actors.Status;
import game.behaviours.ProduceBehaviour;
import game.conditions.Condition;
import game.conditions.TurnBasedCondition;
import game.effects.Effect;
import game.effects.RestoreStaminaEffect;
import game.items.Eatable;
import game.items.Egg;
import game.items.GoldenBeetleMeat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Golden Beetle is a unique creature that produces golden eggs, follows actors with FOLLOWABLE capability,
 * and can be consumed by other actors to provide health and balance benefits.
 * It has 25 HP and lays eggs every 5 turns by default.
 *
 * @author Kar May Ong
 */
public class GoldenBeetle extends Creature implements Eatable, Producible {
    /**
     * Default hit points for the Golden Beetle
     */
    private static final int GOLDEN_BEETLE_HITPOINTS = 25;

    /**
     * Reference to the actor this beetle is following
     */
    private Actor followedActor;

    /**
     * Condition that determines when the beetle produces eggs
     */
    private Condition produceCondition;

    /**
     * Number of turns between egg production
     */
    public static final int EGG_TIMER_THRESHOLD = 5;

    /**
     * Effects to apply when the beetle is consumed
     */
    private List<Effect> consumptionEffects;

    /**
     * Default constructor for the Golden Beetle.
     * Initializes with empty consumption effects and a turn-based production condition (every 5 turns).
     * Sets up production and wandering behaviors.
     */
    public GoldenBeetle() {
        super("Golden Beetle\uD83E\uDEB2", 'b', GOLDEN_BEETLE_HITPOINTS);
        // Initialize consumption effects
        this.consumptionEffects = new ArrayList<>();
        // Default to a turn-based production condition
        this.produceCondition = new TurnBasedCondition(EGG_TIMER_THRESHOLD);
        behaviours = new TreeMap<>();
        behaviours.put(1, new ProduceBehaviour(this, produceCondition));
        behaviours.put(999, new WanderBehaviour());

    }

    /**
     * Constructor for the Golden Beetle with custom consumption effects and produce condition.
     * Sets up production and wandering behaviors with the specified parameters.
     *
     * @param consumptionEffects effects to apply when beetle is consumed
     * @param produceCondition condition that determines when the beetle should produce eggs
     */
    public GoldenBeetle(List<Effect> consumptionEffects, Condition produceCondition) {
        super("Golden Beetle\uD83E\uDEB2", 'b', GOLDEN_BEETLE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new ProduceBehaviour(this,produceCondition));
        behaviours.put(999, new WanderBehaviour());

        // Initialize consumption effects
        this.consumptionEffects = new ArrayList<>();
        if (consumptionEffects != null) {
            this.consumptionEffects.addAll(consumptionEffects);
        }
        this.produceCondition = produceCondition;
    }

    /**
     * Sets up follow behavior if the specified actor has the FOLLOWABLE capability.
     * Stores reference to the followed actor and adds a FollowBehaviour to the beetle's behaviors.
     *
     * @param toFollow the actor to follow
     */
    private void startFollowing(Actor toFollow) {
        if (toFollow != null && toFollow.hasCapability(Status.FOLLOWABLE)) {
            followedActor = toFollow;
            behaviours.put(2, new FollowBehaviour(toFollow));
        }
    }

    /**
     * Produces a Golden Egg at the beetle's current location on the map.
     * The egg hatches when adjacent to an entity with the CURSED capability,
     * provides stamina restoration when consumed, and hatches into a new Golden Beetle.
     *
     * @param producer the actor performing the production (the Golden Beetle)
     * @param map the game map where the beetle is located
     */
    @Override
    public void produce(Actor producer, GameMap map) {
        // Create a new golden egg with an AdjacentCapabilityCondition for hatching
        List<Effect> eggEffects = new ArrayList<>();
        eggEffects.add(new RestoreStaminaEffect(20));
        Condition eggHatchCondition = new AdjacentCapabilityCondition(Status.CURSED);

        // Create a new Golden Beetle for the hatchling
        GoldenBeetle hatchling = new GoldenBeetle(consumptionEffects, produceCondition);
        Egg goldenEgg = new Egg("Golden Egg\uD83D\uDFE1", eggHatchCondition, hatchling, eggEffects);
        // Add the egg to the map
        map.locationOf(this).addItem(goldenEgg);
    }

    /**
     * Handles what happens when the Golden Beetle is eaten by another actor.
     * Increases the actor's balance by 1000, health by 50, and applies any custom consumption effects.
     * The beetle is then removed from the map.
     *
     * @param actor the actor eating the Golden Beetle
     * @param map the game map where both actors are located
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        // Apply all consumption effects
        for (Effect effect : consumptionEffects) {
            effect.apply(actor, map);
        }
        unconscious(map);
    }

    /**
     * Returns allowable actions that can be performed on this Golden Beetle.
     * If not already following someone and the other actor has the FOLLOWABLE capability,
     * the beetle will start following them.
     * Always adds an EatAction allowing the other actor to consume this beetle.
     *
     * @param otherActor the actor performing actions on this creature
     * @param direction the direction in which the other actor is located
     * @param map the game map where both actors are
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (followedActor == null && otherActor.hasCapability(Status.FOLLOWABLE)) {
            startFollowing(otherActor);
        }
        actions.add(new EatAction(this));
        return actions;
    }

    /**
     * Handles what happens when the GoldenBeetle becomes unconscious due to combat.
     * When the GoldenBeetle is defeated by another actor, it drops GoldenBeetleMeat
     * at its current location before being removed from the game.
     *
     * @param actor the actor that caused this GoldenBeetle to become unconscious
     * @param map the game map where the GoldenBeetle is located
     * @return a string description of what happened when the GoldenBeetle became unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Drop meat when killed by another actor
        map.locationOf(this).addItem(new GoldenBeetleMeat());
        return super.unconscious(actor, map);
    }

    /**
     * Handles what happens when the GoldenBeetle becomes unconscious due to non-combat reasons.
     * This method is called when the GoldenBeetle becomes unconscious without being directly
     * defeated by another actor (e.g., environmental effects, status conditions).
     * The GoldenBeetle still drops GoldenBeetleMeat at its location before being removed.
     *
     * @param map the game map where the GoldenBeetle is located
     * @return a string description of what happened when the GoldenBeetle became unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        // Drop meat when killed by non-combat means
        map.locationOf(this).addItem(new GoldenBeetleMeat());
        return super.unconscious(map);
    }

}
