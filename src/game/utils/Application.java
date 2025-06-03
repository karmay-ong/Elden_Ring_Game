package game.utils;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Status;
import game.actors.conversationalActors.Guts;
import game.actors.conversationalActors.MerchantKale;
import game.actors.conversationalActors.Sellen;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.conditions.AdjacentCapabilityCondition;
import game.conditions.Condition;
import game.conditions.TurnBasedCondition;
import game.effects.*;
import game.grounds.*;
import game.items.Seed;
import game.items.Talisman;
import game.items.Torch;
import game.items.Umbrella;
import game.potions.CrazyPotion;
import game.potions.HealingPotion;
import game.potions.PoisonPotion;
import game.time.EnvironmentalStatusSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class to setup and run the game.
 * This class initializes the game world, maps, actors, and items.
 * It also handles the display of title and game over screens.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 * Modified by: Kar May Ong
 * Modified by: Lim Chi Jian
 */
public class Application {

    /**
     * Main method that starts the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a new world with a display
        World world = new World(new Display());

        // Create a factory with all ground types used in the game
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        // Define the game map layout
        List<String> map = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");


        // Create the game map
        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap);

        // Display the game title with animation
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Create and position the player
        Player player = new Player("\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83C\uDF3EFarmer", '@', 100, 200, 36);
        world.addPlayer(player, gameMap.at(23, 10));
        //world.addPlayer(player, gameMap.at(21, 5)); //test sellen
        //world.addPlayer(player, gameMap.at(11, 12)); //test Guts
        //world.addPlayer(player, gameMap.at(31, 6)); //test Kale
        player.addBalance(100000000);
        EnvironmentalStatusSystem.initialize(player, gameMap);

        // Create conditions and effects for all NPCs
        Condition spiritGoatCondition = new AdjacentCapabilityCondition(Status.BLESSED);
        Condition omenSheepCondition = new TurnBasedCondition(OmenSheep.EGG_TIMER_THRESHOLD);
        Condition goldenBeetleCondition = new TurnBasedCondition(GoldenBeetle.EGG_TIMER_THRESHOLD);
        List<Effect> beetleEffects = new ArrayList<>();
        beetleEffects.add(new HealEffect(15));
        beetleEffects.add(new IncreaseBalanceEffect(1000));


        player.addItemToInventory(new PoisonPotion());
        player.addItemToInventory(new HealingPotion());
        player.addItemToInventory(new CrazyPotion());

        player.addItemToInventory(new Torch());
        player.addItemToInventory(new Umbrella());
        // Create and position NPCs
        SpiritGoat spiritGoat = new SpiritGoat(spiritGoatCondition);
        OmenSheep omenSheep = new OmenSheep(omenSheepCondition);
        GoldenBeetle goldenBeetle = new GoldenBeetle(beetleEffects, goldenBeetleCondition);
        gameMap.addActor(spiritGoat, gameMap.at(24, 10));
        //gameMap.addActor(spiritGoat, gameMap.at(26, 5)); //test spirit goat reproduction

        gameMap.addActor(goldenBeetle, gameMap.at(22, 10));
        gameMap.addActor(omenSheep, gameMap.at(23, 11));
        gameMap.addActor(new Sellen(), gameMap.at(21, 4));
        gameMap.addActor(new MerchantKale(), gameMap.at(30, 6));
        gameMap.addActor(new Guts(), gameMap.at(12, 12));

        // Add starting items to player's inventory
        player.addItemToInventory(new Seed(new Inheritree()));
        player.addItemToInventory(new Seed(new Bloodrose()));
        // Add items to the game world
        gameMap.at(24, 11).addItem(new Talisman());

        // Run the game
        world.run();

        // Display game over screen with animation
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
