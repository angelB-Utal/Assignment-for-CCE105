# Assignment-for-CCE105
Just an expanded version of the previous code of our professor
# Game Character Manager

This Java Swing system lets you put together a team of game characters and pit them against each other in turn-based duels. It improves on the earlier java-game-character project, a console program whose main method simply created a Warrior and a Mage and printed what they did.

## How the project follows the instructions

### Abstraction
`GameCharacter` is an abstract class. It stores the data shared by all fighters (name, type, HP, max HP, score) and declares six abstract methods that each class must fill in on its own: `attack()`, `specialMove()`, `getAttackDamage()`, `getSpecialDamage()`, `getAttackName()` and `getSpecialName()`.

The duel code in `Game` never asks what class a fighter is. It simply calls those methods, and Java runs the version that belongs to the real object, which is polymorphism.

### Abstract Data Type
`charManager` hides how data is stored. Characters live in a `List` (implemented with an `ArrayList`), and the score history lives in a `Stack`. The window talks only to the manager's methods (`addCharacter`, `search`, `updateName`, `remove`, `saveScore`, `getHistory`). Each move pushes an entry onto the stack, and History reads the stack from the top down, which is why the newest entry appears first.

### Graphical user interface
The interface uses Swing components: `JFrame`, `JTextField`, `JComboBox`, `JButton`, `JTextArea` and `JProgressBar`, arranged with `BorderLayout` and `GridLayout`. The colors and the HP bar give it the Showdown feel.

### User input
Users interact through text boxes, a class dropdown, buttons and dialog windows. `checkName()` validates names for both Create and Update, and `doAction()` validates the two fighters before any damage is applied.

## The fighters

| Class | HP | Basic move (damage / points) | Special move (damage / points) |
|---|---|---|---|
| Warrior | 120 | Sword Slash (12 / 10) | Shield Bash (25 / 25) |
| Mage | 80 | Fireball (20 / 15) | Lightning Storm (35 / 30) |
| Rogue | 90 | Backstab (15 / 12) | Shadow Strike (30 / 28) |
| Archer | 100 | Quick Shot (16 / 11) | Arrow Rain (28 / 27) |

Warriors can take the most punishment, Mages deal the most damage but have the least HP, and Rogues and Archers fall in the middle.

## Controls guide

| Button | What it does |
|---|---|
| Create | Adds a new character using the Name box and the class dropdown |
| View All | Prints the whole roster with each character's HP and score |
| Search | Looks up the name in the Character box and shows its HP bar |
| Update | Renames the character in the Character box (its class stays the same) |
| Remove | Deletes the character in the Character box |
| Attack | The Character uses its basic move on the Target |
| Special | The Character uses its special move on the Target |
| History | Lists the recorded scores, newest first |

The **Character** box holds whoever is acting, and the **Target** box holds whoever is being hit. The HP bar at the bottom always shows the Target.

## Duel flow
1. Enter the two names, then press Attack or Special.
2. The Target loses HP, the Character gains points, and the log reports the move, the damage and the Target's remaining HP.
3. The two boxes swap names, so the opponent acts next.
4. When a Target reaches 0 HP it faints, a winner is announced, and the duel ends.

The HP bar is green above 50% HP, yellow above 20%, and red below that.

## Error handling
Every action checks its input before changing anything:
- **Names** must be 1 to 12 characters long and use only letters, numbers and spaces.
- **Duplicates** are blocked, and capitalization is ignored, so "Zed" and "zed" are the same name.
- **Lookups** (Search, Update, Remove) report when no character has the name you typed.
- **Duels** are refused when a name is missing or unknown, when a character is aimed at itself, or when either fighter has already fainted.
- Name problems show up in a pop-up window. Lookup and duel problems show up in the battle log. In both cases the program carries on normally.

## What is new
- Characters can be created, listed, searched, renamed and removed while the program is running.
- Four character classes instead of two, each with its own HP, damage and move names.
- Two-fighter duels with turns, damage, points and fainting.
- A score history that remembers every move.
- Checks on every input, with messages instead of crashes.
