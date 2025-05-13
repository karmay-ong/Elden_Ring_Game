package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PlantCropAction;
import game.grounds.Plantable;

public class Seed extends Item {
    private Plantable plant;

    public Seed(Plantable plant){
        super( "Seed", '*', true);
        this.plant =  plant;
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new PlantCropAction(this,plant,plant.getEnergyToPlant()));
        return actions;
    }

}
