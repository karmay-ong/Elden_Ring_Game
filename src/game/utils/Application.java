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
import game.items.*;
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
                new Wall(), new Floor(), new Soil(), new TeleportationCircle());

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

        // Define the Limveld map layout
        List<String> limveldMapLayout = Arrays.asList(
                ".............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx");

        // Create the game maps
        GameMap valleyMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        GameMap limveldMap = new GameMap("Limveld", groundFactory, limveldMapLayout);
        world.addGameMap(valleyMap);
        world.addGameMap(limveldMap);

        // Create and set up teleportation circles
        TeleportationCircle valleyCircle = new TeleportationCircle();
        TeleportationCircle limveldCircle = new TeleportationCircle();

        // Place teleportation circles in more open areas
        valleyMap.at(7, 3).setGround(valleyCircle);  // More open area in the valley
        limveldMap.at(7, 3).setGround(limveldCircle); // Keep same position in Limveld

        // Link the circles
        valleyCircle.setDestination(limveldMap.at(7, 3));
        limveldCircle.setDestination(valleyMap.at(7, 3));

        // Display the game title with animation
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Create and position the player in an open area next to the teleportation circle
        Player player = new Player("\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83C\uDF3EFarmer", '@', 100, 200, 36);
        world.addPlayer(player, valleyMap.at(8, 3));  // Position player next to the teleportation circle
        //world.addPlayer(player, gameMap.at(21, 5)); //test sellen
        //world.addPlayer(player, gameMap.at(11, 12)); //test Guts
        //world.addPlayer(player, gameMap.at(31, 6)); //test Kale
        player.addBalance(100000000);
        EnvironmentalStatusSystem.initialize(player, valleyMap);

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
        player.addItemToInventory(new WaterBucket());
        // Create and position NPCs
        SpiritGoat spiritGoat = new SpiritGoat(spiritGoatCondition);
        OmenSheep omenSheep = new OmenSheep(omenSheepCondition);
        GoldenBeetle goldenBeetle = new GoldenBeetle(beetleEffects, goldenBeetleCondition);
        valleyMap.addActor(spiritGoat, valleyMap.at(24, 10));
        //gameMap.addActor(spiritGoat, gameMap.at(26, 5)); //test spirit goat reproduction

        valleyMap.addActor(goldenBeetle, valleyMap.at(22, 10));
        valleyMap.addActor(omenSheep, valleyMap.at(23, 11));
        valleyMap.addActor(new Sellen(), valleyMap.at(21, 4));
        valleyMap.addActor(new MerchantKale(), valleyMap.at(30, 6));
        valleyMap.addActor(new Guts(), valleyMap.at(12, 12));

        // Add starting items to player's inventory
        player.addItemToInventory(new Seed(new Inheritree()));
        player.addItemToInventory(new Seed(new Bloodrose()));
        // Add items to the game world
        valleyMap.at(24, 11).addItem(new Talisman());

        // Create test instances of creatures with different behavior selection strategies
        // Ordered behavior (standard) creatures
        OmenSheep orderedSheep = new OmenSheep();
        SpiritGoat orderedGoat = new SpiritGoat(new TurnBasedCondition(5));
        GoldenBeetle orderedBeetle = new GoldenBeetle();

        // Random behavior creatures
        OmenSheep randomSheep = OmenSheep.createRandomBehaviorSheep();
        SpiritGoat randomGoat = SpiritGoat.createRandomBehaviorGoat(new TurnBasedCondition(5));
        GoldenBeetle randomBeetle = GoldenBeetle.createRandomBehaviorBeetle();

        // Place ordered behavior creatures in Valley
        valleyMap.at(10, 10).addActor(orderedSheep);
        valleyMap.at(12, 10).addActor(orderedGoat);
        valleyMap.at(14, 10).addActor(orderedBeetle);

        // Place random behavior creatures in Limveld
        limveldMap.at(5, 5).addActor(randomSheep);
        limveldMap.at(7, 5).addActor(randomGoat);
        limveldMap.at(9, 5).addActor(randomBeetle);

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
