package game.scenarios;

import game.creature.Player;

import java.util.Scanner;

public class Town {
    public void go(Player player) { // generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou are currently in TOWN. It is [WEATHER]. The poeple around seem [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "What will you do?\n" +
                "1. Look AROUND\n" +
                "2. CONTEMPLATE your life choices\n" +
                "3. Get OUT OF here\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("TOWN_LOOK"); break;
            case "2": player.setSTATE("CONTEMPLATE"); break;
            case "3": player.setSTATE("FIGHT_CHOOSE"); break;}
    }

    public void look(Player player) { // generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou start WANDERING AROUND. You see [RANDOM PERSON/GROUP]. [SHE/HE/THEY] seem [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "What now?\n" +
                "1. Find the TAVERN\n" +
                "2. Go to the SHOP\n" +
                "3. Stop wandering around\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("TAVERN"); break;
            case "2": player.setSTATE("SHOP"); break;
            case "3": player.setSTATE("TOWN"); break;}
    }
}
