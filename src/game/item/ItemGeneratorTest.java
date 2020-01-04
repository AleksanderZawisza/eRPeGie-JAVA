package game.item;

import java.util.ArrayList;
import java.util.Random;


public class ItemGeneratorTest {
    public static void main (String[]args) {
        Random rand = new Random();
        ArrayList testItems = ItemGenerator.items();
        Item testitem = ItemGenerator.items().get(rand.nextInt(ItemGenerator.items().size()));
        System.out.println( testitem.toString() );
        testitem = ItemGenerator.descriptor(testitem);
        System.out.println(testitem + "\n\n");

        //fin
        Item testItem1 = ItemGenerator.newItem();
        System.out.println( testItem1 );

    }
}
