package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface for grounds that can be planted by actors
 *
 * @author Kian Lok Chin
 */
public interface Plantable {
    /**
     * Plants this ground at the specified location
     *
     * @param actor The actor planting the ground
     * @param location The location where the ground will be planted
     * @param map The game map
     * @return A string describing the result of the planting action
     */
    String plant(Actor actor, Location location, GameMap map);

    /**
     * Returns the energy required to plant this ground
     *
     * @return The amount of energy required for planting
     */
    int getEnergyToPlant();
}
