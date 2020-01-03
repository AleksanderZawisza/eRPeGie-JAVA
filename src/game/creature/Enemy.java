package game.creature;

public class Enemy extends Creature {
    protected String race = "";

    public Enemy(int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
    }

    public Enemy(String race, int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
        this.race = race;
    }

    public Enemy(String race, String name, int maxhp, int armor, int attack, int exp, int money) {
        super(name, maxhp, armor, attack, exp, money);
        this.race = race;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "race='" + race + '\'' +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", maxhp=" + maxhp +
                ", armor=" + armor +
                ", attack=" + attack +
                ", exp=" + exp +
                ", money=" + money +
                '}';
    }
}
