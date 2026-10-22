
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final Color DARK = new Color(30, 34, 48);

    private charManager manager = new charManager();
    private JTextField nameField = new JTextField(10);
    private JTextField searchField = new JTextField(8);   // character to use
    private JTextField targetField = new JTextField(8);   // character to hit
    private JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Warrior", "Mage", "Rogue", "Archer"});
    private JTextArea display = new JTextArea();
    private JProgressBar hpBar = new JProgressBar(0, 100);   // Showdown-style HP bar

    public Main() {
        setTitle("Game Character Manager");
        setSize(780, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(DARK);

        // Top
        JPanel top = new JPanel();
        top.setBackground(DARK);
        top.add(label("Name:"));
        top.add(nameField);
        top.add(typeCombo);
        top.add(btn("Create", new Color(39, 174, 96), e -> create()));

        // Center (battle log style)
        display.setEditable(false);
        display.setBackground(new Color(245, 245, 240));
        display.setForeground(new Color(30, 30, 30));
        display.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Right buttons
        JPanel right = new JPanel(new GridLayout(7, 1, 4, 4));
        right.setBackground(DARK);
        right.add(btn("View All", new Color(90, 100, 130), e -> viewAll()));
        right.add(btn("Search", new Color(52, 130, 200), e -> search()));
        right.add(btn("Update", new Color(52, 130, 200), e -> update()));
        right.add(btn("Remove", new Color(192, 57, 43), e -> remove()));
        right.add(btn("Attack", new Color(200, 80, 60), e -> doAction(true)));
        right.add(btn("Special", new Color(142, 68, 173), e -> doAction(false)));
        right.add(btn("History", new Color(90, 100, 130), e -> showHistory()));

        // Bottom
        hpBar.setStringPainted(true);
        JPanel bottom = new JPanel();
        bottom.setBackground(DARK);
        bottom.add(label("Character:"));
        bottom.add(searchField);
        bottom.add(label("Target:"));
        bottom.add(targetField);
        bottom.add(label("Target HP:"));
        bottom.add(hpBar);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(display), BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        display.setText("Create a Warrior, Mage, Rogue or Archer, then use the buttons.");
        setVisible(true);
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        return l;
    }

    private JButton btn(String text, Color color, java.awt.event.ActionListener a) {
        JButton b = new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.addActionListener(a);
        return b;
    }

    // Updates the HP bar (green > yellow > red like Pokemon Showdown)
    private void updateBar(GameCharacter c) {
        if (c == null) {
            hpBar.setValue(0);
            hpBar.setString("");
            return;
        }
        hpBar.setMaximum(c.getMaxHealth());
        hpBar.setValue(c.getHealth());
        hpBar.setString(c.getHealth() + " / " + c.getMaxHealth());
        double pct = (double) c.getHealth() / c.getMaxHealth();
        hpBar.setForeground(pct > 0.5 ? new Color(70, 200, 90)
                : pct > 0.2 ? new Color(240, 200, 40) : new Color(230, 60, 50));
    }

    // Returns an error message, or null if the name is valid
    private String checkName(String name) {
        if (name.isEmpty()) return "Please enter a name.";
        if (name.length() > 12) return "Name must be 12 characters or fewer.";
        if (!name.matches("[A-Za-z0-9 ]+")) return "Use letters, numbers and spaces only.";
        if (manager.search(name) != null) return "That name already exists.";
        return null;
    }

    private void create() {
        String name = nameField.getText().trim();
        String error = checkName(name);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        String type = (String) typeCombo.getSelectedItem();
        GameCharacter c;
        if (type.equals("Warrior")) c = new Warrior(name);
        else if (type.equals("Mage")) c = new Mage(name);
        else if (type.equals("Rogue")) c = new Rogue(name);
        else c = new Archer(name);

        manager.addCharacter(c);
        nameField.setText("");
        viewAll();
    }

    private void viewAll() {
        StringBuilder sb = new StringBuilder("=== Characters ===\n");
        int i = 1;
        for (GameCharacter c : manager.getAll()) {
            sb.append(i++).append(". ").append(c).append("\n");
        }
        display.setText(sb.length() > 20 ? sb.toString() : "No characters yet.");
    }

    private void search() {
        GameCharacter c = manager.search(searchField.getText().trim());
        display.setText(c != null ? "Found:\n" + c : "Not found");
        updateBar(c);
    }

    private void update() {
        String old = searchField.getText().trim();
        if (manager.search(old) == null) {
            display.setText("Type the character's name in the Character box first.");
            return;
        }
        String neu = JOptionPane.showInputDialog("New name:");
        if (neu == null) return;
        neu = neu.trim();
        String error = checkName(neu);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        manager.updateName(old, neu);
        searchField.setText(neu);
        viewAll();
    }

    private void remove() {
        if (manager.remove(searchField.getText().trim())) {
            updateBar(null);
            viewAll();
        } else {
            display.setText("Type the character's name in the Character box first.");
        }
    }

    private void doAction(boolean isAttack) {
        GameCharacter c = manager.search(searchField.getText().trim());   // attacker
        GameCharacter t = manager.search(targetField.getText().trim());   // target
        if (c == null || t == null) {
            display.setText("Enter a valid Character and Target name.");
            return;
        }
        if (c == t) {
            display.setText("A character can't target itself.");
            return;
        }
        if (c.isFainted() || t.isFainted()) {
            display.setText("A fainted character can't fight.");
            return;
        }

        int damage;
        String move;
        if (isAttack) {
            c.attack();                       // gains points
            damage = c.getAttackDamage();     // damage depends on character type
            move = c.getAttackName();
        } else {
            c.specialMove();
            damage = c.getSpecialDamage();
            move = c.getSpecialName();
        }
        t.takeDamage(damage);

        manager.saveScore(c);
        updateBar(t);
        display.append("\n-> " + c.getName() + " used " + move + " on " + t.getName()
                + " (-" + damage + ") | HP: " + t.getHealthBar());
        if (t.isFainted()) {
            display.append("\n" + t.getName() + " fainted! " + c.getName() + " wins!");
        } else {
            // Turn-based: swap roles so the other fighter goes next
            searchField.setText(t.getName());
            targetField.setText(c.getName());
            updateBar(c);
            display.append("\n" + t.getName() + "'s turn!");
        }
    }

    // Shows the Stack of score history, newest first
    private void showHistory() {
        StringBuilder sb = new StringBuilder("=== Score History (newest first) ===\n");
        java.util.Stack<String> h = manager.getHistory();
        for (int i = h.size() - 1; i >= 0; i--) sb.append(h.get(i)).append("\n");
        display.setText(h.isEmpty() ? "No moves yet." : sb.toString());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(Main::new);
    }
}
