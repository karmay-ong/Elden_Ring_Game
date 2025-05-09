package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.trading.PurchaseEffect;

public class HealEffect implements PurchaseEffect {
    private int healAmount;

    public HealEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.heal(healAmount);
    }
}