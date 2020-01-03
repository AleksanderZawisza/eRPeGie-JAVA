package game.creature;

import java.util.Random;
import java.util.HashSet;

public class EnemyGenerator {
    Random rand = new Random();

    public static HashSet<Enemy> plains() {
        HashSet<Enemy> plainsEnemies = new HashSet<>();
        plainsEnemies.add(new Enemy("mouse", 1,1,1,2,1));
        plainsEnemies.add(new Enemy("rabbit", 1,1,1,3,1));
        plainsEnemies.add(new Enemy("rat", 1,1,1,5,1));
        plainsEnemies.add(new Enemy("stork", 1,1,1,6,1));
        plainsEnemies.add(new Enemy("snake", 1,1,1,9,1));
        plainsEnemies.add(new Enemy("wild horse", 1,1,1,11,1));
        plainsEnemies.add(new Enemy("badger", 1,1,1,14,1));
        plainsEnemies.add(new Enemy("gaggle of geese", 1,1,16,1,1));
        plainsEnemies.add(new Enemy("bison", 1,1,1,18,1));

        return plainsEnemies;
    }

    public void descriptor() {
        String[] desc = {"small", "weak", "diseased",  // -EXP, -ATT/ARMOR
        "completely average", "mediocre", "common", "typical", "plain",
        "shaggy", "beautiful", // -/+MONEY
        "rabid", "large", "hardy", // +EXP, +ATT/ARMOR
        "humongous"}; // ++EXP, ++ATT, ++HP
    }

    public static void main (String[]args) {
        System.out.println( EnemyGenerator.plains().toString() );
    }

}
