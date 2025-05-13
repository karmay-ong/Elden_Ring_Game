package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PlantCropAction;
import game.grounds.Plantable;

/**
 * A seed item that can be planted to grow various plants
 *
 * @author Kian Lok Chin
 */
public class Seed extends Item {
    /**
     * The plant that will grow from this seed when planted
     */
    private Plantable plant;

    /**
     * Constructor for the Seed
     *
     * @param plant The Plantable that will grow from this seed
     */
    public Seed(Plantable plant){
        super( "Seed", '*', true);
        this.plant =  plant;
    }

    /**
     * Returns allowable actions for this Seed, which includes the ability to plant it
     *
     * @param owner The actor who owns the seed
     * @param map The game map
     * @return A list of actions that can be performed with this seed
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new PlantCropAction(this, plant, plant.getEnergyToPlant()));
        return actions;
    }
}

