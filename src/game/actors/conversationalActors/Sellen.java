package game.actors.conversationalActors;

import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.effects.*;
import game.items.Umbrella;
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
    private List<Offer> sellenOffers;
    private static final int WANDER_BEHAVIOUR_PRIORITY = 999;
    private static final int BROAD_SWORD_PRICE = 100;
    private static final int DRAGON_SLAYER_GREATSWORD_PRICE = 1500;
    private static final int KATANA_PRICE = 500;
    private static final int UMBRELLA_PRICE = 700;

    private static final int INCREASE_MAX_HEALTH_AMOUNT = 20;
    private static final int HEAL_AMOUNT = 10;
    private static final int INCREASE_MAX_STAMINA_AMOUNT = 20;
    private static final int HURT_AMOUNT = 10;

    /**
     * Constructs a new Sellen with predefined hitpoints and wandering behaviour.
     * Offers will be defined in the allowableActions method.
     */
    public Sellen() {
        super("Sellen\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDE92", 's', SELLEN_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
        sellenOffers = new ArrayList<>();
    }

    /**
     * Initializes two merchant-specific monologues with lore hints about the academy
     * and glintstones. These monologues have no special conditions and will always
     * be eligible.
     *
     * @param Listener the actor that will be listening to Sellen
     */
    @Override
    protected void initMonologues(Actor Listener) {
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
        if(sellenOffers.isEmpty()) {
            sellenOffers.add(new Offer(new BroadSword(), BROAD_SWORD_PRICE, List.of(new IncreaseMaxHealthEffect(INCREASE_MAX_HEALTH_AMOUNT))));
            sellenOffers.add(new Offer(new DragonSlayerGreatSword(), DRAGON_SLAYER_GREATSWORD_PRICE, List.of(new SpawnGoldenBeetleEffect(otherActor))));
            sellenOffers.add(new Offer(new Katana(), KATANA_PRICE, Arrays.asList(new SpawnOmenSheepEffect(this),
                    new HealEffect(HEAL_AMOUNT),
                    new IncreaseMaxStaminaEffect(INCREASE_MAX_STAMINA_AMOUNT))));
            sellenOffers.add(new Offer(new Umbrella(), UMBRELLA_PRICE, List.of(new HurtEffect(HURT_AMOUNT))));
        }
        for (Offer offer : sellenOffers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }
}