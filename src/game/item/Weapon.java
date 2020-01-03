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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weapon)) return false;
        if (!super.equals(o)) return false;
        Weapon weapon = (Weapon) o;
        return getDamage() == weapon.getDamage();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
