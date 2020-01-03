package game.item;

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

}