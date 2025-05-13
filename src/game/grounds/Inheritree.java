package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;

/**
 * A beneficial tree that heals actors in adjacent locations.
 * The Inheritree is represented by 't' on the game map and provides
 * healing and stamina restoration to any actor standing next to it.
 *
 * @author Kian Lok Chin
 */
public class Inheritree extends Ground implements Plantable {

    public final static int MIN_ENERGY_TO_PLANT = 25;

    /**
     * Constructor for the Inheritree class.
     * Initializes the inheritree with a display character 't'.
     */
    public Inheritree() {
        super('t', "Inheritree\uD83C\uDF32");
    }

    /**
     * Performs the Inheritree's action each turn, healing and restoring
     * stamina to any actors in adjacent locations.
     *
     * Each adjacent actor receives:
     * - 5 points of health healing
     * - 5 points of stamina restoration (if they have stamina)
     *
     * Messages are printed to the console when actors are healed or
     * have their stamina restored.
     *
     * @param location the location of the Inheritree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // Check all surrounding locations for actors
        for (Exit exit : location.getExits()) {
            Location surroundings = exit.getDestination();
            Actor actor = surroundings.getActor();
            if(actor != null) {
                // Heal the actor
                new Display().println("❤️\u200D\uD83E\uDE79"+ actor + " health is increased by 5❤\uFE0F\u200D\uD83E\uDE79");
                actor.heal(5);

                // Restore stamina if the actor has stamina attribute
                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    new Display().println("\uD83D\uDD0B"+ actor + " stamina is increased by 5\uD83D\uDD0B");
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 5);
                }
            }
        }
    }
    @Override
    public int getEnergyToPlant() {
        return MIN_ENERGY_TO_PLANT;
    }

    @Override
    public String plant(Actor actor, Location location, GameMap map) {
        location.setGround(this);
        // Purify adjacent cursed ground by turning it into soil
        for (Exit exit : location.getExits()) {
            if(exit.getDestination().getGround().hasCapability(Status.CURSED)){
                exit.getDestination().setGround(new Soil());
            }
        }

        return actor + " planted a " + this;
    }
}
