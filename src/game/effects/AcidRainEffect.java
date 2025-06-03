package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Ability;
import game.actors.Player;
import game.actors.Status;

/**
 * Represents the effects of acid rain weather on the player.
 *
 * Acid rain reduces the player's health each tick unless they are shielded.
 * It also extinguishes any lit torches or warm items the player carries.
 *
 * @author Lim Chi Jian
 */
public class AcidRainEffect extends WeatherEffect {
    private final String emoji = "⚠️🌧️️";
    private final String color = "\u001B[34m";
    private static final int HEALTH_REDUCTION_PER_TICK = 5;

    /**
     * Checks if this effect is applicable given the current weather type.
     *
     * @param weather the current weather type
     * @return true if the weather is ACID_RAIN, false otherwise
     */
    @Override
    public boolean isApplicable(WeatherType weather) {
        return weather == WeatherType.ACID_RAIN;
    }

    /**
     * Returns the color associated with this object.
     *
     * @return a string representing the color of the object
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Returns the emoji representing this object.
     *
     * @return a string containing the emoji symbol
     */
    @Override
    public String getEmoji() {
        return emoji;
    }


    /**
     * Applies the acid rain effect to the player.
     * If the player has the BLOCK_ACID_RAIN capability, no effect occurs.
     * Otherwise, torches and warm items are extinguished, and health is reduced.
     *
     * @param actor the player affected
     * @param map the game map context
     */
    @Override
    protected void applyEffect(Player actor, GameMap map) {
        Display display = new Display();

        if (actor.hasCapability(Ability.BLOCK_ACID_RAIN)) {
            display.println(actor + " is shielded from the acid rain!");
            return;
        }

        // Extinguish torches or warm items
        for (Item item : actor.getItemInventory()) {
            if (item.hasCapability(Status.FLAMMABLE)) {
                item.removeCapability(Status.FLAMMABLE);
                actor.removeCapability(Ability.ABLE_WARM);
                display.println("The acid rain extinguishes " + actor + "'s " + item + "!");
            } else if (item.hasCapability(Ability.ABLE_WARM)) {
                item.removeCapability(Ability.ABLE_WARM);
                display.println("The acid rain extinguishes " + actor + "'s " + item + "!");
            }
        }

        // Reduce health due to acid rain damage
        actor.hurt(HEALTH_REDUCTION_PER_TICK);

        if (!actor.isConscious()) {
            display.println(actor.unconscious(map));
        }

        display.println("Acid rain corrodes " + actor + ", health reduced by "
                + HEALTH_REDUCTION_PER_TICK + " points.");
    }

    @Override
    public String toString() {
        return "Acid Rain️";
    }
}
