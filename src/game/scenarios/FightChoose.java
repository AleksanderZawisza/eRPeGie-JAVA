package game.scenarios;

import game.creature.Player;

import java.util.Scanner;

public class FightChoose {

    public void go(Player player) { // generator opisów DO ZAIMPLEMENTOWANIA
        Scanner input = new Scanner(System.in);
        String choiceN;

        System.out.print("\nYou are NEAR THE TOWN GATES. It is [WEATHER]. You see THREE PATHS.\n" +
                "You decide to\n" +
                "1. Go EAST into the PLAINS\n" +
                "2. Go NORTH into the FOREST\n" +
                "3. Go WEST into the MOUNTAINS\n" +
                "4. Turn around and GO BACK to the TOWN\n> ");

        choiceN = input.nextLine();
        switch (choiceN)
        {case "1": player.setSTATE("PLAINS"); break;
            case "2": player.setSTATE("FOREST"); break;
            case "3": player.setSTATE("MOUNTAINS"); break;
            case "4": player.setSTATE("TOWN"); break;}
    }
}
