package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.potions.Pouch;
import game.time.EnvironmentalStatusSystem;
import game.weapons.BareFist;

import java.util.List;

/**
 * Class representing the Player.
 * The player is controlled by user input and has attributes like health and stamina.
 * It displays a UI with attribute and inventory information.
 *
 * @author Adrian Kristanto
 * @author Kian Lok Chin
 */
public class Player extends Actor {
    private Pouch potionPouch;
    private int temperature;

    /**
     * Constructor for the Player class.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param stamina     Player's starting stamina points
     */
    public Player(String name, char displayChar, int hitPoints, int stamina, int temperature) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FOLLOWABLE);
        this.setIntrinsicWeapon(new BareFist());
        this.temperature = temperature;
        this.potionPouch = new Pouch();
    }

    /**
     * Returns the current temperature value.
     *
     * @return the current temperature as an integer.
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * Decreases the temperature by the specified amount.
     *
     * @param temp the amount to decrease the temperature by.
     */
    public void cold(int temp) {
        this.temperature -= temp;
    }

    /**
     * Increases the temperature by the specified amount.
     *
     * @param temp the amount to increase the temperature by.
     */
    public void warm(int temp) {
        this.temperature += temp;
    }

    /**
     * Displays the player's attributes (health and stamina) in a formatted UI.
     * Uses ANSI color codes for visual enhancement.
     *
     * @param display the display where attributes will be rendered
     */
    private void displayAttributes(Display display) {
        String reset   = "\u001B[0m";
        String red     = "\u001B[31m";
        String green   = "\u001B[32m";
        String yellow  = "\u001B[33m";
        String magenta = "\u001B[35m";
        String blue = "\u001B[34m";

        // Build the attributes line, now including wallet balance
        String singleLine = magenta + "===" + reset + " " +
                "✨ " + magenta + "Attributes" + reset + " ✨" + " " +
                red     + "❤ HP: "       + this.getAttribute(BaseActorAttributes.HEALTH)  + reset + " | " +
                green   + "⚡ Stamina: "  + this.getAttribute(BaseActorAttributes.STAMINA) + reset + " | " +
                blue   + "🌡️ Temperature: "  + this.temperature + "°C" + reset + " | " +
                yellow  + "💰 Gold: "     + this.getBalance()                            + reset + " " +
                magenta + "===" + reset;

        display.println(singleLine);
    }

    /**
     * Displays the player's inventory in a formatted UI.
     * If inventory is empty, displays "Empty" message.
     * Uses ANSI color codes for visual enhancement.
     *
     * @param display the display where inventory will be rendered
     */
    private void displayInventory(Display display) {
        String reset = "\u001B[0m";
        String cyan = "\u001B[36m";
        String yellow = "\u001B[33m";
        String purple = "\u001B[35;1m";  // Bright magenta

        // Get inventory items
        String items = "";
        if (this.getItemInventory().isEmpty()) {
            items = yellow + "Empty" + reset;
        } else {
            int count = 0;
            for (Item item : this.getItemInventory()) {
                count++;
                // Add color variation for alternating items
                String itemColor = (count % 2 == 0) ? yellow : cyan;
                items += itemColor + item.toString() + reset;

                // Add separator if not the last item
                if (count < this.getItemInventory().size()) {
                    items += " • ";
                }
            }
        }

        String inventoryLine = purple + "╠═════" + reset + " " +
                "🎒 " + purple + "Inventory" + reset + " " +
                "[ " + items + " ]" + " " +
                purple + "═════╣" + reset;

        display.println(inventoryLine);
    }

    /**
     * Determines and executes the player's turn in the game.
     * Displays attributes and inventory, then presents a menu of possible actions.
     * Handles multi-turn actions and checks if the player is still conscious.
     *
     * @param actions collection of possible actions
     * @param lastAction the action performed last turn
     * @param map the game map the player is on
     * @param display the display where the player is rendered
     * @return the action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        EnvironmentalStatusSystem system = EnvironmentalStatusSystem.getInstance();
        system.timeChanged();

        if (!this.isConscious()) {
            return new DoNothingAction();
        }

        List<Action> brewingActions = potionPouch.getBrewingActions(this);
        for (Action action : brewingActions) {
            actions.add(action);
        }
        displayAttributes(display);
        displayInventory(display);
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
}
