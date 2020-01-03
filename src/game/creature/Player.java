package game.creature;

public class Player extends Creature {
    public String savename;
    public boolean isAlive = true;

    public Player(int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

}
