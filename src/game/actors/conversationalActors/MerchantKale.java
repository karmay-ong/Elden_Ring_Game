package game.actors.conversationalActors;

import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.Status;
import game.conditions.*;
import game.effects.IncreaseMaxStaminaEffect;
import game.trading.Offer;
import game.weapons.BroadSword;
import game.weapons.DragonSlayerGreatSword;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A ConversationalActor implementing Merchant, offering items for sale with dynamic monologues
 * based on the player's balance, inventory, or proximity to cursed entities.
 * Provides purchase actions for weapons with additional effects.
 *
 * @author Lim Chi Jian
 * @author Kian Lok Chin
 */
public class MerchantKale extends ConversationalActor {
    public static final int MERCHANT_KALE_HITPOINTS = 200;
    public static final int MONOLOGUE_LOW_BALANCE_THRESHOLD = 500;

    /**
     * Constructs a new MerchantKale with predefined hitpoints and wandering behaviour.
     * Offers will be defined in the allowableActions method.
     */
    public MerchantKale() {
        super("Kale\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFED", 'k', MERCHANT_KALE_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Initializes merchant-specific monologues based on contextual conditions:
     * - When player's balance is below 500
     * - When player has an empty inventory
     * - When near a cursed entity
     * - A default monologue with no conditions
     *
     * @param listener the actor that will be listening to MerchantKale
     */
    @Override
    protected void initMonologues(Actor listener) {

        addMonologue("Ah, hard times, I see. Keep your head low and your blade sharp.",
                new BalanceCondition(listener, MONOLOGUE_LOW_BALANCE_THRESHOLD, Operators.LESS_THAN));

        addMonologue("Not a scrap to your name? Even a farmer should carry a trinket or two.",
                new EmptyInventoryCondition(listener));

        addMonologue("Rest by the flame when you can, friend. These lands will wear you thin.",
                new AdjacentCapabilityCondition(this, Status.CURSED));

        addMonologue("A merchant's life is a lonely one. But the roads… they whisper secrets to those who listen.");
    }

    /**
     * Returns allowable actions including purchasing weapons with stamina enhancement effects.
     * So far for now offers BroadSword and DragonSlayerGreatSword with stamina bonuses.
     *
     * @param otherActor the actor interacting with this merchant
     * @param direction  the direction of the other actor
     * @param map        the game map
     * @return the ActionList including purchase options
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        List<Offer> kaleOffers = new ArrayList<>();
        kaleOffers.add(new Offer(new BroadSword(),150, List.of(new IncreaseMaxStaminaEffect(30 ))));
        kaleOffers.add(new Offer(new DragonSlayerGreatSword(),1700, List.of(new IncreaseMaxStaminaEffect(20))));
        for (Offer offer : kaleOffers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }

}