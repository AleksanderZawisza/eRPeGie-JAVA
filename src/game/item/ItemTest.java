package game.item;

import game.creature.Player;
import game.item.armor.Arms;

public class ItemTest {
    public static void main(String[] args) {
        Player player = new Player(100,10,10,0,0);
        System.out.println("attack" + player.getAttack());
        System.out.println("armor" + player.getArmor());
        Weapon stick = new Weapon("Stick",10,0);
        Arms kek = new Arms("Kek", 15,1000);
        player.take(new Weapon("Stick",10,0));
        player.take(kek);
        player.equip(new Weapon("Stick",10,0));
        player.equip(kek);
        System.out.println("newattack" + player.getAttack());
        System.out.println("newarmor" + player.getArmor());


    }
}
