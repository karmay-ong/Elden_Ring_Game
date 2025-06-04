package game.actors.creatures;

import game.actors.Status;
import game.behaviours.BehaviourSelector;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Curable;
import game.actors.Producible;
import game.behaviours.ProduceBehaviour;
import game.behaviours.RottingBehaviour;
import game.conditions.Condition;
import game.items.Meat;

/**
 * A special creature that rots over time, can be cured to reset its rotting timer,
 * and reproduces by spawning offspring in adjacent locations.
 * The Spirit Goat is represented by 'y' on the game map.
 *
 * @author Kian Lok Chin
 * @author YOUSSEF HASSANEIN
 * Modified by Pemudi Hiruni Halgahawatta Liyanaarachchi
 */
public class SpiritGoat extends Creature implements Producible, Curable {

    /**
     * Countdown timer for the rotting process, measured in turns
     */
    private int countdownTimer = 10;

    /**
     * Default hit points for Spirit Goat
     */
    private static final int SPIRIT_GOAT_HITPOINTS = 50;

    /**
     * Condition that determines when the goat should produce offspring
     */
    private Condition produceCondition;

    /**
     * Constructor for the SpiritGoat with a custom produce condition.
     * Sets up rotting, production, and wandering behaviors.
     *
     * @param produceCondition the condition that determines when the goat should produce offspring
     */
    public SpiritGoat(Condition produceCondition, BehaviourSelector selector) {
        super("Spirit Goat\uD83D\uDC10", 'y', SPIRIT_GOAT_HITPOINTS);
        this.produceCondition = produceCondition;
        this.selector = selector;
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new ProduceBehaviour(this, produceCondition));
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Cures the SpiritGoat by resetting its rotting countdown timer to 10 turns.
     *
     * @param actor the actor performing the cure
     * @param map the game map where the SpiritGoat is located
     * @param cureItem the item used to cure the SpiritGoat
     */
    @Override
    public void cure(Actor actor, GameMap map, Item cureItem) {
        countdownTimer = 10;
    }

    /**
     * Determines the actions another actor can perform on this SpiritGoat.
     * Adds a CureAction if the actor has an item with the CURE capability.
     *
     * @param otherActor the actor interacting with the SpiritGoat
     * @param direction  the direction of the SpiritGoat relative to the actor
     * @param map        the game map
     * @return an ActionList of allowable actions, including CureAction if applicable
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        Item cureItem;
        for(Item item : otherActor.getItemInventory()){
            if (item.hasCapability(Ability.CURE)){
                cureItem = item;
                actions.add(new CureAction(this, cureItem, 0));
            }
        }
        return actions;
    }

    /**
     * Spawns a new Spirit Goat in a random adjacent, unoccupied, and accessible tile.
     * The new goat will have the same production condition as the parent.
     * If no suitable adjacent location is found, no offspring is produced.
     *
     * @param producer the actor initiating the production (the parent SpiritGoat)
     * @param map      the game map
     */
    @Override
    public void produce(Actor producer, GameMap map) {
        Location here = map.locationOf(producer);
        Creature child = new SpiritGoat(produceCondition, selector);
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(child)) {
                dest.addActor(child);
                break;
            }
        }
    }

    /**
     * Handles what happens when the SpiritGoat becomes unconscious due to combat.
     * When the SpiritGoat is defeated by another actor, it drops SpiritGoatMeat
     * at its current location before being removed from the game.
     *
     * @param actor the actor that caused this SpiritGoat to become unconscious
     * @param map the game map where the SpiritGoat is located
     * @return a string description of what happened when the SpiritGoat became unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Drop meat when killed by another actor
        map.locationOf(this).addItem(new Meat("Spirit Goat Meat", Status.BLESSED));
        return super.unconscious(actor, map);
    }

    /**
     * Handles what happens when the SpiritGoat becomes unconscious due to non-combat reasons.
     * This method is called when the SpiritGoat becomes unconscious without being directly
     * defeated by another actor (e.g., environmental effects, status conditions).
     * The SpiritGoat still drops SpiritGoatMeat at its location before being removed.
     *
     * @param map the game map where the SpiritGoat is located
     * @return a string description of what happened when the SpiritGoat became unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        // Drop meat when killed by non-combat means
        map.locationOf(this).addItem(new Meat("Spirit Goat Meat", Status.BLESSED));
        return super.unconscious(map);
    }
}
