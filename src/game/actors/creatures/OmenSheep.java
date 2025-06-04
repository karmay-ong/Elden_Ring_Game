package game.actors.creatures;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Curable;
import game.actors.Producible;
import game.behaviours.BehaviourSelector;
import game.behaviours.OrderedSelector;
import game.behaviours.ProduceBehaviour;
import game.behaviours.RandomSelector;
import game.behaviours.RottingBehaviour;
import game.conditions.Condition;
import game.conditions.TurnBasedCondition;
import game.effects.Effect;
import game.effects.IncreaseMaxHealthEffect;
import game.grounds.Inheritree;
import game.items.Egg;
import game.items.OmenSheepMeat;

import java.util.ArrayList;
import java.util.List;

/**
 * A special creature that rots over time, produces eggs, and transforms surrounding ground into Inheritrees when cured.
 * The Omen Sheep is represented by 'm' on the game map and has rotting, producing, and wandering behaviors.
 *
 * @author Kian Lok Chin
 * Modified By Pemudi Hiruni Halgahawatta Liyanaarachchi, Youssef Hassanein
 */
public class OmenSheep extends Creature implements Producible, Curable {

    /**
     * Default hitpoints for Omen Sheep
     */
    private static final int OMEN_SHEEP_HITPOINTS = 50;

    /**
     * Countdown timer for the rotting process, measured in turns
     */
    private int countdownTimer = 15;

    /**
     * Default threshold in turns for egg production and hatching
     */
    public static final int EGG_TIMER_THRESHOLD = 7;

    /**
     * Condition that determines when the sheep should produce eggs
     */
    private Condition produceCondition;

    /**
     * Constructor for the OmenSheep with a custom produce condition and behavior selector.
     *
     * @param produceCondition the condition that determines when the sheep should produce eggs
     * @param selector the strategy for selecting behaviors
     */
    public OmenSheep(Condition produceCondition, BehaviourSelector selector) {
        super("Omen Sheep\uD83D\uDC11", 'm', OMEN_SHEEP_HITPOINTS, selector);
        this.produceCondition = produceCondition;
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new ProduceBehaviour(this, produceCondition));
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Constructor for the OmenSheep with a custom produce condition and default ordered selector.
     *
     * @param produceCondition the condition that determines when the sheep should produce eggs
     */
    public OmenSheep(Condition produceCondition) {
        this(produceCondition, new OrderedSelector());
    }

    /**
     * Default constructor for OmenSheep with ordered selector.
     * Sets up a default turn-based production condition.
     */
    public OmenSheep() {
        this(new TurnBasedCondition(EGG_TIMER_THRESHOLD), new OrderedSelector());
    }

    /**
     * Creates an OmenSheep with random behavior selection.
     *
     * @return a new OmenSheep instance that uses random behavior selection
     */
    public static OmenSheep createRandomBehaviorSheep() {
        return new OmenSheep(new TurnBasedCondition(EGG_TIMER_THRESHOLD), new RandomSelector());
    }

    /**
     * Cures the OmenSheep and transforms all adjacent locations into Inheritrees.
     * Called when the OmenSheep is cured by an item with the CURE capability.
     *
     * @param actor the actor performing the cure
     * @param map the game map where the OmenSheep is located
     * @param cureItem the item used to cure the OmenSheep
     */
    @Override
    public void cure(Actor actor, GameMap map, Item cureItem) {
        for (Exit exit : map.locationOf(this).getExits()) {
            exit.getDestination().setGround(new Inheritree());
        }
    }

    /**
     * Determines the actions another actor can perform on this OmenSheep.
     * Adds a CureAction if the actor has an item with the CURE capability.
     *
     * @param otherActor the actor interacting with the OmenSheep
     * @param direction  the direction of the OmenSheep relative to the actor
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
     * Spawns an Omen Sheep Egg at the OmenSheep's current location on the map.
     * The egg contains health-increasing effects when consumed and will hatch
     * into another OmenSheep after a set number of turns.
     *
     * @param producer the actor performing the production (usually the OmenSheep itself)
     * @param map      the current game map
     */
    @Override
    public void produce(Actor producer, GameMap map) {
        List<Effect> eatEggEffects = new ArrayList<>();
        eatEggEffects.add(new IncreaseMaxHealthEffect(10));
        OmenSheep hatchling = new OmenSheep(produceCondition);
        Egg omenSheepEgg = new Egg("Omen Sheep Egg\uD83E\uDD5A", new TurnBasedCondition(3), hatchling, eatEggEffects);
        map.locationOf(producer).addItem(omenSheepEgg);
    }

    // Add to OmenSheep class

    /**
     * Handles what happens when the OmenSheep becomes unconscious.
     * Drops OmenSheepMeat when killed.
     *
     * @param actor the actor that killed this OmenSheep
     * @param map the map where the OmenSheep is
     * @return description of what happened
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Drop meat when killed
        map.locationOf(this).addItem(new OmenSheepMeat());
        return super.unconscious(actor, map);
    }

    @Override
    public String unconscious(GameMap map) {
        map.locationOf(this).addItem(new OmenSheepMeat());
        return super.unconscious(map);
    }
}
