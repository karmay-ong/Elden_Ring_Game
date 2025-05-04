package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Curable;
import game.actors.Status;

/**
 * A class representing a blight covering the ground of the valley.
 * The blight is a cursed form of ground that can be cured to transform into soil.
 * It is represented by 'x' on the game map.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 */
public class Blight extends Ground implements Curable {

    /**
     * Constructor for the Blight class.
     * Initializes the blight with a display character 'x' and adds the CURSED capability.
     */
    public Blight() {
        super('x', "Blight\uD83C\uDFD4\uFE0F");
        this.addCapability(Status.CURSED);
    }

    /**
     * Cures the blighted ground, transforming it into soil.
     *
     * @param actor the actor performing the cure
     * @param map the game map where the blight is located
     * @param cureItem the item used to cure the blight
     */
    @Override
    public void cure(Actor actor, GameMap map, Item cureItem) {
        map.locationOf(actor).setGround(new Soil());
    }

    /**
     * Returns a list of actions that can be performed on this ground by an actor.
     * If the actor is standing on the blight and has items with the CURE ability,
     * they can perform a cure action to transform the blight into soil.
     *
     * @param actor the actor performing actions
     * @param location the location of the ground
     * @param direction the direction from which the actor is approaching
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        Actor availableActor = location.getActor();
        Item cureItem;
        if (availableActor != null) {
            if (location.getActor().equals(actor)) {
                for (Item item: actor.getItemInventory()){
                    if (item.hasCapability(Ability.CURE)){
                        cureItem = item;
                        actions.add(new CureAction(this, cureItem, 50));
                    }
                }
            }
        }
        return actions;
    }
}
