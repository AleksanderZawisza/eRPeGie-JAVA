package game.scenarios;

import game.creature.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tavern {

    public void go(Player player) { // generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou manage to find the TAVERN. You see [RANDOM PERSON/GROUP] in the corner. The barmaid looks [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "It's time to\n" +
                "1. Buy yourself a STIFF DRINK\n" +
                "2. Get a room and HIT THE HAY\n" +
                "3. Get OUT OF here\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("DRINK"); break;
            case "2": player.setSTATE("REST"); break;
            case "3": player.setSTATE("TOWN_LOOK"); break;}
    }

    public void drink(Player player) { // questy/potrzeba pieniedzy do kupowania alko + generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou get yourself a [DRINK]. It tastes [TASTE]. [GENERAL OPINION]. \n" +
                "It's time to\n" +
                "1. Buy yourself ANOTHER STIFF DRINK\n" +
                "2. Get a room and HIT THE HAY\n" +
                "3. Get OUT OF here\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("DRINK"); break;
            case "2": player.setSTATE("REST"); break;
            case "3": player.setSTATE("TOWN_LOOK"); break;}
    }

    public void rest(Player player) throws InterruptedException { // save + generator opisów DO ZAIMPLEMENTOWANIA

        System.out.print("\nYou rent a room and decide to TAKE A BREATHER for the rest of the day.\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.print("You lie down and MAKE AN ENTRY in your JOURNAL.\n");
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.print("Your bed is [DESCRIPTION].\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.print("You hear [SOUND] in the background.\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.print("The pillows are [DESCRIPTION].\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.print("...\n");
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.print("...\n");
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.print("...\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.print("Rise and shine! It's a NEW DAY. The SUN is UP and SO ARE YOU. You get up and GET OUT.\n");
        TimeUnit.MILLISECONDS.sleep(2000);

        player.setSTATE("TOWN");
    }

    public void contemplate(Player player) {
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYour NAME is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
                "You also enjoy KILLING THINGS sometimes.\n" +
                "You probably will be able to CHECK YOUR STATS here in the future, but for now KEEP DREAMING, LOSER.\n" +
                "1. Get me THE HELL OUT OF HERE\n> " ); // generator postaci + staty + eq DO ZAIMPLEMENTOWANIA

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("TOWN"); break;}
    }
}
