package game.item;

import java.util.HashSet;

public class Items {

    static HashSet items = new HashSet();
    public static void main(String[] args) {

        items.add(new Weapon("Sword", 10, 40));
        items.add(new Weapon("Knife", 4, 15));
        items.add(new Armor("Helmet", 5, 25));
        items.add(new Armor("Shield", 8,30));

    }
}
