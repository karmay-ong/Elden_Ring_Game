package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A crazy effect that drastically increases max health and damage multiplier.
 *
 * @author Kian Lok Chin
 */
public class CrazyEffect extends StatusEffect {

    private int duration;
    private int originalMaxHealth;
    private static final int INITIAL_DURATION = 10;
    private static final int EXPIRED_DURATION = 0;
    private static final int HEALTH_MULTIPLIER = 2;

    /**
     * Constructor for CrazyEffect
     *
     * @param duration The number of ticks the effect lasts
     */
    public CrazyEffect(int duration) {
        super("CRAZY");
        this.duration = duration;
    }

    /**
     * Apply the crazy effect on first tick and track duration.
     *
     * @param location The location of the actor
     * @param actor The actor with the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        if (duration == INITIAL_DURATION) { // First tick
            originalMaxHealth = actor.getAttributeMaximum(BaseActorAttributes.HEALTH);
            // Double max health
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, originalMaxHealth * HEALTH_MULTIPLIER);
            // Restore health to new maximum
            actor.heal(actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
            new Display().println(actor + "'s maximum health is multiplied by " + HEALTH_MULTIPLIER);
        }

        duration--;

        if (duration <= EXPIRED_DURATION) {
            // Reset to original values
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, originalMaxHealth);
            actor.removeStatusEffect(this);
        }
    }
}
