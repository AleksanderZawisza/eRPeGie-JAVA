package adventure;

public final class Player {

    private int hp;
    private String weapon;
    private String position;

    public Player(int hp, String weapon) {
        this.hp = hp;
        this.weapon = weapon;
        this.position = null;
    }

    public int getHP() {
        return hp;
    }

    public String getPosition() {
        return position;
    }

    public String getWeapon() {
        return weapon;
    }

    public void receiveDamage(int damage) {
        hp -= damage;
    }

    public void receiveHealing(int healing) {
        hp += healing;
    }

    public void movePosition(String position) {
        this.position = position;
    }

    public void equipWeapon(String weapon) {
        this.weapon = weapon;
    }

}
