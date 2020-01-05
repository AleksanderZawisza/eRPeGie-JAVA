package game.creature;

import game.item.Item;
import game.item.Weapon;
import game.item.armor.Arms;
import game.item.armor.Head;
import game.item.armor.Legs;
import game.item.armor.Torso;

import java.util.ArrayList;
import java.util.HashSet;

public class Player extends Creature {
    public String savename = "";
    public String STATE;
    public ArrayList<Item> inventory;
    private Weapon weapon;
    private Legs legs;
    private Torso torso;
    private Arms arms;
    private Head head;
    private Weapon defaultWeapon = new Weapon("",0,0);
    private Legs defaultLegs = new Legs("",0,0);
    private Torso defaultTorso = new Torso("",0,0);
    private Arms defaultArms = new Arms("",0,0);
    private Head defaultHead = new Head("",0,0);


    public Player(int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
        this.inventory = new ArrayList<Item>();
        this.weapon = defaultWeapon;
        this.legs = defaultLegs;
        this.torso = defaultTorso;
        this.arms = defaultArms;
        this.head = defaultHead;
        this.STATE = "BEGIN";
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public void take(Item item){
        if(inventory.size() < 12){
            inventory.add(item);
            return;
        }
        System.out.println("Your inventory is full!");
    }

    public void remove(Item item){
        if (inventory.contains(item)) {
            unequip(item);
            inventory.remove(item);
        }
        else{
            System.out.println("You don't have that item.");
        }
    }

    public void equip(Item item){
        if (inventory.contains(item)) {
            if (item instanceof Weapon){
                this.setAttack(this.getAttack() - weapon.getDamage() + ((Weapon) item).getDamage());
                weapon = (Weapon) item;
            }
            else if (item instanceof Legs){
                this.setArmor(this.getArmor() - legs.getDefence() + ((Legs) item).getDefence());
                legs = (Legs) item;
            }
            else if (item instanceof Arms){
                this.setArmor(this.getArmor() - arms.getDefence() + ((Arms) item).getDefence());
                arms = (Arms) item;
            }
            else if (item instanceof Torso){
                this.setArmor(this.getArmor() - torso.getDefence() + ((Torso) item).getDefence());
                torso = (Torso) item;
            }
            else if (item instanceof Head){
                this.setArmor(this.getArmor() - head.getDefence() + ((Head) item).getDefence());
                head = (Head) item;
            }
            else{
                System.out.println("\nItem cannot be equipped!\n");
            }
        }
    }

    public void unequip(Item item){
        if (item instanceof Weapon) {
            if (item.equals(weapon)) {
                this.setAttack(this.getAttack() - weapon.getDamage());
                weapon = defaultWeapon;
            }
        }
        else if (item instanceof Legs) {
            if (item.equals(legs)) {
                this.setArmor(this.getArmor() - legs.getDefence());
                legs = defaultLegs;
            }
        }
        else if (item instanceof Torso) {
            if (item.equals(torso)) {
                this.setArmor(this.getArmor() - torso.getDefence());
                torso = defaultTorso;
            }
        }
        else if (item instanceof Arms) {
            if (item.equals(arms)) {
                this.setArmor(this.getArmor() - arms.getDefence());
                arms = defaultArms;
            }
        }
        else if (item instanceof Head) {
            if (item.equals(head)) {
                this.setArmor(this.getArmor() - head.getDefence());
                head = defaultHead;
            }
        }
        else {}
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE() {
        return STATE;
    }
}
