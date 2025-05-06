package game.actors.creatures;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Producible;
import game.behaviours.ProduceBehaviour;
import game.items.Eatable;
import game.items.GoldenEgg;

import java.util.TreeMap;

/**
 * Golden Beetle: 25 HP, every 5 turns lays a GoldenEgg; follows Farmer;
 * immune to Crimson Rot; can be consumed by Farmer in adjacency.
 */
public class GoldenBeetle extends Creature implements Eatable, Producible {
    public static final int GOLDEN_BEETLE_HITPOINTS = 25;
    public static final int HEALTH_INCREASE_AFTER_EATEN = 50;
    public static final int BALANCE_INCREASE_AFTER_EATEN = 1000;
    private int eggTimer = 0;
    public static final int EGG_TIMER_THRESHOLD = 5;

    public GoldenBeetle() {
        super("Golden Beetle\uD83E\uDEB2", 'b', GOLDEN_BEETLE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new ProduceBehaviour(this));
        behaviours.put(999, new WanderBehaviour());
    }

    @Override
    public boolean canProduce(Actor producer, GameMap map) {
        eggTimer += 1;
        if (eggTimer >= EGG_TIMER_THRESHOLD) {
            eggTimer = 0;
            return true;
        }
        return false;
    }

    @Override
    public void produce(Actor producer, GameMap map) {
        map.locationOf(this).addItem(new GoldenEgg());
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        actor.addBalance(BALANCE_INCREASE_AFTER_EATEN);
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,HEALTH_INCREASE_AFTER_EATEN);
        unconscious(map);
    }

}