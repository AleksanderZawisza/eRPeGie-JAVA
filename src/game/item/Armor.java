package game.item;

import game.item.Item;

public class Armor extends Item {

    private final int defence;

    public Armor(String name, int defence, int price) {
        super(name, price);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
