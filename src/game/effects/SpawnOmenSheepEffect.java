package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Merchant;
import game.actors.creatures.OmenSheep;

public class SpawnOmenSheepEffect implements PurchaseEffect {

    private Actor target;

    public SpawnOmenSheepEffect(Actor actor){
        this.target = actor;
    }
    public SpawnOmenSheepEffect(){}

    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        if (target == null) {
            target = buyer;
        }
        Location location = map.locationOf(target);
        OmenSheep omenSheep = new OmenSheep();
        for (Exit exit : location.getExits()) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.canActorEnter(omenSheep)) {
                dest.addActor(omenSheep);
                break;
            }
        }
    }
}