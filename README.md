# 🎮 CUBE LOGIC MAZE

## 🃏 Alice in Borderland – Three of Clubs Inspired Game

> 🧩 A Java Swing-based survival maze game where players navigate through rooms, encounter **Life and Death doors**, follow clues, avoid traps, and find the exit before it's too late.

---

# ✨ GAME OVERVIEW

**Cube Logic Maze** is a GUI-based Java game inspired by the **Three of Clubs** concept from *Alice in Borderland*.

The player starts in **Room #1** and moves through a grid of rooms.

Each room may contain:

- 💖 A safe path
- ☠️ A trap
- 🚪 The exit
- 💡 A clue
- 🎲 A randomly assigned Life/Death door label

### 🎯 Objective

> 🏃 **Navigate through the maze → Avoid traps → Follow clues → Reach the EXIT → Survive!**

---

# 🖥️ FEATURES

### 🎮 Interactive GUI
Built using **Java Swing** with an interactive graphical maze.

### 🗺️ Multiple Maps
Choose between:

- 🟢 **Map 1 – Beginner**
- 🔴 **Map 2 – Advanced**

### ☠️ Trap System
Certain rooms contain hidden traps. Entering a trap results in death.

### 🚪 Life / Death Doors
Doors can be displayed as:

- 💖 `LIFE`
- ☠️ `DEATH`

The displayed label can also be randomly reversed, making each decision unpredictable.

### 💡 Room Clues
Rooms provide hints to help the player make decisions.

Examples:

> `"Beware! Danger is nearby."`

> `"Exit is ahead!"`

> `"Choose wisely to survive."`

### 📊 Player Statistics
The game tracks:

- 📍 X Position
- 📍 Y Position
- 💖 Life Score
- ☠️ Death Score

### 🏆 Exit System
Reach the designated exit room to successfully complete the game.

---

# 📁 PROJECT STRUCTURE

```text
📁 DAA-CODES
│
├── 📁 p1
│   └── 👤 Player.java
│
├── 📄 🎮 CubeLogicMazeGame.java
├── 📄 🗺️ MapConfig.java
├── 📄 🚪 RoomCube.java
├── 📄 ⚙️ tempCodeRunnerFile.java
└── 📄 .gitignore
```

> ⚠️ `tempCodeRunnerFile.java` is a temporary development file and is not required for the main game.

---

# 🧩 CLASS STRUCTURE

## 👤 Player.java

Responsible for managing the player's state.

### Stores:

```text
📍 X Position
📍 Y Position
💖 Life Score
☠️ Death Score
```

### Main Functions:

- 🚶 Move player
- 📊 Display player statistics

---

## 🗺️ MapConfig.java

Stores the configuration of each maze.

### Contains:

```text
📐 Grid Size
☠️ Trap Positions
🚪 Exit Position
💡 Room Clues
```

This allows multiple maps to be created using the same game logic.

---

## 🚪 RoomCube.java

Represents an individual room in the maze.

Each room stores:

```text
☠️ Is Trap?
🚪 Is Exit?
👣 Visited?
💀 Dead End?
🔢 Room Number
💡 Clue
```

---

## 🎮 CubeLogicMazeGame.java

The main class responsible for the game.

It handles:

- 🖥️ GUI creation
- 🗺️ Maze generation
- 🚶 Player movement
- 🚪 Door decisions
- ☠️ Trap detection
- 💡 Clues
- 📊 Score updates
- 🏆 Exit detection
- 🗺️ Map selection

---

# 🧠 GAME FLOW

```text
              🎮 START GAME
                    │
                    ▼
             🗺️ SELECT MAP
                    │
                    ▼
              🏠 ROOM #1
                    │
                    ▼
          🔎 Find adjacent rooms
                    │
                    ▼
             🚪 Choose a door
               /          \
             YES            NO
              │              │
              ▼              ▼
        Enter room       Game Over
              │
       ┌──────┼──────┐
       ▼      ▼      ▼
    💖 SAFE  ☠️ TRAP  🚪 EXIT
       │      │      │
       ▼      ▼      ▼
    Continue  DEAD   🏆 WIN
```

---

# 🗺️ AVAILABLE MAPS

## 🟢 MAP 1 — BEGINNER

```text
3 × 3 Grid

☠️ Traps:
Room #2
Room #5

🚪 Exit:
Room #3
```

---

## 🔴 MAP 2 — ADVANCED

```text
3 × 3 Grid

☠️ Traps:
Multiple trap rooms

🚪 Exit:
Room #9
```

---

# 🛠️ TECHNOLOGIES USED

<p align="center">

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">

<img src="https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge">

<img src="https://img.shields.io/badge/DAA-Algorithms-purple?style=for-the-badge">

</p>

---

# 📚 CONCEPTS USED

- ☕ Java
- 🧩 Object-Oriented Programming
- 🗃️ Classes & Objects
- 🔢 2D Arrays
- 🔄 Loops
- 📋 ArrayList
- 🎲 Randomization
- 🗺️ Grid-Based Navigation
- 🔎 Search / Traversal Logic
- 🖥️ Java Swing
- ⚡ Event Handling
- 🧠 Algorithmic Problem Solving

---

# ▶️ HOW TO RUN

## 1️⃣ Clone the Repository

```bash
git clone <your-repository-url>
```

## 2️⃣ Open the Project

Open the project using:

- 💻 VS Code
- ☕ IntelliJ IDEA
- 🟩 Eclipse
- 🧑‍💻 Any Java IDE

## 3️⃣ Compile

From the project directory:

```bash
javac p1/Player.java RoomCube.java MapConfig.java CubeLogicMazeGame.java
```

## 4️⃣ Run

```bash
java CubeLogicMazeGame
```

---

# 🎯 OBJECTIVE

```text
🟢 Start at Room #1
        ↓
🔎 Explore available rooms
        ↓
💡 Use clues
        ↓
🚪 Choose your door
        ↓
☠️ Avoid traps
        ↓
🏃 Keep moving
        ↓
🚪 Reach the EXIT
        ↓
🏆 SURVIVE!
```

---

# 🎓 PURPOSE

This project demonstrates the practical application of:

- 💻 Java Programming
- 🧠 Object-Oriented Programming
- 📚 Design and Analysis of Algorithms
- 🖥️ GUI Development
- 🗺️ Grid-Based Navigation
- 🎲 Randomized Game Logic

It was created for **academic learning, DAA practice, and Java programming practice**.

---

# 👨‍💻 AUTHOR

<div align="center">

# 🚀 Manascan tcode

### 💻 Computer Science Student

**Java • DAA • Algorithms • Programming**

</div>

---

# ⭐ SUPPORT

If you found this project interesting, consider giving the repository a ⭐!

<div align="center">

# 🎮 PLAY • THINK • SURVIVE 🃏

### ☠️ Choose wisely. Not every door leads to LIFE. ☠️

</div>
