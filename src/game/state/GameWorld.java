package game.state;

import game.creature.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameWorld {
    Player player;
    Scanner input = new Scanner(System.in);

    public void begin() throws InterruptedException {

        System.out.print("\nHi there. What's your name?\n> ");
        String savename = input.next().toUpperCase();
        ;
        System.out.print("\n" + savename + ", is it? [Y/N]\n> ");
        String answer = input.next();
        while (!answer.toLowerCase().startsWith("y")) {
            System.out.print("\nWell then, what is it?\n> ");
            savename = input.next().toUpperCase();
            System.out.print("\n" + savename + ", are you sure? [Y/N]\n> ");
            answer = input.next();
        }  // savename - do wczytywania save'ow DO ZAIMPLEMENTOWANIA

        Player player = new Player(100, 0, 1, 0, 0, savename);
        System.out.println("\nThat's not your name.");

        TimeUnit.MILLISECONDS.sleep(2000); // czeka 2 sekundy
        System.out.println(new String(new char[70]).replace("\0", "\r\n"));  //"clear screen"
        }

    public void opisPostaci() {
        System.out.println("Your name is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
                "You also enjoy KILLING THINGS sometimes."); // generator postaci DO ZAIMPLEMENTOWANIA
        }
        public void town() {
            System.out.println("You are currently in TOWN. It is [WEATHER]. The poeple around seem [EMOTION]. \n What will you do?\n" +
                    "1. ");
        }


        public static void main (String[]args) throws InterruptedException {
            GameWorld game = new GameWorld();
            //game.begin();
            game.opisPostaci();
            game.town();

        }


}
