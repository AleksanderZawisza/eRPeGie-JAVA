package game.item;

public class Weapon extends Item {
    private final int damage;

    public Weapon(String name, int damage, int price) {
        super(name, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
