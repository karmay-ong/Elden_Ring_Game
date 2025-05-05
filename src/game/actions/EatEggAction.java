// game/actions/EatEggAction.java
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Ability;
import game.items.OmenSheepEgg;
import game.actors.Player;

/**
 * Allows an actor with the EAT_EGG ability to eat an OmenSheepEgg.
 */
public class EatEggAction extends Action {
    private final OmenSheepEgg egg;

    public EatEggAction(OmenSheepEgg egg) {
        this.egg = egg;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.hasCapability(Ability.EAT_EGG)) {
            return "❌ You can’t eat this ❌";
        }
        ((Player)actor).increaseMaxHp(10);
        actor.removeItemFromInventory(egg);
        return actor + " eats the egg and gains +10 max HP";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats an omen sheep egg";
    }
}
