package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * An Action that represents an attack by one Actor on another Actor.
 * This action handles both weapon-based and intrinsic attacks.
 *
 * @author Kian Lok Chin
 * @author FIT2099 teaching team
 */
public class AttackAction extends Action {

    /**
     * The Actor being attacked
     */
    private Actor target;

    /**
     * The Weapon being used for the attack. Can be null, in which case
     * the actor's intrinsic weapon will be used.
     */
    private Weapon weapon;

    /**
     * Constructor for the AttackAction class.
     *
     * @param target the Actor being attacked
     * @param weapon the Weapon being used for the attack. Can be null, in which case
     *               the actor's intrinsic weapon will be used.
     */
    public AttackAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    /**
     * Executes the attack action.
     * If the weapon is null, the actor's intrinsic weapon will be used.
     * If the attack causes the target to become unconscious, additional
     * processing will occur.
     *
     * @param actor the Actor performing the attack
     * @param map the GameMap containing the Actor
     * @return a description of the attack and its outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }
        return result;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor the Actor performing the action
     * @return a String describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
