## 📖 About the Project

In today’s digital world, virtual pets are a fun way to simulate real-life caregiving and emotional bonding. However, most simple pet simulations don't account for behavioral diversity or personality-driven logic.

This project is a Java-based **Virtual Pet Simulator** that introduces realistic behavioral differences by assigning each pet a unique **personality** that affects its needs and responses. The game provides an interactive graphical user interface (GUI) built with **Java Swing**, and follows the **Model-View-Controller (MVC)** design pattern for clean separation of logic, display, and interaction handling.

### 🎯 Problem:
Simulating a digital pet that:
- Reacts to user actions (feeding, cleaning, playing, sleeping)
- Has changing emotional states (happy/sad)
- Can "die" if neglected
- Behaves differently depending on its personality

### 🛠️ Solution:
This program models a pet with four core needs:
- **Hunger**
- **Hygiene**
- **Social (Play)**
- **Sleep**

Each pet is randomly assigned a **personality** (e.g., Lazy, Glutton, Smart), which modifies how quickly their needs decay and how they respond to interactions. The game updates every 10 seconds and reacts to player input. If any need reaches 0, the pet dies. If any need drops below 20, the pet becomes sad.

The simulation supports:
- Real-time visual updates and interaction animations
- Status tracking (mood, health, alive state)
- Full unit test coverage with JUnit 4

## ✨ Features

### 🎮 Core Gameplay
- ✅ Take care of your virtual pet by feeding, cleaning, playing, and letting it sleep
- ✅ Each action affects multiple stats (hunger, hygiene, social, sleep)
- ✅ Needs decay over time — neglect leads to sadness or even death
- ✅ Visual animation for each interaction
- ✅ Pet mood system: mood changes dynamically (HAPPY or SAD)
- ✅ Game-over screen when your pet dies

### 🧠 Personality System
- ✅ Each pet is randomly assigned one of 7 personalities:
    - **Glutton** – Eats more, gets hungry faster
    - **Lazy** – Sleeps more, plays less
    - **Energetic** – Loves to play, drains energy fast
    - **Mysophobia** – Hygiene decays quickly, obsessed with cleanliness
    - **Needy** – Craves attention and social interaction
    - **Aloof** – Responds less intensely to care
    - **Smart** – Occasionally avoids need decay
- ✅ Personality affects:
    - How fast needs decay over time
    - How the pet responds to actions

### 🖼️ User Interface
- ✅ Built with Java Swing (no external libraries required)
- ✅ Animated pet images for idle and action states
- ✅ Custom UI: background, buttons, font, and color themes
- ✅ Status panel shows live health metrics
- ✅ Sadness hints appear when a stat is low
- ✅ Progress bar and timer for action feedback

### ✅ Technical
- ✅ Follows the **Model-View-Controller (MVC)** design pattern
- ✅ Modular code structure for scalability and readability
- ✅ Full **JUnit 4** test coverage for core logic
- ✅ Clamp function prevents stat overflow or underflow (0–100)
- ✅ Uses interfaces and enums for extensibility (e.g., moods, actions, personalities)

## 🚀 How to Run

This project is packaged into an executable JAR file named `Minimal API.jar`.  
To run the virtual pet simulator, make sure the required image assets are in the correct location relative to the JAR file.

## 📁 Project Structure

Make sure your folder looks like this:

```plaintext
your-folder/
├── res/
│   ├── Minimal API.jar
│   └── images/
│       ├── default/
│       ├── interact/
│       ├── sad/
│       └── ... (other image folders used in the game)
```

## 🕹️ How to Use the Program

Once the program starts, the pet will appear on screen along with several buttons at the bottom of the window. The game is entirely interactive using the mouse (no typing required).

### 🐶 Basic Controls

You can take care of your pet by clicking the following buttons:

| Button      | Action Description                            |
|-------------|-----------------------------------------------|
| 🧼 Shower!!  | Cleans the pet (increases hygiene)            |
| 🍗 Feed!!    | Feeds the pet (increases hunger stat)         |
| 🧸 Play!!    | Plays with the pet (increases social stat)     |
| 😴 Sleep!!   | Puts the pet to sleep (increases sleep stat)   |
| 🔄 RESET     | Resets the game and restarts with a new pet    |
| 👣 STEP      | Forces one step of time to simulate need decay |
| 📊 SHOW      | Displays the live status bar of all needs      |

> Tip: Hover your mouse over buttons to see if they’re clickable — they’ll change the cursor to a hand.

---

### 💡 Game Flow

- Every **10 seconds**, the pet’s needs will naturally decay.
- If **any need drops below 20**, the pet becomes **SAD**.
- If **any need hits 0**, the pet will **die**, and the game ends.
- When dead, the pet image will change, and all interaction buttons become disabled except RESET.

---

### 🧠 Personality System

At the top of the screen, you will see your pet's **personality label** (e.g., "GLUTTON", "LAZY").

Each personality affects how:
- Needs decay over time (faster/slower)
- The pet reacts to actions (some gain more from sleep, others from play, etc.)

You **cannot choose the personality** — it is randomly assigned when the game starts or resets.

---

### 🎭 Mood Indicator

- The mood label (`HAPPY` or `SAD`) is displayed on the top left.
- The pet’s animation changes depending on mood and whether it's interacting.
- When stats are critically low, **thought bubbles** appear (e.g., hungry, dirty, sleepy).

---

### 📈 Status Display

- Click **SHOW** to see current need values (hunger, hygiene, social, sleep).
- Click **HIDE** again to remove the status bar.

---

### 🔁 Restarting

Click **RESET** to start a new game with a new personality. The pet will be revived, and all needs will be set to 50.

## 🧱 Design / Model Changes

Over the course of development, several important design changes were made to improve scalability, modularity, and feature richness. These changes were made based on feedback, testing needs, and usability improvements.

---

### 🌀 Version 1.0 – Initial Design

- Pet had four needs: **hunger**, **hygiene**, **social**, and **sleep**
- Needs decayed over time with fixed rates
- Interactions increased corresponding stats equally
- No personality system or mood tracking
- All logic was handled in one file (monolithic design)

---

### 🧠 Version 1.1 – Personality System Added

**Change:** Introduced the `Personality` enum and `PersonalityInterface` to support multiple behavioral styles  
**Why:** To simulate more realistic and varied pet behaviors based on traits (e.g., Glutton eats more, Lazy sleeps more)

- Each personality now implements custom logic for:
    - `modifyStep()` — how needs decay passively
    - `applyPersonalityInteract()` — how the pet responds to user actions

---

### 🧱 Version 1.2 – MVC Architecture Refactor

**Change:** Refactored code into **Model-View-Controller (MVC)**  
**Why:** To separate concerns and make testing and debugging easier

- `Pet` implements the `PetInterface` (Model)
- `PetView` handles all Java Swing GUI logic (View)
- `PetController` coordinates between user inputs and model updates (Controller)

---

### 😃 Version 1.3 – Mood Tracking System

**Change:** Added mood states using `MoodEnum` (HAPPY or SAD)  
**Why:** To give players emotional feedback based on pet well-being

- Mood changes when any stat drops below 20
- Visual changes occur (mood label + thought bubbles)

---

### 🖼️ Version 1.4 – UI Enhancements

**Change:** Added animated pet images and progress bar interaction feedback  
**Why:** To improve user experience and clarity of actions

- Dynamic animation on interactions (play, feed, sleep, clean)
- Timer-based progress bar to simulate action time
- Mood and personality labels now shown in the UI

---

### 🧪 Version 1.5 – Unit Testing and Testability

**Change:** Improved modularity for testing with `JUnit 4`  
**Why:** To verify logic and ensure reliability across updates

- Added `setPersonality()` and `setMood()` methods for test injection
- Full test suite covers all personality behaviors, mood changes, health checks, and interaction logic

---

These design changes were essential to evolve the project from a simple simulation into a modular, testable, and enjoyable interactive game.

## 📌 Assumptions

The following assumptions were made during the design and implementation of this project. All assumptions are aligned with the project requirements and aim to support a consistent and enjoyable user experience.

---

### 🎮 Game Mechanics

- All pet needs (hunger, hygiene, social, sleep) range from **0 to 100**
    - Values are clamped using a `clamp()` method to ensure they stay within bounds
- Each pet begins the game with all stats initialized to **50**
- Pet mood is **HAPPY** when all needs are above 20; **SAD** when any need is ≤ 20
- Pet dies immediately if **any need reaches 0**
- The `step()` method is called every 10 seconds (or manually via the STEP button)

---

### 🧠 Personality System

- A pet is assigned a **random personality** at the start of each game (or reset)
- Personalities are represented by enum values, each with unique `modifyStep()` and `applyPersonalityInteract()` logic
- There is **no way to choose or change the personality manually** during normal gameplay
- `setPersonality()` exists only for testing purposes

---

### 🧪 Testing

- `setMood()` and `setPersonality()` were added for **unit testing control**
- All public interface methods are thoroughly tested using **JUnit 4**
- Randomness in `SmartPersonality` is tested using deterministic mocks

---

### 🖼️ User Interface

- Image resources are loaded from an `images/` directory that must exist next to the JAR file
- Animations rely on a set of images indexed numerically (e.g., `0.png`, `1.png`, etc.)
- Pet's visual representation changes with actions and mood
- Mood and personality are shown in text labels at the top-left of the GUI

---

### 💡 Miscellaneous

- No command-line arguments are required or supported
- The program is a **single-player** experience with no external input or server dependency
- The game only uses standard Java and Swing libraries (no third-party dependencies)

## ⚠️ Limitations

While the program successfully implements core gameplay features and behavior customization, a few limitations remain:

---

### 🧠 Personality Behavior

- Personalities are randomized and **not visible before the game starts**
    - The player must start the game before seeing which personality is assigned
- The `Smart` personality relies on randomness, so its behavior can feel unpredictable
    - It works correctly but is harder to observe/test in gameplay unless specifically mocked

---

### 💻 User Interface (GUI)

- The game only runs as a **desktop Java Swing application**
    - It is not compatible with mobile or web environments
- Pet image flickering may occur briefly during fast interactions or repeated actions
- Button visuals and UI scaling are not optimized for high-DPI or 4K screens
- If the `images/` directory is missing or renamed, the program may crash or show a blank pet

---

### 📂 Project Structure

- Image paths are expected to follow a strict directory format:
    - e.g., `res/images/default/`, `res/images/interact/`, etc.
    - Moving or renaming these folders will break animations

---

### 🧪 Testing

- GUI components (`PetView`) are not unit tested due to the complexity of Swing rendering
- Mood changes and pet death can only be **indirectly tested** via stat decay in model tests
- Resource loading is not tested — if an image is missing, the app will not show a warning

---

### ⏳ Features Not Implemented

- No save/load functionality for pet state between sessions
- No way to customize or configure pet stats/personality before launch
- No sound effects or music (visual-only feedback)

---

Despite these limitations, the core mechanics — including mood, need decay, personality behavior, and visual feedback — work as expected and provide a full, testable, and playable simulation experience.

## 📚 Citations

All code in this project was written independently using course materials and standard Java documentation.

All visual assets (including pet images, action animations, and UI graphics) were **originally generated by ChatGPT (OpenAI's image generation feature)** and are free for use in this project.

No external websites, libraries, or publications were referenced for implementation logic or visual content.

## 📚 Citations

The following sources were referenced during development:

- Oracle. (n.d.). *How to Use Labels*. Java Swing Tutorial. Retrieved April 10, 2025, from https://docs.oracle.com/javase/tutorial/uiswing/components/label.html

- GeeksforGeeks. (2023, August 12). *Java Swing | JProgressBar*. Retrieved April 10, 2025, from https://www.geeksforgeeks.org/java-swing-jprogressbar/

- Baeldung. (2021, March 15). *Guide to JUnit 4*. Retrieved April 10, 2025, from https://www.baeldung.com/junit-4

If a concept or snippet was adapted from an online resource, it was rewritten and integrated with understanding.
