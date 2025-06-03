package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A poison effect that damages the actor over time.
 *
 * @author Kian Lok Chin
 */
public class PoisonEffect extends StatusEffect {

    private int damage;
    private int duration;
    private static final int EFFECT_DURATION_EXPIRED = 0;

    /**
     * Constructor for PoisonEffect
     *
     * @param damage The amount of damage per tick
     * @param duration The number of ticks the effect lasts
     */
    public PoisonEffect(int damage, int duration) {
        super("Poisoned");
        this.damage = damage;
        this.duration = duration;
    }

    /**
     * Apply the poison effect each tick.
     *
     * @param location The location of the actor
     * @param actor The actor with the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        actor.hurt(damage);
        new Display().println(actor + "'s health is decreased by " + damage);
        duration--;

        if (duration <= EFFECT_DURATION_EXPIRED) {
            actor.removeStatusEffect(this);
        }
    }
}
