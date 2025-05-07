package game.utils;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.conversationalActors.Guts;
import game.actors.conversationalActors.MerchantKale;
import game.actors.conversationalActors.Sellen;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.grounds.Blight;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.Wall;
import game.items.BloodroseSeed;
import game.items.InheritreeSeed;
import game.items.Talisman;

import java.util.Arrays;
import java.util.List;

/**
 * The main class to setup and run the game.
 * This class initializes the game world, maps, actors, and items.
 * It also handles the display of title and game over screens.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
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
        Player player = new Player("\uD83E\uDDD1\uD83C\uDFFB\u200D\uD83C\uDF3EFarmer", '@', 100, 200);
        world.addPlayer(player, gameMap.at(23, 10));

        // Create and position NPCs
        SpiritGoat spiritGoat = new SpiritGoat();
        OmenSheep omenSheep = new OmenSheep();
        gameMap.addActor(spiritGoat, gameMap.at(24, 10));
        gameMap.addActor(omenSheep, gameMap.at(23, 11));

        gameMap.addActor(new Sellen(), gameMap.at(21, 4));
        gameMap.addActor(new MerchantKale(), gameMap.at(30, 6));
        gameMap.addActor(new Guts(), gameMap.at(12, 12));

        // Add starting items to player's inventory
        player.addItemToInventory(new InheritreeSeed());
        player.addItemToInventory(new BloodroseSeed());

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
