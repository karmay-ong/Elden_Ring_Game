# FIT2099 Assignment (Semester 1, 2025)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```
## Contribution Log
[Contribution Log](https://docs.google.com/spreadsheets/d/1gY31ceq3zFlml2lFmm20qJjxKAHnyYOaSGsYnuyJ8es/edit?gid=1582995291#gid=1582995291)


# ASSIGNMENT 3 IMPLEMENTATION
## 🌍 Introduction: Weather Madness

Welcome to the wild and wondrous lands of *Elden Thing*, where the skies are as unpredictable as the creatures that roam beneath them. One moment, the sun gently warms your journey. The next, you're drenched in acidic rain, torch extinguished, health slipping away. Here, **the weather isn’t just a backdrop—it’s a living system, one that tests your preparation, resilience, and strategy**.

☀️❄️🌧️ **Sun don't wait. Snow doesn't ask. Acid rain doesn’t care.**

To bring this brutal realism to life, we’ve built a **dynamic Weather System** that transforms each turn into a tactical choice. Will you brace for the cold with a flickering torch? Or risk it all, umbrella snapped open, praying it lasts just one more tick of acid rain?

This system is powered by an elegant architecture:

- A **Singleton Weather Controller** tracks and transitions weather based on time and probability.
- Modular **WeatherEffect** subclasses define unique gameplay impacts—like freezing players or corroding their health.
- Limited-use **protection items** (Torch, Umbrella) give players a fighting chance—but only for so long.

Much like the spirit goats and golden beetles of Limveld choose their behaviors each turn, the **weather itself chooses how to act**—sometimes predictably, sometimes chaotically. And you, brave player, must respond.

Designed with scalability in mind (and more than a few sneaky surprises for future expansion), this system blends **gameplay challenge** with **object-oriented elegance**, ensuring that your next encounter with the sky is never just "bad weather"—it’s a new kind of boss fight.

---

## UML Diagram
![Weather Madness UML](docs/design/assignment3/Req3/diagram_readme_req3.png)

## 🌦️ Weather System Core Classes

🎮 **System Controller**

🔄 **EnvironmentalStatusSystem** (Singleton)

- Controls weather transitions and effects
- Manages weather duration and change probability
- Notifies player of weather changes
- Applies weather effects each turn

---

## 🌪️ Weather Effects

📋 **WeatherEffect** (Abstract)

- Base class for all weather effects
- Defines interface for weather-specific effects
- Manages effect application logic
- Provides weather status information

🌨️ **SnowEffect**

- Reduces player temperature each turn
- Causes unconsciousness if temperature is unsafe(If player's temperature is <= 12 or >= 50)
- Can be countered by Torch item

☔ **AcidRainEffect**

- Deals damage to player each turn
- Extinguishes lit torches
- Can be blocked by Umbrella item

☀️ **WarmEffect**

- Normal weather conditions
- No negative effects on player

---

# 💡 Additional Classes to Support

## 🎒 Protection Items

🔥 **Torch**

- Provides warmth during snow
- Limited to 3 uses
- Can be extinguished by acid rain

☂️ **Umbrella**

- Protects against acid rain
- Can be opened/closed
- Limited to 3 uses

---

## ⚔️ Actions

🔥 **UseTorchAction**

- Handles torch lighting/extinguishing
- Manages torch use count
- Provides warmth effect

☔ **UseUmbrellaAction**

- Controls umbrella opening/closing
- Manages umbrella use count
- Provides rain protection

---

## 🔄 System Flow

**Initialization**

- EnvironmentalStatusSystem created with player and map
- Initial weather effect selected

**Per Turn Processing**

- Weather duration tracked
- Weather changes checked (60% chance after 5 turns)
- Current weather effect applied to player

**Protection Items**

- Players can use Torch or Umbrella
- Items provide temporary protection
- Limited use counts tracked

**Effect Application**

- Weather effects check for protection
- Unprotected players receive damage/effects
- Status messages displayed to player

---

## 🎯 Key Features

- **Weather Variety:** Three distinct weather types
- **Protection System:** Multiple protection items
- **Limited Resources:** Item use restrictions
- **Visual Feedback:** Colored weather messages
- **Dynamic Changes:** Random weather transitions

---

## 📚 Case Scenario

### ❄️ Snowstorm Survival with Torch

**Context:**

The player enters a cold biome while the **SnowEffect** is active.

**Progression:**

- **Turn 1:** Weather message displays: “heavy snow rages...” Player’s temperature drops.
- **Turn 4:** Player can use the **Torch** to restore temperature.
- **Turn 5–6:** Snow continues. Player stays warm with the Torch, but Torch’uses decrease.
- **Turn 7:** Torch is depleted. If not protected, the player becomes unconscious due to hypothermia.

**Outcome:**

Demonstrates the dynamic interaction between **SnowEffect** and the **Torch**, highlighting the need for timely protection and resource management.

---

## REQ4: Witch Mayhem of Potions

🧪 Witch Mayhem of Potions is a chaotic, strategic alchemy system where every brew holds power—and danger. As a battle-ready alchemist, you gather rare ingredients like 🐐 blessed goat meat or 🪲 cursed beetle flesh to craft potions that can change the tide of battle. Drink them for personal boosts or 🎯 throw them for devastating area control. Behind the scenes, smart abstractions like Potion and Meat handle interactions, while 💜 Healing, 🖤 Poison, and ❤️ Crazy potions unleash turn-based effects that heal, harm, or trigger unpredictable chaos. With ⚗️ limited resources, a 💼 brewing pouch, and a 🌊 dynamic world full of tactical choices, every drop you brew could mean survival—or spectacular mayhem.


## UML Diagram
![Witch Mayhem of Potions UML](docs/design/assignment3/Req4/diagram_readme_req4.png)

### 1. Base Classes & Abstractions

### 🧪 Potion (Abstract)

- Base for all potions
- Manages drinking/throwing mechanics
- Handles action creation

### 🥩 Meat

- Base class for creature materials
- Can have BLESSED or CURSED capabilities
- Specific meat types from different creatures


### 💧 WaterBucket

- Ingredient with the **DRINKABLE** capability
- Allows for collecting and using water

---

### 2. Potion Types

### 💜 HealingPotion

- Restores health when consumed or thrown
- Applies a timed healing effect

### 🖤 PoisonPotion

- Inflicts damage when consumed or thrown
- Applies a timed poison effect

### ❤️ CrazyPotion

- Temporarily increases max health or induces unpredictable behavior
- Applies a timed effect

---

### 4. Effects

### 💚 HealingEffect

- Restores health over time

### 💀 PoisonEffect

- Deals damage over time

### 🌟 CrazyEffect

- Alters actor behavior or stats for a duration

---

### 5. Actions

### 🥤 DrinkPotionAction

- Lets actors drink potions
- Executes healing, poisoning, or crazy effects

### 🎯 ThrowPotionAction

- Enables potion throwing
- Affects area or target with potion effect

### 🧪 BrewPotionAction

- Unified brewing action managed through Pouch system

- Pouch validates ingredient combinations

- Creates appropriate potion based on available ingredients

- Consumes ingredients upon successful brewing

- Determines potion type based on ingredient capabilities:

  - BLESSED + DRINKABLE → Healing Potion

  - CURSED + DRINKABLE → Poison Potion

  - 3x BLESSED + 2x DRINKABLE → Crazy Potion




### 🪣 CollectWaterAction

- Collects water into a container (e.g., bucket)

### 🌊 CreatePondAction

- Uses water to create a pond in the environment

---

### 6. Environment & Systems

### 💼 Pouch

- Stores ingredients
- Verifies ingredient requirements
- Provides potion brewing actions

### 🌊 Pond

- A terrain feature for interacting with water
- Enables water-based actions

---

### 🔄 System Flow

#### 1. Ingredient Management
- Players collect **blessed meats** from creatures.
- **Water** can be gathered from ponds using the `WaterBucket`.
- Items are stored in the player's inventory with appropriate **Status** tags:
  - `BLESSED`
  - `CURSED`
  - `DRINKABLE`

#### 2. Brewing Process
- The **Pouch** checks the inventory for required `Status` combinations.
- Valid brewing actions become available when all requirements are met.
- Ingredients are **consumed** upon successful brewing.

#### 3. Potion Usage
Each potion provides **two tactical usage options**:

- **Drink** *(stronger effect on single target - self)*:
  - **Healing Potion**: Restores **20 HP per turn**
  - **Poison Potion**: Deals **15 damage per turn**
  - **Crazy Potion**: Grants **double max HP**

- **Throw** *(area effect on multiple targets)*:
  - **Healing Potion**: Restores **10 HP per turn** to all in area
  - **Poison Potion**: Deals **5 damage per turn** to all in area
  - Affects the **center tile** and all **adjacent tiles**

#### 4. Effect Resolution
- Effects are applied via the **StatusEffect system**.
- Each effect **ticks independently** every turn.
- Effects **automatically expire** when their duration ends.
- **Multiple effects can stack** on a single target.


---

## 🌟 Key Features

- **Versatile Effects**: Multiple potion types with unique mechanics
- **Area Effects**: AoE application through throwing
- **Crafting System**: Rich and expandable brewing mechanics
- **Status Management**: Turn-based, reusable effects
- **Resource Management**: Strategic use of ingredients and potion types

---

### 🎯 Scenario: Poison Cloud Ambush

**Context:**

The party of adventurers—Lyria the Ranger, Torvik the Warrior, and Nyssa the Mage—enters a narrow canyon known as the Serpent’s Gorge. Rumor has it that a band of venomous rattler-lizards nests here, ready to strike. The party’s last vial is a **Poison Potion**, intended for area denial rather than single-target use.

- **Player Inventory:**
    - 1× Poison Potion
    - 1× Healing Potion
    - Basic weapons and light armor
- **Map Setup:**
    - A winding corridor, 3 tiles wide, lined with rocky outcrops.
    - Enemy rattler-lizards patrol in a cluster near the midpoint.

---

## 🎮 Turn 1: The Tactical Setup

**Player Action:**
- Throws **Poison Potion** into the narrow corridor
- Creates a **3x3 toxic barrier** between the party and enemies

**Environment Effect:**
- **Purple mist** spreads across the corridor
- Covers the **choke point** where rattler-lizards are clustered

**Enemy Status:**
- **Three rattler-lizards** caught in the initial poison cloud
- Each affected enemy takes **5 damage**
- **Two enemies** begin showing signs of poisoning

---

## 🎮 Turn 2: The Poison Spreads

**Poison Effect:**
- Poison cloud **remains active** in the corridor
- Affected enemies take a **second wave of damage** (**10 total**)
- **Visible weakening** of poisoned rattler-lizards

**Enemy Response:**
- **Two unaffected** rattler-lizards attempt to **circle around**
- Poisoned enemies **struggle to maintain formation**
- **One rattler-lizard retreats** from the poison area

**Party Position:**
- Maintains **defensive formation** behind the poison cloud
- Prepares for **potential flanking maneuvers**

---

## 🎮 Turn 3: The Counter Attack

**Combat Situation:**
- **Flanking rattler-lizards** reach the party’s position
- **One party member** takes **significant damage**
- Poison continues affecting trapped enemies (**15 total damage**)

**Player Response:**
- Uses **Healing Potion** for emergency recovery
- Gains **20 HP** instantly
- Repositions for a **stronger defensive stance**

**Battlefield State:**
- Poison cloud continues to **deny the central corridor**
- Weakened enemies **split between retreat and advance**
- Party gains a **healing advantage** for upcoming rounds

---

### 🔍 Outcome

- **Optimal Use of AoE:** Throwing the **Poison Potion** at the clustered rattler-lizards inflicted area damage, rapidly thinning their numbers.
- **Resource Trade-Off:** Nyssa sacrificed a single-use Poison Potion to neutralize multiple threats, preventing a more dangerous melee.
- **Strategic Positioning:** By throwing from a safe distance, the party avoided direct engagement with all three enemies at once.
- **Follow-Up Actions:** Torvik capitalized on the lizard’s stagger to eliminate the last foe before it could bite again.

[Approved by TAs.]