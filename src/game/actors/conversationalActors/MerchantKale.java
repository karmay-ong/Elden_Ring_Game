package game.actors.conversationalActors;

import edu.monash.fit2099.demo.huntsman.WanderBehaviour;
import game.actors.Status;
import game.utils.AdjacentCapabilityChecker;
import java.util.TreeMap;

public class MerchantKale extends ConversationalActor {
    public static final int MERCHANT_KALE_HITPOINTS =  200;
    public static final int MONOLOGUE_LOW_BALANCE_THRESHOLD = 500;

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
}
