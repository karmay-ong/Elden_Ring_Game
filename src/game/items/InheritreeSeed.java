package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.grounds.Inheritree;
import game.grounds.Soil;

/**
 * A seed that can be planted to grow an Inheritree.
 * The Inheritree Seed is represented by '*' on the game map.
 * When planted, it transforms the ground into an Inheritree and purifies
 * any cursed ground in adjacent locations, turning it into soil.
 *
 * @author Kian Lok Chin
 */
public class InheritreeSeed extends PlantSeed {

    /**
     * Constructor for the InheritreeSeed class.
     * Initializes the seed with name "Inheritree Seed", display character '*',
     * as portable, and with energy cost 25.
     */
    public InheritreeSeed() {
        super("Inheritree Seed\uD83C\uDF31", '*', true, 25);
    }

    /**
     * Plants the seed at the specified location, transforming the ground into an Inheritree.
     * The seed is consumed in the process. Additionally, any cursed ground in adjacent
     * locations is purified and transformed into soil.
     *
     * @param actor the actor planting the seed
     * @param location the location where the seed is being planted
     * @param map the game map containing the location
     * @return a string describing the planting action
     */
    @Override
    public String plant(Actor actor, Location location, GameMap map) {
        location.removeItem(this);
        Inheritree inheritree = new Inheritree();
        location.setGround(inheritree);

        // Purify adjacent cursed ground by turning it into soil
        for (Exit exit : location.getExits()) {
            if(exit.getDestination().getGround().hasCapability(Status.CURSED)){
                exit.getDestination().setGround(new Soil());
            }
        }

        return actor + " planted a " + inheritree;
    }
}
