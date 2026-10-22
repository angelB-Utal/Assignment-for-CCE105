package sixsevenzre;

public abstract class GameCharacter {
    protected String name;
    protected int score;
    protected String type;
    protected int health;
    protected int maxHealth;

    public GameCharacter(String name, String type) {
        this.name = name;
        this.type = type;
        this.score = 0;
        this.maxHealth = 100;
        this.health = 100;
    }

    public abstract void attack();
    public abstract void specialMove();

    // Each character type defines its own damage and move names
    public abstract int getAttackDamage();
    public abstract int getSpecialDamage();
    public abstract String getAttackName();
    public abstract String getSpecialName();

    public boolean isFainted() { return health <= 0; }

    public void addPoints(int points) {
        this.score += points;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    public String getType() { return type; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    // Simple text health bar
    public String getHealthBar() {
        int bars = health / 10;
        String bar = "";
        for (int i = 0; i < bars; i++) bar += "█";
        for (int i = bars; i < 10; i++) bar += "░";
        return bar + " " + health + "/" + maxHealth;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + name + "  |  HP: " + getHealthBar() + "  |  Score: " + score;
    }
}