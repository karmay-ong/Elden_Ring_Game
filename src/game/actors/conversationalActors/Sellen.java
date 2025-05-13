package game.actors.conversationalActors;

import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.Merchant;
import game.effects.*;
import game.trading.Offer;
import game.weapons.BroadSword;
import game.weapons.DragonSlayerGreatSword;
import game.weapons.Katana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * A ConversationalActor implementing Merchant, with a variety of unique offers that grant effects
 * such as healing or spawning creatures. Provides lore-focused monologues.
 *
 * @author Lim Chi Jian
 * @author Kian Lok Chin
 */
public class Sellen extends ConversationalActor implements Merchant {
    public final static int SELLEN_HITPOINTS = 150;
    private List<Offer> offers;

    /**
     * Constructs a new Sellen with predefined offers and wandering behaviour.
     */
    public Sellen() {
        super("Sellen\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDE92", 's', SELLEN_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(999, new WanderBehaviour());
        offers = new ArrayList<>();
        addOffer(new Offer( new BroadSword(),100, List.of(new MaxHealthEffect(20,
                ActorAttributeOperations.INCREASE))));
        addOffer(new Offer( new DragonSlayerGreatSword(),1500, List.of(new SpawnGoldenBeetleEffect())));
        addOffer(new Offer( new Katana(), 500, Arrays.asList( new SpawnOmenSheepEffect(this),
                            new HealEffect(10),
                            new MaxStaminaEffect(20, ActorAttributeOperations.INCREASE))));
    }

    /**
     * Initializes merchant-specific monologues with lore hints about glintstones and exile.
     */
    @Override
    protected void initMonologues() {
        addMonologue("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        addMonologue("You sense it too, don’t you? The Glintstone hums, even now.");
    }

    /**
     * Returns allowable actions including purchasing available offers.
     *
     * @param otherActor the actor interacting with this merchant
     * @param direction  the direction of the other actor
     * @param map        the game map
     * @return the ActionList including purchase options
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor ,direction, map);
        for (Offer offer : offers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }

    /**
     * Adds a new trade offer to this merchant's list.
     *
     * @param offer the Offer to be added
     */
    @Override
    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}