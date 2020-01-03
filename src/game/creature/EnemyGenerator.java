package game.creature;

import java.util.ArrayList;
import java.util.Random;

public class EnemyGenerator {
    static Random rand = new Random();

    public static ArrayList<Enemy> plains() {
        ArrayList<Enemy> plainsEnemies = new ArrayList<>();
        plainsEnemies.add(new Enemy("mouse", 3,0,2,2,3));
        plainsEnemies.add(new Enemy("rabbit", 5,0,3,3,10));
        plainsEnemies.add(new Enemy("pheasant", 10,0,6,5,12));
        plainsEnemies.add(new Enemy("rat", 10,0,8,6,5));
        plainsEnemies.add(new Enemy("snake", 20,1,11,9,8));
        plainsEnemies.add(new Enemy("wild horse", 40,0,10,11,20));
        plainsEnemies.add(new Enemy("badger", 30,5,22,13,25));
        plainsEnemies.add(new Enemy("gaggle of geese", 50,1,18,15,30));
        plainsEnemies.add(new Enemy("bison", 60,10,15,17,50));

        return plainsEnemies;
    }

    public static Enemy plainsEnemy() {
        Enemy enemy = plains().get(rand.nextInt(plains().size()));
        enemy = descriptor(enemy);
        return enemy;
    }

    public static Enemy descriptor(Enemy enemy) {
        String[] desc = {"small", "weak", "diseased",  // -EXP, -ATT/HP
                "completely average", "mediocre", "common", "typical", "plain",
                "shaggy", "beautiful", // -/+MONEY
                "hardy", "large", "rabid", // +EXP, +ATT/ARMOR/HP
                "humongous"}; // ++EXP, ++ATT, ++HP, +ARMOR, +MONEY
        String myDesc = desc[rand.nextInt(desc.length)];
        switch(myDesc)
        {   case "small": enemy.lowerHp((int) enemy.getMaxhp()/10); enemy.lowerExp(1); break;
            case "weak": enemy.lowerAttack((int) enemy.getAttack()/10); enemy.lowerExp(1); break;
            case "diseased": enemy.lowerHp((int) enemy.getMaxhp()/10);
                             enemy.lowerAttack((int) enemy.getAttack()/10); enemy.lowerExp(1); break;
            case "shaggy": enemy.lowerMoney((int) enemy.getMoney()/10); break;
            case "beautiful": enemy.addMoney((int) enemy.getMoney()/10); break;
            case "hardy": enemy.addHp((int) enemy.getMaxhp()/10); enemy.addExp(1);
                          enemy.addArmor((int) enemy.getArmor()/10); break;
            case "large": enemy.addAttack((int) enemy.getAttack()/10); enemy.addExp(1); break;
            case "rabid": enemy.addHp((int) enemy.getMaxhp()/10);
                          enemy.addAttack((int) enemy.getAttack()/10); enemy.addExp(1); break;
            case "humongous": enemy.addHp((int) enemy.getMaxhp()/5); enemy.addArmor((int) enemy.getArmor()/5);
                              enemy.addAttack((int) enemy.getAttack()/5); enemy.addExp(2); break;
            default: break;
        }
        enemy.setRace(myDesc + " " + enemy.getRace());
        return enemy;
    }


}
