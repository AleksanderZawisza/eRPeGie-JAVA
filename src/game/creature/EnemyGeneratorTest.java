package game.creature;

import game.item.Item;
import game.item.ItemGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class EnemyGeneratorTest {
    public static void main (String[]args) {
        Random rand = new Random();
        ArrayList testEnemies = EnemyGenerator.plains();
        Enemy testenemy = EnemyGenerator.plains().get(rand.nextInt(EnemyGenerator.plains().size()));
        System.out.println( testenemy.toString() );
        testenemy = EnemyGenerator.descriptor(testenemy);
        System.out.println(testenemy );

        //fin
        Enemy testEnemy1 = EnemyGenerator.plainsEnemy();
        System.out.println( testEnemy1 );

        //part2
        Enemy testEnemy2 = EnemyGenerator.forestEnemy();
        System.out.println( testEnemy2 );

        //ExpRange and names
        Enemy testEnemy3 = EnemyGenerator.forestEnemyName("swordsman");
        System.out.println( testEnemy3 );
        
    }
}
