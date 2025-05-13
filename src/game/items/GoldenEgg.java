package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.actors.creatures.Creature;
import game.actors.creatures.GoldenBeetle;
import game.utils.AdjacentCapabilityChecker;

/**
 * GoldenEgg: produced by GoldenBeetle every five turns.
 * Hatches into a GoldenBeetle when adjacent to any cursed entity.
 * Pauses hatch-timer when in inventory.
 * Can be consumed by Farmer for stamina.
 *
 * @author Kar May Ong
 */
public class GoldenEgg extends Egg {
    public static final int STAMINA_INCREASE_AMOUNT_EAT = 20;

    /**
     * Constructor for the GoldenEgg.
     */
    public GoldenEgg() {
        super("Golden Egg\uD83D\uDFE1");
    }

    /**
     * Creates the hatchling that this egg spawns, which is a GoldenBeetle
     *
     * @return an instance of GoldenBeetle
     */
    @Override
    protected Creature createHatchling() {
        return new GoldenBeetle();
    }

    /**
     * Consumes the egg and increases actor's stamina.
     * @param actor the actor eating the egg
     * @param map   the game map where the action occurs.
     */
    @Override
    public void eat(Actor actor, GameMap map) {
        super.eat(actor, map);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, STAMINA_INCREASE_AMOUNT_EAT);
        new Display().println("Farmer's stamina is increased by " + STAMINA_INCREASE_AMOUNT_EAT);
    }

    /**
     * Determines whether the egg can be hatched.
     * GoldenEgg can only be hatched when it is near to cursed entities
     *
     * @param currentLocation current ground Location
     * @return true if the egg should hatch, false otherwise
     */
    @Override
    protected boolean shouldHatch(Location currentLocation) {
        return AdjacentCapabilityChecker.hasAdjacentCapability(currentLocation, Status.CURSED);
    }

}
