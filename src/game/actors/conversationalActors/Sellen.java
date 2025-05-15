package game.actors.conversationalActors;

import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
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
 * A ConversationalActor implementing Merchant, offering weapons for sale with special effects
 * such as health increase and creature spawning. Provides lore-focused monologues about
 * the academy and glintstones.
 *
 * @author Lim Chi Jian
 * @author Kian Lok Chin
 */
public class Sellen extends ConversationalActor {
    public final static int SELLEN_HITPOINTS = 150;

    /**
     * Constructs a new Sellen with predefined hitpoints and wandering behaviour.
     * Offers will be defined in the allowableActions method.
     */
    public Sellen() {
        super("Sellen\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDE92", 's', SELLEN_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Initializes merchant-specific monologues with lore hints about the academy and glintstones.
     */
    @Override
    protected void initMonologues() {
        addMonologue("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        addMonologue("You sense it too, don't you? The Glintstone hums, even now.");
    }

    /**
     * Returns allowable actions including purchasing weapons with special effects.
     * So far for now offers BroadSword with health bonus, DragonSlayerGreatSword that spawns
     * a Golden Beetle, and Katana that spawns an Omen Sheep with heal and increase max stamina effects.
     *
     * @param otherActor the actor interacting with this merchant
     * @param direction  the direction of the other actor
     * @param map        the game map
     * @return the ActionList including purchase options
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor ,direction, map);
        List<Offer> sellenOffers = new ArrayList<>();
        sellenOffers.add(new Offer( new BroadSword(),100, List.of(new IncreaseMaxHealthEffect(20))));
        sellenOffers.add(new Offer( new DragonSlayerGreatSword(),1500, List.of(new SpawnGoldenBeetleEffect(otherActor))));
        sellenOffers.add(new Offer( new Katana(), 500, Arrays.asList( new SpawnOmenSheepEffect(this),
                new HealEffect(10),
                new IncreaseMaxStaminaEffect(20))));
        for (Offer offer : sellenOffers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }
}
