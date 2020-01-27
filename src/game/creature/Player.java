package game.creature;

import game.item.*;
import game.item.armor.Arms;
import game.item.armor.Head;
import game.item.armor.Legs;
import game.item.armor.Torso;

import java.util.ArrayList;

public class Player extends Creature {
    public String savename = "";
    public ArrayList<Item> inventory;
    public Weapon weapon;
    public Legs legs;
    public Torso torso;
    public Arms arms;
    public Head head;
    private String currentQuest = "";
    private int questCount = 0;
    private int maxQuestCount = 0;
    private String whereQuest = "";
    private Weapon defaultWeapon = new Weapon("trusty stick",0,0);
    private Legs defaultLegs = new Legs("patched linen pants",0,0);
    private Torso defaultTorso = new Torso("simple linen shirt",0,0);
    private Arms defaultArms = new Arms("frayed friendship bracelet",0,0);
    private Head defaultHead = new Head("old straw hat",0,0);
    private int defaultMaxHp = 100;
    private int defaultArmor = 0;
    private int defaultAttack = 2;
    private int defaultExp = 0;
    private int defaultMoney = 0;
    private ArrayList<Item> defaultInventory = new ArrayList<>();
    public int fartmasterCount;
    private int dayCount;
    private int lastShopRestock;
    private int killCount;
    private int dailyKillCount;
    private boolean killedPlainsBoss;
    private boolean killedForestBoss;
    private boolean killedMountainsBoss;


    public Player(){
        this.maxhp = defaultMaxHp;
        this.hp = defaultMaxHp;
        this.armor = defaultArmor;
        this.attack = defaultAttack;
        this.exp = defaultExp;
        this.money = defaultMoney;
        this.inventory = defaultInventory;
        this.weapon = defaultWeapon;
        this.legs = defaultLegs;
        this.torso = defaultTorso;
        this.arms = defaultArms;
        this.head = defaultHead;
        this.dayCount = 1;
        this.lastShopRestock = 1;
        this.dayCount = 0;
        this.lastShopRestock = 0;
        this.killedPlainsBoss = false;
        this.killedForestBoss = false;
        this. killedMountainsBoss = false;
    }

    public Player(int maxhp, int armor, int attack, int exp, int money) {
        super(maxhp, armor, attack, exp, money);
        this.inventory = defaultInventory;
        this.weapon = defaultWeapon;
        this.legs = defaultLegs;
        this.torso = defaultTorso;
        this.arms = defaultArms;
        this.head = defaultHead;
        this.dayCount = 1;
        this.lastShopRestock = 1;
        this.dayCount = 0;
        this.lastShopRestock = 0;
        this.killedPlainsBoss = false;
        this.killedForestBoss = false;
        this. killedMountainsBoss = false;
    }

    public void setSavename(String savename) { this.savename = savename; }
    public String getSavename() { return savename; }

    public void take(Item item){
        if(inventory.size() < 12){
            inventory.add(item);
        }
    }

    public void remove(int i){
        if (inventory.size()-1 <= i && i>=0) {
            inventory.remove(i);
        }

    }

    public void useItem(Item item){
        if (item instanceof Weapon){
            this.setAttack(this.getAttack() - this.weapon.getDamage() + ((Weapon) item).getDamage());
            this.weapon = (Weapon) item;
        }
        if (item instanceof Legs){
            this.setArmor(this.getArmor() - this.legs.getDefence() + ((Legs) item).getDefence());
            this.legs = (Legs) item;
        }
        if (item instanceof Arms){
            this.setArmor(this.getArmor() - this.arms.getDefence() + ((Arms) item).getDefence());
            this.arms = (Arms) item;
        }
        if (item instanceof Torso){
            this.setArmor(this.getArmor() - this.torso.getDefence() + ((Torso) item).getDefence());
            this.torso = (Torso) item;
        }
        if (item instanceof Head){
            this.setArmor(this.getArmor() - this.head.getDefence() + ((Head) item).getDefence());
            this.head = (Head) item;
        }
        if (item instanceof Healing){
            setHp(Math.min(this.getHp()+((Healing) item).getRestore(), this.getMaxhp()));
        }
    }

    public int howManyItemsInInv(){
        return this.inventory.size();
    }

    public Item getItemFromInv(int i) {
        if (i<howManyItemsInInv()) return this.inventory.get(i);
        return null;
    }

    public void removeItemFromInv(int i) {
        if (i<howManyItemsInInv()) this.inventory.remove(i);
    }

    public void addItemToInv(Item item) {
        if (howManyItemsInInv()<12) this.inventory.add(item);
    }

    public void replaceItemInInvWith(int i, Item item) {
        if (i<howManyItemsInInv()) {
            this.inventory.set(i, item);
        }
    }

    public void yeetItemFromInv(int i) {
        if (i<howManyItemsInInv()) this.inventory.remove(i);
    }

    public void setEverythingToDefault(){
        this.weapon = defaultWeapon;
        this.legs = defaultLegs;
        this.torso = defaultTorso;
        this.arms = defaultArms;
        this.head = defaultHead;
        this.maxhp = defaultMaxHp;
        this.hp = defaultMaxHp;
        this.armor = defaultArmor;
        this.attack = defaultAttack;
        this.exp = defaultExp;
        this.money = defaultMoney;
        this.inventory.clear();
        this.inventory = defaultInventory;
        this.dayCount = 1;
        this.lastShopRestock = 1;
        this.killCount = 0;
        this.dailyKillCount = 0;
        this.killedPlainsBoss = false;
        this.killedForestBoss = false;
        this. killedMountainsBoss = false;
    }

    public float expWithoutLevel(){
        return (this.exp - (int) this.exp);
    }

    public void updateMaxHp() {this.maxhp = defaultMaxHp + 10* (int) getExp();}

    public int getDayCount() { return dayCount; }
    public void addOneDay() { this.dayCount += 1; }

    public int getLastShopRestock() { return lastShopRestock; }
    public void setLastShopRestock(int lastShopRestock) { this.lastShopRestock = lastShopRestock; }

    public int getKillCount() { return killCount; }
    public void addKillCount() { this.killCount += 1; }

    public int getDailyKillCount() { return dailyKillCount; }
    public void addDailyKillCount() { this.dailyKillCount += 1; }
    public void resetDailyKillCount() { this.dailyKillCount = 0; }

    public String getCurrentQuest() {
        return currentQuest;
    }
    public void setCurrentQuest(String currentQuest) {
        this.currentQuest = currentQuest;
    }

    public int getQuestCount() {
        return questCount;
    }
    public void setQuestCount(int questCount) {
        this.questCount = questCount;
    }
    public void addQuestCount(){ this.questCount += 1; }
    public void resetQuestCount(){ this.questCount = 0; }

    public void setMaxQuestCount(int maxQuestCount) {
        this.maxQuestCount = maxQuestCount;
    }
    public int getMaxQuestCount() {
        return maxQuestCount;
    }

    public void setWhereQuest(String whereQuest) {
        this.whereQuest = whereQuest;
    }
    public String getWhereQuest() {
        return whereQuest;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public Legs getLegs() {
        return legs;
    }
    public Torso getTorso() {
        return torso;
    }
    public Arms getArms() {
        return arms;
    }
    public Head getHead() {
        return head;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setLegs(Legs legs) {
        this.legs = legs;
    }
    public void setTorso(Torso torso) {
        this.torso = torso;
    }
    public void setArms(Arms arms) {
        this.arms = arms;
    }
    public void setHead(Head head) {
        this.head = head;
    }

    public boolean killedPlainsBoss() { return killedPlainsBoss; }
    public void setKilledPlainsBoss(boolean killedPlainsBoss) { this.killedPlainsBoss = killedPlainsBoss; }

    public boolean killedForestBoss() { return killedForestBoss; }
    public void setKilledForestBoss(boolean killedForestBoss) { this.killedForestBoss = killedForestBoss; }

    public boolean killedMountainsBoss() { return killedMountainsBoss; }
    public void setKilledMountainsBoss(boolean killedMountainsBoss) { this.killedMountainsBoss = killedMountainsBoss; }
}
