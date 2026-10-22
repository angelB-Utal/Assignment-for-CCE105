package sixsevenzre;

public class Mage extends GameCharacter {

    public Mage(String name) {
        super(name, "Mage");
        maxHealth = 80;           // fragile but sakeeett
        health = maxHealth;
    }


    public void attack() {
        addPoints(15);
        System.out.println(name + " casts a fireball! (+15)");
    }


    public void specialMove() {
        addPoints(30);
        System.out.println(name + " uses Lightning Storm! (+30)");
    }

  public int getAttackDamage()  { return 20; }
  public int getSpecialDamage() { return 35; }
  public String getAttackName()  { return "Fireball"; }
  public String getSpecialName() { return "Lightning Storm"; }
}