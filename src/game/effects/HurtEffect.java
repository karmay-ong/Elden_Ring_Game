package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.trading.PurchaseEffect;

public class HurtEffect implements PurchaseEffect {
    private int damage;

    public HurtEffect(int damage) {
        this.damage = damage;
    }

    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.hurt(damage);
    }
}