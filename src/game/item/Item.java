package game.item;

import java.util.Objects;

// TODO isEquipped ?

public class Item {
    private String name;
    private int price;
    public boolean isEquipped;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.isEquipped = false;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void equip() {
        this.isEquipped = true;
    }

    public void unequip() {
        this.isEquipped = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getPrice() == item.getPrice() &&
                isEquipped == item.isEquipped &&
                Objects.equals(getName(), item.getName());
    }
    @Override
    public int hashCode() {
        return 0;
    }
}