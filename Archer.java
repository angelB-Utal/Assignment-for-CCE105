package sixsevenzre;

public class Archer extends GameCharacter {

    public Archer(String name) {
        super(name, "Archer");
        maxHealth = 100;          // balanced ranged fighter
        health = maxHealth;
    }

    @Override
    public void attack() {
        addPoints(11);
        System.out.println(name + " fires a quick arrow! (+11)");
    }

    @Override
    public void specialMove() {
        addPoints(27);
        System.out.println(name + " calls down an Arrow Rain! (+27)");
    }

    @Override public int getAttackDamage()  { return 16; }
    @Override public int getSpecialDamage() { return 28; }
    @Override public String getAttackName()  { return "Quick Shot"; }
    @Override public String getSpecialName() { return "Arrow Rain"; }
}