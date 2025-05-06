package game.actors.creatures;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;

import java.util.TreeMap;

/**
 * Golden Beetle: 25 HP, every 5 turns lays a GoldenEgg; follows Farmer;
 * immune to Crimson Rot; can be consumed by Farmer in adjacency.
 */
public class GoldenBeetle extends Creature {
    public static final int GOLDEN_BEETLE_HITPOINTS = 25;

    public GoldenBeetle() {
        super("Golden Beetle\uD83E\uDEB2", 'b', GOLDEN_BEETLE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(999, new WanderBehaviour());
    }
}