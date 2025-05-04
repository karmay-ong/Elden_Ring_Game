package game.actors.creatures;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.RottingBehaviour;

/**
 * A special creature that rots over time and can be cured to reset its timer.
 * The Spirit Goat is represented by 'y' on the game map.
 *
 * @author Kian Lok Chin
 */
public class SpiritGoat extends Creature{

    /**
     * Countdown timer for the rotting process, measured in turns
     */
    private int countdownTimer = 10;

    /**
     * Constructor for the SpiritGoat.
     * Initializes the goat with a wander behavior.
     */
    public SpiritGoat() {
        super("Spirit Goat\uD83D\uDC10", 'y', 50);
        this.behaviours.put(1, new RottingBehaviour(countdownTimer));
        this.behaviours.put(2, new WanderBehaviour());
    }


    /**
     * Cures the SpiritGoat by resetting its countdown timer.
     * This prevents the goat from rotting for another 10 turns.
     *
     * @param actor the actor performing the cure
     * @param map the game map where the SpiritGoat is located
     * @param cureItem the item used to cure the SpiritGoat
     */
    @Override
    public void cure(Actor actor, GameMap map, Item cureItem) {
        countdownTimer = 10;
    }
}
