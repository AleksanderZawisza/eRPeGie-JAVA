package game.creature;

public class Creature {
    protected String name = "";
    protected int hp;
    protected int maxhp;
    protected int armor;
    protected int attack;
    protected float exp;
    protected int money;

    public Creature(int maxhp, int armor, int attack, float exp, int money) {
        this.hp = maxhp;
        this.maxhp = maxhp;
        this.armor = armor;
        this.attack = attack;
        this.exp = exp;
        this.money = money;
    }

    public Creature(String name, int maxhp, int armor, int attack, float exp, int money) {
        this.name = name;
        this.hp = maxhp;
        this.maxhp = maxhp;
        this.armor = armor;
        this.attack = attack;
        this.exp = exp;
        this.money = money;
    }

    public int getArmor() {
        return armor;
    }

    public void addArmor(int armor) {
        this.armor += armor;
    }

    public int getAttack() {
        return attack;
    }

    public void addAttack(int attack) {
        this.attack += attack;
    }

    public float getExp() {
        return exp;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public void addMaxhp(int maxhp) {
        this.maxhp += maxhp;
    }

    public int getHp() {
        return hp;
    }

    public void addHp(int hp) {
        this.hp += hp;
    }

    public void lowerHp(int hp) {
        this.hp -= hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
