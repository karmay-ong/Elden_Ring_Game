
package game.actors.conversationalActors;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.behaviours.AttackBehaviour;
import game.weapons.BareFist;
import java.util.TreeMap;

public class Guts extends ConversationalActor {
    public static final int GUTS_HITPOINTS =  500;
    public static final int MONOLOGUE_WEAK_HEALTH_THRESHOLD = 50;

    public Guts() {
        super("Guts\uD83D\uDC7A", 'g',GUTS_HITPOINTS );
        setIntrinsicWeapon(new BareFist());
        behaviours = new TreeMap<>();
        behaviours.put(1, new AttackBehaviour());
        behaviours.put(3, new WanderBehaviour());
    }
    @Override
    protected void initMonologues() {
        addMonologue("RAAAAGH!");
        addMonologue("I’LL CRUSH YOU ALL!");
        addMonologue("WEAK! TOO WEAK TO FIGHT ME!",
                (farmer, map, self) -> farmer.getAttribute(BaseActorAttributes.HEALTH) < MONOLOGUE_WEAK_HEALTH_THRESHOLD);
    }
}