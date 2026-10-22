package sixsevenzre;

public class Rogue extends GameCharacter {

    public Rogue(String name) {
        super(name, "Rogue");
        maxHealth = 90;           // quick and sneaky
        health = maxHealth;
    }

    @Override
    public void attack() {
        addPoints(12);
        System.out.println(name + " backstabs from the shadows! (+12)");
    }

    @Override
    public void specialMove() {
        addPoints(28);
        System.out.println(name + " uses Shadow Strike! (+28)");
    }

    @Override public int getAttackDamage()  { return 15; }
    @Override public int getSpecialDamage() { return 30; }
    @Override public String getAttackName()  { return "Backstab"; }
    @Override public String getSpecialName() { return "Shadow Strike"; }
}