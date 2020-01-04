package game.creature;

import game.item.Item;
import game.item.ItemGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EnemyGenerator {
    static Random rand = new Random();

    protected static Enemy descriptor(Enemy enemy) {
        String[] desc = {"injured", "small", "diseased",  // -EXP, -ATT/HP
                "completely average", "mediocre", "common", "typical", "plain", "boringly ordinary",
                "shaggy", "rare", // -/+MONEY
                "large", "ferocious", "rabid", // +EXP, +ATT/ARMOR/HP
                "massive",  // ++EXP, ++ATT, ++HP, +ARMOR, +MONEY
        };

        if(rand.nextInt(201)==200) {
            enemy.addHp(100);
            enemy.addArmor(10);
            enemy.addMoney(100);
            enemy.addAttack(10);
            enemy.addExp(10);
            enemy.setName("titanic " + enemy.getRace());
            return enemy;
        }

        String myDesc = desc[rand.nextInt(desc.length)];
        switch(myDesc)
        {   case "injured": enemy.lowerHp((int) enemy.getMaxhp()/10 +2); enemy.lowerExp(1); break;
            case "small": enemy.lowerAttack((int) enemy.getAttack()/10 +1); enemy.lowerExp(1); break;
            case "diseased": enemy.lowerHp((int) enemy.getMaxhp()/10 +2);
                enemy.lowerAttack((int) enemy.getAttack()/10  +1); enemy.lowerExp(1); break;
            case "shaggy": enemy.lowerMoney((int) enemy.getMoney()/5 +1); break;
            case "rare": enemy.addMoney((int) enemy.getMoney()/5 +10); break;
            case "large": enemy.addHp((int) enemy.getMaxhp()/10 +2); enemy.addExp(1);
                enemy.addArmor((int) enemy.getArmor()/10); break;
            case "ferocious": enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            case "rabid": enemy.addHp((int) enemy.getMaxhp()/10 +2);
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            case "massive": enemy.addHp((int) enemy.getMaxhp()/5 +2); enemy.addArmor(1);
                enemy.addMoney((int) enemy.getMoney()/5 +5); enemy.addAttack((int) enemy.getAttack()/5 +1);
                enemy.addExp(2); break;
            default: break;
        }
        enemy.setName(myDesc + " " + enemy.getRace());
        return enemy;
    }

    protected static Enemy profession(Enemy enemy) {
        String[] desc = {"", "", "",  // NO CLASS
                " ranger",  //dagger
                " hunter", //knife
                " warrior",  //swords/axes/whatevs
                " swordsman",  //swords
                " spearman", //spears duh
                " lumberjack", //axe
        };

        enemy.setPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeArmor(0, enemy.getMoney()),
                ItemGenerator.newItemPriceRangeArmor(0, enemy.getMoney()),
                ItemGenerator.newItemPriceRangeArmor(0, enemy.getMoney()),
                ItemGenerator.newItemPriceRangeArmor(0, enemy.getMoney()) ));

        String myDesc = desc[rand.nextInt(desc.length)];
        switch(myDesc)
        {   case " ranger": enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeAndName(0, enemy.getMoney(), "dagger")  ));
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1);    break;
            case " hunter": enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeAndName(0, enemy.getMoney(), "knife")  ));
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            case " warrior": enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeWeapon(0, enemy.getMoney()*2)  ));
                enemy.addAttack((int) enemy.getAttack()/5 +1); enemy.addExp(2); break;
            case " swordsman":  enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeAndName(0, enemy.getMoney(), "sword")  ));
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            case " spearman":  enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeAndName(0, enemy.getMoney(), "spear")  ));
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            case " lumberjack":  enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeAndName(0, enemy.getMoney(), "axe")  ));
                enemy.addAttack((int) enemy.getAttack()/10 +1); enemy.addExp(1); break;
            default: enemy.addPossibleDrop(Arrays.asList(
                ItemGenerator.newItemPriceRangeWeapon(0, enemy.getMoney()/2)  ));  break;
        }
        enemy.setName(enemy.getRace() + myDesc);
        return enemy;
    }

    public static ArrayList<Enemy> plains() {
        ArrayList<Enemy> plainsEnemies = new ArrayList<>();
        plainsEnemies.add(new Enemy("mouse", 4,0,5,2,3));
        plainsEnemies.add(new Enemy("rabbit", 5,0,7,3,10));
        plainsEnemies.add(new Enemy("pheasant", 9,0,10,5,12));
        plainsEnemies.add(new Enemy("goose", 10,0,13,5,12));
        plainsEnemies.add(new Enemy("rat", 10,0,11,6,5));
        plainsEnemies.add(new Enemy("snake", 20,1,19,9,8));
        plainsEnemies.add(new Enemy("wild horse", 40,3,15,11,20));
        plainsEnemies.add(new Enemy("badger", 30,5,27,13,25));
        plainsEnemies.add(new Enemy("gaggle of geese", 50,3,23,15,30));
        plainsEnemies.add(new Enemy("bison", 60,10,20,17,40));

        return plainsEnemies;
    }

    public static Enemy plainsEnemy() {
        Enemy enemy = plains().get(rand.nextInt(plains().size()));
        enemy = descriptor(enemy);
        return enemy;
    }

    public static Enemy plainsEnemyName(String whatsInTheName) {
        Enemy enemy = plainsEnemy();
        int i = 0;
        while(!enemy.getName().contains(whatsInTheName))
        {   enemy = plainsEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w plainsEnemyName");
                break; }
        }
        return enemy;
    }

    public static Enemy plainsEnemyExpRangeAndName(int begin, int end, String whatsInTheName) {
        Enemy enemy = plainsEnemy();
        int i = 0;
        while(enemy.getExp()<begin || enemy.getExp()>end || !enemy.getName().contains(whatsInTheName))
        {   enemy = plainsEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w plainsEnemyExpRangeAndName");
                break; }
        }
        return enemy;
    }

    public static Enemy plainsEnemyExpRange(int begin, int end) {
        Enemy enemy = plainsEnemy();
        int i = 0;
        while(enemy.getExp()<begin || enemy.getExp()>end)
        {   enemy = plainsEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w plainsEnemyExpRange");
                break; }
        }
        return enemy;
    }

    public static ArrayList<Enemy> forestAnimals() {
        ArrayList<Enemy> forestAnimals = new ArrayList<>();
        forestAnimals.add(new Enemy("hare", 10,0,10,5,10));
        forestAnimals.add(new Enemy("deer", 20,2,10,9,20));
        forestAnimals.add(new Enemy("fox", 30,5,35,14,27));
        forestAnimals.add(new Enemy("wolverine", 50,10,30,18,25));
        forestAnimals.add(new Enemy("swarm of angry hornets", 10,20,40,20,0));
        forestAnimals.add(new Enemy("murder of crows", 40,20,40,22,20));
        forestAnimals.add(new Enemy("moose", 80,35,20,23,40));
        forestAnimals.add(new Enemy("wolf", 60,15,55,26,45));
        forestAnimals.add(new Enemy("brown bear", 100,20,40,29,50));
        forestAnimals.add(new Enemy("grizzly bear", 150,30,50,32,65));
        forestAnimals.add(new Enemy("dire wolf", 180,25,70,35,70));
        forestAnimals.add(new Enemy("cockatrice", 200,40,90,38,90));
        forestAnimals.add(new Enemy("basilisk", 250,55,100,42,100));
        forestAnimals.add(new Enemy("green wyvern", 500,100,140,50,120));
        return forestAnimals;
    }

    public static ArrayList<Enemy> forestSentients() {
        ArrayList<Enemy> forestSentients = new ArrayList<>();
        forestSentients.add(new Enemy("green goblin", 50,20,15,21,10));
        forestSentients.add(new Enemy("black goblin", 60,25,20,23,10));
        forestSentients.add(new Enemy("kobold", 80,30,30,26,30));
        forestSentients.add(new Enemy("green hobgoblin", 100,40,45,29,40));
        forestSentients.add(new Enemy("black hobgoblin", 120,45,55,31,40));
        forestSentients.add(new Enemy("gnoll", 90,40,85,32,60));
        forestSentients.add(new Enemy("lizardman", 160,60,50,34,50));
        forestSentients.add(new Enemy("green orc", 200,60,80,37,90));
        forestSentients.add(new Enemy("wood elf", 180,40,120,40,100));
        return forestSentients;
    }

    public static Enemy forestEnemy() {
        Enemy enemy = new Enemy();

        if(rand.nextInt(6)==5) {
            enemy = forestSentients().get(rand.nextInt(forestSentients().size()));
            enemy = profession(enemy);
        }

        else {
            enemy = forestAnimals().get(rand.nextInt(forestAnimals().size()));
            enemy = descriptor(enemy);
        }

        return enemy;
    }

    public static Enemy forestEnemyName(String whatsInTheName) {
        Enemy enemy = forestEnemy();
        int i = 0;
        while(!enemy.getName().contains(whatsInTheName))
        {   enemy = forestEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w forestEnemyName");
                break; }
        }
        return enemy;
    }

    public static Enemy forestEnemyExpRangeAndName(int begin, int end, String whatsInTheName) {
        Enemy enemy = forestEnemy();
        int i = 0;
        while(enemy.getExp()<begin || enemy.getExp()>end || !enemy.getName().contains(whatsInTheName))
        {   enemy = forestEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w forestEnemyExpRangeAndName");
                break; }
        }
        return enemy;
    }

    public static Enemy forestEnemyExpRange(int begin, int end) {
        Enemy enemy = forestEnemy();
        int i = 0;
        while(enemy.getExp()<begin || enemy.getExp()>end)
        {   enemy = forestEnemy(); i++;
            if(i==10000) {
                System.out.println("uwaga: prawdopodobnie nieskonczona petla w forestEnemyExpRange");
                break; }
        }
        return enemy;
    }


}
