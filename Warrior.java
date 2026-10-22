package sixsevenzre;

public class Warrior extends GameCharacter {

    public Warrior(String name) {
        super(name, "Warrior");
        maxHealth = 120;          // tanky
        health = maxHealth;
    }


    public void attack() {
        addPoints(10);
        System.out.println(name + " swings a mighty sword! (+10)");
    }

  
    public void specialMove() {
        addPoints(25);
        System.out.println(name + " uses Shield Bash! (+25)");
    }

public int getAttackDamage()  { return 12; }
public int getSpecialDamage() { return 25; }
public String getAttackName()  { return "Sword Slash"; }
public String getSpecialName() { return "Shield Bash"; }
}