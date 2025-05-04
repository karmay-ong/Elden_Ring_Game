package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PlantCropAction;

/**
 * Abstract base class for all plantable seeds in the game.
 * Seeds are items that can be planted to grow various plants.
 * Each seed type requires a specific amount of energy to plant
 * and has a unique planting effect.
 *
 * @author Kian Lok Chin
 */
public abstract class PlantSeed extends Item {

    /**
     * The amount of energy required to plant the seed
     */
    private int energyToPlant;

    /**
     * Constructor for the PlantSeed class.
     *
     * @param name the name of the seed
     * @param displayChar the character that will represent the seed in the display
     * @param portable true if the seed can be picked up and carried
     * @param minEnergyToPlant the minimum energy required to plant the seed
     */
    public PlantSeed(String name, char displayChar, boolean portable, int minEnergyToPlant) {
        super(name, displayChar, portable);
        this.energyToPlant = minEnergyToPlant;
    }

    /**
     * Plants the seed at the specified location, with effects depending on the
     * specific seed type.
     * This method must be implemented by all concrete seed classes.
     *
     * @param actor the actor planting the seed
     * @param location the location where the seed is being planted
     * @param map the game map containing the location
     * @return a string describing the planting action and its results
     */
    public abstract String plant(Actor actor, Location location, GameMap map);

    /**
     * Returns a list of allowable actions for this seed.
     * By default, all seeds can be planted if the actor has sufficient energy.
     *
     * @param location the current location of the seed
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();
        actions.add(new PlantCropAction(this, energyToPlant));
        return actions;
    }
}
