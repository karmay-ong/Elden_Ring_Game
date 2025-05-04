package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class RotAction extends Action {
    private Actor target;

    /**
     * @param target the actor that should rot away
     */
    public RotAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return target.unconscious(map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return target + " rots away";
    }
}
