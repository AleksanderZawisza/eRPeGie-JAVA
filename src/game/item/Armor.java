package game.item;

import game.item.Item;

import java.util.Objects;

public class Armor extends Item {

    private final int defence;

    public Armor(String name, int defence, int price) {
        super(name, price);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Armor)) return false;
        if (!super.equals(o)) return false;
        Armor armor = (Armor) o;
        return getDefence() == armor.getDefence();
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
