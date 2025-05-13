package game.actors.creatures;

import edu.monash.fit2099.engine.displays.Display;
import game.behaviours.WanderBehaviour;
import game.behaviours.FollowBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EatAction;
import game.actors.Producible;
import game.actors.Status;
import game.behaviours.ProduceBehaviour;
import game.items.Eatable;
import game.items.GoldenEgg;

import java.util.TreeMap;

/**
 * Golden Beetle: 25 HP, every 5 turns lays a GoldenEgg; follows Farmer;
 * immune to Crimson Rot; can be consumed by Farmer in adjacency.
 *
 * @author Kar May Ong
 */
public class GoldenBeetle extends Creature implements Eatable, Producible {
    public static final int GOLDEN_BEETLE_HITPOINTS = 25;
    public static final int HEALTH_INCREASE_AFTER_EATEN = 50;
    public static final int BALANCE_INCREASE_AFTER_EATEN = 1000;
    private Actor followedActor;
    private int eggTimer = 0;
    public static final int EGG_TIMER_THRESHOLD = 5;

    /**
     * Constructor for the Golden Beetle.
     * Initialise GoldenBeetle with ProduceBehaviour and WanderBehaviour with different priority key.
     */
    public GoldenBeetle() {
        super("Golden Beetle\uD83E\uDEB2", 'b', GOLDEN_BEETLE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new ProduceBehaviour(this));
        behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Follow the actor if the actor is Followable.
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
     * Determines if GoldenBeetle can produce an egg.
     *
     * @param producer  the actor performing the producing behaviour
     * @param map       the map actor is on
     * @return          true if GoldenBeetle can produce, false otherwise
     */
    @Override
    public boolean canProduce(Actor producer, GameMap map) {
        eggTimer += 1;
        if (eggTimer >= EGG_TIMER_THRESHOLD) {
            eggTimer = 0;
            return true;
        }
        return false;
    }

    /**
     * Produces an egg on the GoldenBeetle is standing.
     *
     * @param producer  the actor performing the behaviour
     * @param map       the map actor is on
     */
    @Override
    public void produce(Actor producer, GameMap map) {
        map.locationOf(this).addItem(new GoldenEgg());
    }

    /**
     * Increase the actor's runes and health after consuming GoldenBeetle.
     *
     * @param actor    the actor eating GoldenBeetle
     * @param map      the map actor is on
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        actor.addBalance(BALANCE_INCREASE_AFTER_EATEN);
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,HEALTH_INCREASE_AFTER_EATEN);
        new Display().println("Farmer's health is increased by " + HEALTH_INCREASE_AFTER_EATEN);
        unconscious(map);
    }

    /**
     * Returns a list of allowable actions that can be performed on GoldenBeetle by another actor.
     * GoldenBeetle can follow an actor if the actor is present and followable.
     * All GoldenBeetle can be eaten by otherActor.
     *
     * @param otherActor the actor performing actions on this creature
     * @param direction  the direction in which the other actor is located
     * @param map        the game map where both actors are
     * @return  a list of allowableActions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (followedActor == null && otherActor.hasCapability(Status.FOLLOWABLE)) {
            startFollowing(otherActor);
        }
        actions.add(new EatAction(otherActor, this));
        return actions;
    }
}