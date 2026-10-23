# Game Character Manager

This Java Swing app lets you put together a team of game characters and pit them against each other in turn-based duels. It improves on the earlier java-game-character project, a console program whose main method simply created a Warrior and a Mage and printed what they did.

## How the project follows the instructions

- **Abstraction:** `GameCharacter` is an abstract class with abstract methods like `attack()` and `specialMove()`. Warrior, Mage, Rogue and Archer each write their own version.
- **Abstract Data Type:** `charManager` keeps the characters in a List and the score history in a Stack.
- **GUI:** The window is made with Java Swing (text boxes, buttons, a dropdown, a log and an HP bar).
- **User input:** You can create, view, search, rename and remove characters, and the program checks what you type.

## The fighters

| Class | HP | Attack damage | Special damage |
|---|---|---|---|
| Warrior | 120 | 12 | 25 |
| Mage | 80 | 20 | 35 |
| Rogue | 90 | 15 | 30 |
| Archer | 100 | 16 | 28 |

## Buttons

- **Create:** adds a character using the name box and the class dropdown.
- **View All:** shows every character with its HP and score.
- **Search:** finds the character typed in the Character box.
- **Update:** renames that character.
- **Remove:** deletes that character.
- **Attack / Special:** the Character hits the Target.
- **History:** shows past scores, newest first.

## How a duel works

1. Type who is fighting in the **Character** box and who is being hit in the **Target** box.
2. Press Attack or Special. The Target loses HP and the Character earns points.
3. The two names swap, so the other fighter goes next.
4. A character at 0 HP faints and the other one wins.

## Error handling

- Names must be 1 to 12 characters, with only letters, numbers and spaces.
- Two characters cannot share a name ("Zed" and "zed" count as the same).
- If a name is not found, the program says so instead of crashing.
- A duel will not start if a name is missing, a character targets itself, or someone has fainted.

## What is new

- A Swing window instead of console output.
- Create, search, rename and remove characters while the program runs.
- Four character types instead of two.
- Turn-based duels with HP.
- A score history.
