package game.actors.conversationalActors;
import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.behaviours.AttackBehaviour;
import game.conditions.ActorAttributeCondition;
import game.conditions.Condition;
import game.conditions.Operators;
import game.weapons.BareFist;
import java.util.TreeMap;

/**
 * A specific ConversationalActor named Guts, with high hit points and two behaviours:
 * attacking and wandering. Provides themed monologues based on health thresholds.
 *
 * @author Lim Chi Jian
 * @version ver1.0.0
 */
public class Guts extends ConversationalActor {
    public static final int GUTS_HITPOINTS =  500;
    public static final int MONOLOGUE_WEAK_HEALTH_THRESHOLD = 50;

    /**
     * Constructs a new Guts actor with predefined attributes, intrinsic weapon, and behaviours.
     */
    public Guts() {
        super("Guts\uD83D\uDC7A", 'g',GUTS_HITPOINTS );
        setIntrinsicWeapon(new BareFist());
        behaviours = new TreeMap<>();
        behaviours.put(1,new AttackBehaviour(new ActorAttributeCondition(BaseActorAttributes.HEALTH,50, Operators.GREATER_THAN)));
        behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Initializes Guts' monologues, including two general battle cries and
     * a conditional taunt that triggers when the listener's health is below 50.
     *
     * @param listener the actor that will be listening to Guts
     */
    @Override
    protected void initMonologues(Actor listener) {
        addMonologue("RAAAAGH!");
        addMonologue("I’LL CRUSH YOU ALL!");
        Condition lowHealthCondition = new ActorAttributeCondition(listener,
                BaseActorAttributes.HEALTH,
                MONOLOGUE_WEAK_HEALTH_THRESHOLD,
                Operators.LESS_THAN
        );
        addMonologue("WEAK! TOO WEAK TO FIGHT ME!", lowHealthCondition);
    }
}