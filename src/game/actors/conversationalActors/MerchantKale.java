package game.actors.conversationalActors;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.Merchant;
import game.actors.Status;
import game.effects.HealEffect;
import game.effects.MaxHealthEffect;
import game.effects.MaxStaminaEffect;
import game.trading.Offer;
import game.utils.AdjacentCapabilityChecker;
import game.weapons.BroadSword;
import game.weapons.DragonSlayerGreatSword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class MerchantKale extends ConversationalActor implements Merchant {
    public static final int MERCHANT_KALE_HITPOINTS =  200;
    public static final int MONOLOGUE_LOW_BALANCE_THRESHOLD = 500;

    private final List<Offer> offers = new ArrayList<>(Arrays.asList(
            new Offer(
                    new BroadSword(),
                    150,
                    Arrays.asList(
                            new HealEffect(10),
                            new MaxStaminaEffect(30, ActorAttributeOperations.INCREASE)
                    )
            ),
            new Offer(
                    new DragonSlayerGreatSword(),
                    1700,
                    Arrays.asList(
                            new MaxHealthEffect(15, ActorAttributeOperations.INCREASE),
                            new MaxStaminaEffect(20, ActorAttributeOperations.INCREASE)
                    )
            )
    ));
    public MerchantKale() {
        super("Kale\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFED", 'k', MERCHANT_KALE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new WanderBehaviour());
    }

    @Override
    protected void initMonologues() {
        addMonologue("Ah, hard times, I see. Keep your head low and your blade sharp.",
                (farmer, map, self) -> farmer.getBalance() < MONOLOGUE_LOW_BALANCE_THRESHOLD);

        addMonologue("Not a scrap to your name? Even a farmer should carry a trinket or two.",
                (farmer, map, self) -> farmer.getItemInventory().isEmpty());

        addMonologue("Rest by the flame when you can, friend. These lands will wear you thin.",
                (farmer, map, self) ->
                        AdjacentCapabilityChecker.hasAdjacentCapability(map.locationOf(self), Status.CURSED));

        addMonologue("A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen.");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor ,direction, map);
        for (Offer offer : offers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }
    public void removeOffer(Offer offer) {
        offers.remove(offer);
    }


}
