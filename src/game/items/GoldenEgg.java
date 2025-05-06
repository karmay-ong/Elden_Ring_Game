package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.actors.creatures.Creature;
import game.actors.creatures.GoldenBeetle;
import game.utils.AdjacentCapabilityChecker;

/**
 * GoldenEgg: produced by GoldenBeetle every five turns.  Hatches into a
 * GoldenBeetle when adjacent to any cursed entity.  Pauses hatch-timer when
 * in inventory.  Can be consumed by Farmer for stamina.
 */
public class GoldenEgg extends Egg {
    public static final int STAMINA_INCREASE_AMOUNT_EAT = 20;

    public GoldenEgg() {
        super("Golden Egg\uD83D\uDFE1");
    }

    @Override
    protected Creature createHatchling() {
        return new GoldenBeetle();
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        super.eat(actor, map);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, STAMINA_INCREASE_AMOUNT_EAT);
    }

    @Override
    protected boolean shouldHatch(Location currentLocation) {
        return AdjacentCapabilityChecker.hasAdjacentCapability(currentLocation, Status.CURSED);
    }

}
