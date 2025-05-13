package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Plantable {
    String plant(Actor actor, Location location, GameMap map);
    int getEnergyToPlant();
}
