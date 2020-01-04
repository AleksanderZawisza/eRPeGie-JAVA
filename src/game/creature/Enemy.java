package game.creature;

import game.item.Item;

import java.util.List;
import java.util.Random;



import java.util.ArrayList;

public class Enemy extends Creature {
    protected String race = "";
    protected List<Item> possibleDrop = new ArrayList<Item>();
    static Random rand = new Random();

    public Enemy(){}

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

    public Enemy(String race, int maxhp, int armor, int attack, int exp, int money, List<Item> possibleDrop) {
        super(maxhp, armor, attack, exp, money);
        this.race = race;
        this.possibleDrop = possibleDrop;
    }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public List<Item> getPossibleDrop() { return possibleDrop; }
    public void setPossibleDrop(List<Item> possibleDrop) { this.possibleDrop = possibleDrop; }
    public void addPossibleDrop(List<Item> addedDrop) {
        List merged = new ArrayList(this.possibleDrop);
        merged.addAll(addedDrop);
        this.possibleDrop = merged;
    }

    public Item getRandomDrop() {
        Item drop = this.possibleDrop.get(rand.nextInt(this.possibleDrop.size()));
        return drop;
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
                ", possibleDrop=" + possibleDrop +
                '}';
    }
}
