package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Curable;

/**
 * Action class for curing both ground and creatures
 *
 * @author Kian Lok Chin
 */
public class CureAction extends Action {

    /**
     * The entity to be cured (ground or creature)
     */
    private Curable target;

    /**
     * The item used for curing
     */
    private Item cureItem;

    /**
     * The amount of energy required to perform the cure action
     */
    private int energyToCure;


    /**
     * Constructor for the CureAction
     *
     * @param target       The Cureable entity to be cured
     * @param cureItem     The item used to perform the cure
     * @param energyToCure The amount of energy required to perform the cure
     */
    public CureAction(Curable target, Item cureItem, int energyToCure) {
        this.target = target;
        this.cureItem = cureItem;
        this.energyToCure = energyToCure;
    }

    /**
     * Executes the cure action if the actor has enough stamina.
     * Decreases the actor's stamina by the required energy amount.
     *
     * @param actor The actor performing the cure action
     * @param map The game map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if actor has enough stamina to perform the cure
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < energyToCure) {
            return "\uD83D\uDE14Sorry. The farmer does not have energy to cure " + target;
        }
        target.cure(actor, map, cureItem);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, energyToCure);
        return target + " is healed using " + cureItem;
    }

    /**
     * Returns a description of this action suitable for the menu
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + cureItem + " on " + target;
    }
}
