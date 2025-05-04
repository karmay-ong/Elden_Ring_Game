package game.actors.creatures;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.RottingBehaviour;
import game.grounds.Inheritree;

/**
 * A special creature that rots over time and transforms surrounding ground when cured.
 * The Omen Sheep is represented by 'm' on the game map.
 *
 * @author Kian Lok Chin
 */
public class OmenSheep extends Creature {

    /**
     * Countdown timer for the rotting process, measured in turns
     */
    private int countdownTimer = 15;

    /**
     * Constructor for the OmenSheep.
     * Initializes the sheep with a wander behavior.
     */
    public OmenSheep() {
        super("Omen Sheep\uD83D\uDC11", 'm', 75);
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new WanderBehaviour());
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

}