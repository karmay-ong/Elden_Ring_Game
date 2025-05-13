package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Merchant;
import game.actors.creatures.GoldenBeetle;

public class SpawnGoldenBeetleEffect implements PurchaseEffect {

    private Actor target;

    public SpawnGoldenBeetleEffect(Actor target){
        this.target = target;
    }
    public SpawnGoldenBeetleEffect(){}

    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        if (target == null) {
            target = buyer;
        }
        Location here = map.locationOf(target);
        GoldenBeetle goldenBeetle = new GoldenBeetle();
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(goldenBeetle)) {
                dest.addActor(goldenBeetle);
                break;
            }
        }
    }
}