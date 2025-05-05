package game.actors.creatures;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Curable;
import game.actors.Producible;
import game.behaviours.ProduceBehaviour;
import game.behaviours.RottingBehaviour;
import game.grounds.Inheritree;


/**
 * A special creature that rots over time and transforms surrounding ground when cured.
 * The Omen Sheep is represented by 'm' on the game map.
 *
 * @author Kian Lok Chin
 */
public class OmenSheep extends Creature implements Producible, Curable {

    /**
     * Countdown timer for the rotting process, measured in turns
     */

    public static final int OMEN_SHEEP_HITPOINTS = 50;
    private int countdownTimer = 15;
    private int eggTimer = 0;
    public static final int EGG_TIMER_THRESHOLD = 7;

    /**
     * Constructor for the OmenSheep.
     * Initializes the sheep with a wander behavior.
     */
    public OmenSheep() {
        super("Omen Sheep\uD83D\uDC11", 'm', OMEN_SHEEP_HITPOINTS);
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new ProduceBehaviour(this));
        this.behaviours.put(3, new WanderBehaviour());

    }

    /**
     * Cures the OmenSheep and transforms all adjacent locations into Inheritrees.
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

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions =  super.allowableActions(otherActor, direction, map);
        Item cureItem;
        for(Item item : otherActor.getItemInventory()){
            if (item.hasCapability(Ability.CURE)){
                cureItem = item;
                actions.add(new CureAction(this, cureItem, 0));
            }
        }
        return actions;
    }

}