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
- ✅ Fully self-contained JAR — no need to distribute image folders separately
- ✅ Modular code structure for scalability and readability
- ✅ Full **JUnit 4** test coverage for core logic
- ✅ Clamp function prevents stat overflow or underflow (0–100)
- ✅ Uses interfaces and enums for extensibility (e.g., moods, actions, personalities)

## 🚀 How to Run

This project is packaged into a self-contained executable JAR file: `Minimal API.jar`.  
All image assets are embedded directly into the JAR file, so no additional image folders are required at runtime.

---

### 🧱 Prerequisites

- Java 8 or higher installed on your system
- Any operating system that supports Java (Windows, macOS, Linux)

---

### ▶️ Run by Double-Click

1. Locate the `Minimal API.jar` file (e.g., in your `res/` or output folder)
2. Double-click it
3. The game window should launch automatically 🎉

> ⚠️ If nothing happens, make sure `.jar` files are associated with the Java runtime on your system.

---

### 💻 Run from Terminal (Alternative)

If double-click doesn’t work, you can run it manually:

```bash
java -jar "Minimal API.jar"
```

## 📁 Project Structure

Make sure your folder looks like this:

```plaintext
your-folder/
├── resources/            ← Resources are now embedded in the JAR
│   └── images/
│       ├── default/
│       ├── interact/
│       ├── sad/
│       └── ...
├── res/
│   └── Minimal API.jar   ← JAR file
├── src/                  ← Java source code
├── test/
│   └── pet               ← JUnit4 test code
│       ├── PetTest/
│       └── ...
```

## 🕹️ How to Use the Program

Once the program starts, the pet will appear on screen along with several buttons at the bottom of the window. The game is entirely interactive using the mouse (no typing required).

### 🐶 Basic Controls

You can take care of your pet by clicking the following buttons:

| Button      | Action Description                             |
|-------------|------------------------------------------------|
| 🧼 Shower!! | Cleans the pet (increases hygiene)             |
| 🍗 Feed!!   | Feeds the pet (increases hunger stat)          |
| 🧸 Play!!   | Plays with the pet (increases social stat)     |
| 😴 Sleep!!  | Puts the pet to sleep (increases sleep stat)   |
| 🔄 RESET    | Resets the game and restarts with a new pet    |
| 👣 STEP     | Forces one step of time to simulate need decay |
| 📊 SHOW     | Displays the live status bar of all needs      |

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

The following assumptions were made during the design and development of this project. All are consistent with project requirements and contribute to a clear, self-contained game experience.

---

### 🎮 Game Logic

- The pet has four core needs: **hunger**, **hygiene**, **social**, and **sleep**
- All stats range from **0 to 100**, and are clamped to stay within this range
- The game starts with all stats initialized to **50**
- A new "step" (time unit) occurs every 10 seconds or manually via the STEP button
- If **any stat drops to 0**, the pet dies
- If **any stat is 20 or lower**, the pet becomes **SAD**
- The pet is considered **HAPPY** only when all needs are above 20

---

### 🧠 Personality System

- Pets are assigned a **random personality** (e.g., Lazy, Energetic, Glutton) on game start or reset
- Personality affects:
  - Passive stat decay (`step()`)
  - The effect of user interactions (`interactWith()`)
- The player cannot manually select or change a pet’s personality during gameplay
- A `setPersonality()` method exists only for testing purposes

---

### 🖼️ Resources and UI

- All images are **embedded in the JAR** using IntelliJ’s Resources Root
- Image resources are loaded via `getClass().getResource(...)`
- The game window uses fixed dimensions and is not resizable
- Thought bubble hints are shown when stats fall below the mood threshold
- Visual feedback (progress bar, animations) helps indicate action success

---

### 🧪 Testing

- Testing is focused on the **model and controller layers**
- GUI components are not unit tested due to the complexity of Swing
- `setMood()` and `setPersonality()` are exposed only for test injection

---

### 💻 Runtime

- No command-line arguments are required or supported
- The JAR is fully self-contained and portable across platforms with Java installed

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

All source code in this project was written independently using standard Java libraries and course-provided materials.

All visual assets (including pet animations, interaction images, and UI icons) were **generated using ChatGPT's image generation feature** and are free to use within this project.

No external libraries, tutorials, or websites were used in the development or implementation of this program.

## 📚 Citations

The following sources were referenced during development:

- Oracle. (n.d.). *How to Use Labels*. Java Swing Tutorial. Retrieved April 10, 2025, from https://docs.oracle.com/javase/tutorial/uiswing/components/label.html

- GeeksforGeeks. (2023, August 12). *Java Swing | JProgressBar*. Retrieved April 10, 2025, from https://www.geeksforgeeks.org/java-swing-jprogressbar/

- Baeldung. (2021, March 15). *Guide to JUnit 4*. Retrieved April 10, 2025, from https://www.baeldung.com/junit-4

If a concept or snippet was adapted from an online resource, it was rewritten and integrated with understanding.
