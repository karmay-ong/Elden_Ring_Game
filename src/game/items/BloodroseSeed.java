package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Bloodrose;

/**
 * A seed that can be planted to grow a Bloodrose plant.
 * The Bloodrose Seed is represented by '*' on the game map.
 * When planted, it transforms the ground into a Bloodrose and causes
 * minor harm to the planter.
 *
 * @author Kian Lok Chin
 */
public class BloodroseSeed extends PlantSeed {

    /**
     * Constructor for the BloodroseSeed class.
     * Initializes the seed with name "Bloodrose Seed", display character '*',
     * as portable, and with energy cost 75.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed\uD83C\uDF31", '*', true, 75);
    }

    /**
     * Plants the seed at the specified location, transforming the ground into a Bloodrose.
     * The seed is consumed in the process, and the planting actor takes 5 points of damage
     * due to the dangerous nature of the plant.
     *
     * @param actor the actor planting the seed
     * @param location the location where the seed is being planted
     * @param map the game map containing the location
     * @return a string describing the planting action
     */
    @Override
    public String plant(Actor actor, Location location, GameMap map) {
        location.removeItem(this);
        Bloodrose bloodrose = new Bloodrose();
        location.setGround(bloodrose);
        actor.hurt(5);
        //check death of surrounding actors
        if(!actor.isConscious()) {
            System.out.println(actor.unconscious(location.map()));
        }
        return actor + " planted a " + bloodrose;
    }
}
