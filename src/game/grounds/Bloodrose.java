package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A dangerous plant that damages actors in adjacent locations.
 * The Bloodrose is represented by 'w' on the game map and hurts any
 * actor standing next to it by 10 hit points each turn.
 *
 * @author Kian Lok Chin
 */
public class Bloodrose extends Ground {

    /**
     * Constructor for the Bloodrose class.
     * Initializes the bloodrose with a display character 'w'.
     */
    public Bloodrose() {
        super('w', "Bloodrose\uD83E\uDD40");
    }

    /**
     * Performs the Bloodrose's action each turn, damaging any actors
     * in adjacent locations.
     *
     * Each adjacent actor takes 10 points of damage per turn.
     * A message is printed to the console when an actor is damaged.
     *
     * @param location the location of the Bloodrose
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // Check all surrounding locations for actors
        for (Exit exit : location.getExits()) {
            Location surroundings = exit.getDestination();
            Actor actor = surroundings.getActor();
            if (actor != null) {
                System.out.println("\uD83D\uDC94" + actor + " health is deducted by 10\uD83D\uDC94");
                actor.hurt(10);
                //check death of surrounding actors
                if(!actor.isConscious()){
                    System.out.println(actor.unconscious(location.map()));
                }
            }
        }
    }
}
