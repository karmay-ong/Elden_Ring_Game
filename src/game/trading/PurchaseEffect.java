package game.trading;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;

public interface PurchaseEffect {
    void apply(Actor buyer, Merchant merchant, GameMap map);
}