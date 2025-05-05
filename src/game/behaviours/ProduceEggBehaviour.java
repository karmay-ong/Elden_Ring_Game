// game/behaviours/ProduceEggBehaviour.java
package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DropEggAction;

/**
 * Behaviour that causes OmenSheep to lay an egg every 7 turns.
 */
public class ProduceEggBehaviour implements Behaviour {
    private int counter = 7;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (--counter == 0) {
            counter = 7;
            return new DropEggAction();
        }
        return null;
    }
}
