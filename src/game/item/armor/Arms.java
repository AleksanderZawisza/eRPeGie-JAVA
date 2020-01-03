package game.item.armor;

import game.item.Armor;

public class Arms extends Armor {
    public Arms(String name, int defence, int price) {
        super(name, defence, price);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
