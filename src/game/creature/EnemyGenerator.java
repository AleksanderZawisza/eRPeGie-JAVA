package game.creature;

import java.util.ArrayList;
import java.util.Random;

public class EnemyGenerator {
    static Random rand = new Random();

    public static ArrayList<Enemy> plains() {
        ArrayList<Enemy> plainsEnemies = new ArrayList<>();
        plainsEnemies.add(new Enemy("mouse", 4,0,5,2,3));
        plainsEnemies.add(new Enemy("rabbit", 5,0,7,3,10));
        plainsEnemies.add(new Enemy("pheasant", 9,0,10,5,12));
        plainsEnemies.add(new Enemy("goose", 10,0,13,5,12));
        plainsEnemies.add(new Enemy("rat", 10,0,11,6,5));
        plainsEnemies.add(new Enemy("snake", 20,1,19,9,8));
        plainsEnemies.add(new Enemy("wild horse", 40,2,15,11,20));
        plainsEnemies.add(new Enemy("badger", 30,5,27,13,25));
        plainsEnemies.add(new Enemy("gaggle of geese", 50,1,23,15,30));
        plainsEnemies.add(new Enemy("bison", 60,10,20,17,50));

        return plainsEnemies;
    }

    public static Enemy plainsEnemy() {
        Enemy enemy = plains().get(rand.nextInt(plains().size()));
        enemy = descriptor(enemy);
        return enemy;
    }

    public static Enemy descriptor(Enemy enemy) {
        String[] desc = {"injured", "small", "diseased",  // -EXP, -ATT/HP
                "completely average", "mediocre", "common", "typical", "plain", "boringly ordinary",
                "shaggy", "rare", // -/+MONEY
                "large", "ferocious", "rabid", // +EXP, +ATT/ARMOR/HP
                "massive",  // ++EXP, ++ATT, ++HP, +ARMOR, +MONEY
        };

        if(rand.nextInt(1001)==1000) {
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


}
