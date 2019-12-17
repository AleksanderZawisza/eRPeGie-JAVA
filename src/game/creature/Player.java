package game.creature;

public class Player extends Creature {
    private String savename;
    public Player(int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
    }

    public Player(int maxhp, int armor, int attack, int exp, int money, String savename) {
        super(maxhp, armor, attack, exp, money);
        this.savename = savename;
    }

}
