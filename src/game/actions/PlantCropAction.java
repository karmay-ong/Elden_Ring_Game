package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.items.PlantSeed;

/**
 * An action for planting seeds to grow crops.
 * The action can only be performed on plantable ground and requires
 * the actor to have sufficient stamina.
 *
 * @author Kian Lok Chin
 */
public class PlantCropAction extends Action {

    /**
     * The seed to be planted
     */
    private PlantSeed seed;

    /**
     * The amount of energy required to plant the seed
     */
    private int energyToPlant;

    /**
     * Constructor for the PlantCropAction.
     *
     * @param seed The seed to be planted
     * @param energyToPlant The amount of energy required to plant the seed
     */
    public PlantCropAction(PlantSeed seed, int energyToPlant) {
        this.seed = seed;
        this.energyToPlant = energyToPlant;
    }

    /**
     * Executes the planting action if the actor has enough stamina and
     * is standing on plantable ground.
     *
     * The action decrease the actor's stamina, and plants the seed at the actor's location.
     *
     * @param actor The actor performing the planting action
     * @param map The game map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < energyToPlant){
            return "\uD83D\uDE14Sorry. The farmer does not have energy to plant " + seed;
        }
        Location here = map.locationOf(actor);
        if (!(here.getGround().hasCapability(Status.PLANTABLE))) {
            return "❌You cannot plant seeds here!❌";
        }

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, energyToPlant);
        return seed.plant(actor, here, map);
    }

    /**
     * Returns a description of this action suitable for the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants " + seed;
    }
}
