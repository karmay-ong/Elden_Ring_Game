package game.actors.conversationalActors;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
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

public class Sellen extends ConversationalActor implements Merchant {
    public final static int SELLEN_HITPOINTS = 150;
    private List<Offer> offers = new ArrayList<>(Arrays.asList(
            new Offer(
                    new BroadSword(),
                    100,
                    Arrays.asList(
                            new HealEffect(10),
                            new MaxHealthEffect(20, ActorAttributeOperations.INCREASE)
                    )
            ),
            new Offer(
                    new DragonSlayerGreatSword(),
                    1500,
                    Arrays.asList(
                            new MaxHealthEffect(15, ActorAttributeOperations.INCREASE),
                            new SpawnGoldenBeetleEffect()
                    )
            ),
            new Offer(
                    new Katana(),
                    500,
                    Arrays.asList(
                            new HurtEffect(25),
                            new SpawnOmenSheepEffect(this),
                            new HealEffect(10),
                            new MaxStaminaEffect(20, ActorAttributeOperations.INCREASE)
                    )
            )
    ));


    public Sellen() {
        super("Sellen\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDE92", 's', SELLEN_HITPOINTS);
        behaviours = new TreeMap<>();
        behaviours.put(1, new WanderBehaviour());
    }

    @Override
    protected void initMonologues() {
        addMonologue("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        addMonologue("You sense it too, don’t you? The Glintstone hums, even now.");
    }

    @Override
    public void removeOffer(Offer offer) {
        offers.remove(offer);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor ,direction, map);
        for (Offer offer : offers) {
            actions.add(new PurchaseAction(this, offer));
        }
        return actions;
    }
}