package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A healing effect that restores health over time.
 *
 * @author Kian Lok Chin
 */
public class HealingEffect extends StatusEffect {

    private int healAmount;
    private int duration;
    private static final int EFFECT_DURATION_EXPIRED = 0;

    /**
     * Constructor for HealingEffect
     *
     * @param healAmount The amount of healing per tick
     * @param duration The number of ticks the effect lasts
     */
    public HealingEffect(int healAmount, int duration) {
        super("Healing");
        this.healAmount = healAmount;
        this.duration = duration;
    }

    /**
     * Apply the healing effect each tick.
     *
     * @param location The location of the actor
     * @param actor The actor with the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        actor.heal(healAmount);
        new Display().println(actor + "'s health is increased by " + healAmount);
        duration--;

        if (duration <= EFFECT_DURATION_EXPIRED) {
            actor.removeStatusEffect(this);
        }
    }
}
