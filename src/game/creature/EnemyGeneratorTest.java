package game.creature;

import java.util.ArrayList;
import java.util.Random;


public class EnemyGeneratorTest {
    public static void main (String[]args) {
        Random rand = new Random();
        ArrayList testEnemies = EnemyGenerator.plains();
        Enemy testenemy = EnemyGenerator.plains().get(rand.nextInt(EnemyGenerator.plains().size()));
        System.out.println( testenemy.toString() );
        testenemy = EnemyGenerator.descriptor(testenemy);
        System.out.println(testenemy + "\n\n");

        //fin
        Enemy testEnemy = EnemyGenerator.plainsEnemy();
        System.out.println( EnemyGenerator.plainsEnemy() );


    }
}
