package game.actors.creatures;

import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actions.ProduceAction;
import game.actors.Ability;
import game.actors.Curable;
import game.actors.Producible;
import game.actors.Status;
import game.behaviours.ProduceBehaviour;
import game.behaviours.RottingBehaviour;
import game.utils.AdjacentCapabilityChecker;

/**
 * A special creature that rots over time and can be cured to reset its timer.
 * The Spirit Goat is represented by 'y' on the game map.
 */
public class SpiritGoat extends Creature implements Producible, Curable {

    /**
     * Countdown timer for the rotting process, measured in turns
     */
    private int countdownTimer = 10;
    public static final int SPIRIT_GOAT_HITPOINTS = 50;


    public SpiritGoat() {
        super("Spirit Goat\uD83D\uDC10", 'y', SPIRIT_GOAT_HITPOINTS);
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new ProduceBehaviour(this));
        this.behaviours.put(3, new WanderBehaviour());
    }

    @Override
    public void cure(Actor actor, GameMap map, Item cureItem) {
        countdownTimer = 10;
    }

    @Override
    public boolean canProduce(Actor producer, GameMap map) {
        Location here = map.locationOf(this);
        return AdjacentCapabilityChecker.hasAdjacentCapability(here, Status.BLESSED);
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

    @Override
    public void produce(Actor producer, GameMap map) {
        Location here = map.locationOf(this);
        SpiritGoat child = new SpiritGoat();
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(child)) {
                dest.addActor(child);
                break;
            }
        }
    }
}
