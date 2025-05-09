package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;
import game.trading.PurchaseEffect;

public class MaxStaminaEffect implements PurchaseEffect {
    private int amount;
    private ActorAttributeOperations operation;

    public MaxStaminaEffect(int amount, ActorAttributeOperations operation) {
        this.amount = amount;
        this.operation = operation;
    }

    @Override
    public void apply(Actor buyer, Merchant merchant, GameMap map) {
        buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, operation, amount);
    }
}