package game.scenarios;

import game.creature.Player;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Shop {
    public void go(Player player) { // generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou go to the SHOP. The SHOPKEEPER seems [EMOTION] when he sees you. He asks if you're interested in BUYING or SELLING.\n" +
                "You have " + player.getMoney() + " GOLD COINS on you.\n" +
                "You say that you want to\n" +
                "1. BUY stuff\n" +
                "2. SELL stuff\n" +
                "3. GET OUT of here\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("BUY"); break;
            case "2": player.setSTATE("SELL"); break;
            case "3": player.setSTATE("TOWN_LOOK"); break;}
    }
    public void buy(Player player) throws InterruptedException { // sklep kupowanie + generator opisów DO ZAIMPLEMENTOWANIA

        System.out.print("\nYou want to BUY SOMETHING. You can't do that because there aren't any ITEMS in the GAME (yet).\n" +
                "You have " + player.getMoney() + " gold coins on you.\n" +
                "You GET OUT of here.\n");
        TimeUnit.MILLISECONDS.sleep(3000);

        player.setSTATE("TOWN_LOOK");
    }

    public void sell(Player player) throws InterruptedException { // sklep sprzedawanie + generator opisów DO ZAIMPLEMENTOWANIA

        System.out.print("\nYou want to SELL SOMETHING. You can't do that because there aren't any ITEMS in the GAME (yet).\n" +
                "You have " + player.getMoney() + " gold coins on you.\n" +
                "You GET OUT of here.\n");
        TimeUnit.MILLISECONDS.sleep(3000);

        player.setSTATE("TOWN_LOOK");
    }
}
