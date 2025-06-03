package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * @author Adrian Kristanto
 * Modified by: Kian Lok Chin
 */
public abstract class WeaponItem extends Item implements Weapon {
    /**
     * The default damage multiplier applied to weapons
     */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    /**
     * The base damage this weapon does
     */
    private int damage;

    /**
     * The probability/chance to hit the target (0-100)
     */
    private int hitRate;

    /**
     * The verb used to describe the attack, e.g. "hits", "slashes"
     */
    private final String verb;

    /**
     * The multiplier applied to the base damage
     */
    private float damageMultiplier;

    private static final int HIT_RATE_MAX = 100;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    /**
     * Performs an attack with this weapon
     *
     * @param attacker the actor performing the attack
     * @param target the actor being attacked
     * @param map the game map where the attack occurs
     * @return a string describing the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(HIT_RATE_MAX) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * Returns allowable actions for this weapon when it's on the ground
     *
     * @param otherActor the actor performing the actions
     * @param location the location of the weapon
     * @return a list of actions that can be performed with this weapon
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(otherActor, this));
        return actions;
    }
}

