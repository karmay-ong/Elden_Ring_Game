package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;
import game.actors.creatures.OmenSheep;

/**
 * OmenSheepEgg: hatches into an OmenSheep after 3 ground-ticks.
 */
public class OmenSheepEgg extends Egg {

    private int groundAge = 0;
    public static final int HEALTH_MAX_INCREASE = 10;
    public static final int MATURITY_AGE_THRESHOLD = 10;

    /**
     * Creates an egg that hatches into an OmenSheep after 3 turns on ground.
     */
    public OmenSheepEgg() {
        super("Omen Sheep Egg\uD83E\uDD5A");
    }

    /**
     * Creates the hatchling that this egg spawns, which is an OmenSheep.
     *
     * @return a new instance of OmenSheep
     */
    @Override
    protected Creature createHatchling() {
        return new OmenSheep();
    }

    /**
     * Consumes the egg and increases the actor's maximum health.
     *
     * @param actor the actor eating the egg
     * @param map   the game map where the action occurs
     */

    @Override
    public void eat(Actor actor, GameMap map) {
        super.eat(actor, map);
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEALTH_MAX_INCREASE);
        System.out.println("Farmer's health is increased by " + HEALTH_MAX_INCREASE);
    }

    /**
     * Determines whether the egg has matured enough to hatch.
     * Increments the ground age each time it is checked.
     *
     * @param currentLocation the location the egg is currently on
     * @return true if the egg should hatch, false otherwise
     */
    @Override
    protected boolean shouldHatch(Location currentLocation) {
        groundAge++;
        return groundAge >= MATURITY_AGE_THRESHOLD;
    }

    /**
     * Returns a string representation of the egg including its ground age.
     *
     * @return a string showing the egg's name and current age
     */
    @Override
    public String toString() {
        return super.toString() + " (" +  groundAge + ")";
    }
}