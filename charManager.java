package sixsevenzre;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class charManager {

    private List<GameCharacter> characters = new ArrayList<>();
    private Stack<String> scoreHistory = new Stack<>();

    public void addCharacter(GameCharacter c) {
        characters.add(c);
    }

    public List<GameCharacter> getAll() {
        return characters;
    }

    public GameCharacter search(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean updateName(String oldName, String newName) {
        GameCharacter c = search(oldName);
        if (c == null) return false;
        c.setName(newName);
        return true;
    }

    public boolean remove(String name) {
        GameCharacter c = search(name);
        if (c != null) {
            characters.remove(c);
            return true;
        }
        return false;
    }

    public void saveScore(GameCharacter c) {
        scoreHistory.push(c.getName() + " (" + c.getType() + ") -> " + c.getScore() + " pts");
    }

    public Stack<String> getHistory() {
        return scoreHistory;
    }

    public int size() {
        return characters.size();
    }

    public boolean isEmpty() {
        return characters.isEmpty();
    }
}