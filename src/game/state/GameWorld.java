package game.state;

import game.combat.Combat;
import game.creature.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameWorld {
    Player player = new Player(100, 0, 2, 0, 0); //nowa postać
    String choice;
    int choiceN;
    String STATE; //od tego zalezy gdzie jestesmy, rozpiska: TOWN, TOWN_LOOK, CONTEMPLATE, FIGHT_CHOOSE, DRINK, REST
    Scanner input = new Scanner(System.in);

    public GameWorld() {
    }

    public String begin() throws InterruptedException {
        System.out.print("\nHi there. What's your NAME?\n> ");
        String savename = input.nextLine();
        savename = savename.toUpperCase();
        System.out.print("\n" + savename + ", right? [Y/N]\n> ");
        String answer = input.nextLine();
        answer = answer.toLowerCase();
        while (!answer.toLowerCase().startsWith("y")) {
            System.out.print("\nWell then, what is it?\n> ");
            savename = input.nextLine();
            savename = savename.toUpperCase();
            System.out.print("\n" + savename + "? [Y/N]\n> ");
            answer = input.nextLine();
            answer = answer.toLowerCase();
        }  // savename - do wczytywania save'ow DO ZAIMPLEMENTOWANIA

        player.setSavename(savename);
        System.out.println("\nThat's not your NAME.");

        TimeUnit.MILLISECONDS.sleep(2000); // czeka 2 sekundy
        STATE = "TOWN";
        System.out.println(new String(new char[70]).replace("\0", "\r\n"));
        return savename; //"clear screen"
    }


    public void opisPostaci(String savename) {
        System.out.print("\nYour NAME is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
                "You also enjoy KILLING THINGS sometimes.\n"); // generator postaci DO ZAIMPLEMENTOWANIA
    }

    public void contemplate() {
        System.out.print("\nYour NAME is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
                "You also enjoy KILLING THINGS sometimes.\n" +
                "You probably will be able to CHECK YOUR STATS here in the future, but for now KEEP DREAMING, LOSER.\n" +
                "1. Get me THE HELL OUT OF HERE\n> " ); // generator postaci + staty + eq DO ZAIMPLEMENTOWANIA
            choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "TOWN"; break;}
    }

    public void town() { // generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou are currently in TOWN. It is [WEATHER]. The poeple around seem [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "What will you do?\n" +
                "1. Look AROUND\n" +
                "2. CONTEMPLATE your life choices\n" +
                "3. Get OUT OF here\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
            {case 1: this.STATE = "TOWN_LOOK"; break;
             case 2: this.STATE = "CONTEMPLATE"; break;
             case 3: this.STATE = "FIGHT_CHOOSE"; break;}
    }

    public void townLook() { // generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou start WANDERING AROUND. You see [RANDOM PERSON/GROUP]. [SHE/HE/THEY] seem [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "What now?\n" +
                "1. Find the TAVERN\n" +
                "2. Go to the SHOP\n" +
                "3. Stop wandering around\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "TAVERN"; break;
            case 2: this.STATE = "SHOP"; break;
            case 3: this.STATE = "TOWN"; break;}
    }

    public void shop() { // generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou go to the SHOP. The SHOPKEEPER seems [EMOTION] when he sees you. He asks if you're interested in BUYING or SELLING.\n" +
                "You have " + this.player.getMoney() + " GOLD COINS on you.\n" +
                "You say that you want to\n" +
                "1. BUY stuff\n" +
                "2. SELL stuff\n" +
                "3. GET OUT of here\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "BUY"; break;
            case 2: this.STATE = "SELL"; break;
            case 3: this.STATE = "TOWN_LOOK"; break;}
    }

    public void buy() throws InterruptedException { // sklep kupowanie + generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou want to BUY SHIT. You can't do that because there aren't any ITEMS in the GAME.\n" +
                "You have " + this.player.getMoney() + " gold coins on you.\n" +
                "You GET OUT of here.\n");
        TimeUnit.MILLISECONDS.sleep(3000);
        this.STATE = "TOWN_LOOK";
    }

    public void sell() throws InterruptedException { // sklep sprzedawanie + generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou want to SELL SHIT. You can't do that because there aren't any ITEMS in the GAME.\n" +
                "You have " + this.player.getMoney() + " gold coins on you.\n" +
                "You GET OUT of here.\n");
        TimeUnit.MILLISECONDS.sleep(3000);
        this.STATE = "TOWN_LOOK";
    }

    public void tavern() { // generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou manage to find the TAVERN. You see [RANDOM PERSON/GROUP] in the corner. The barmaid looks [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "It's time to\n" +
                "1. Buy yourself a STIFF DRINK\n" +
                "2. Get a room and HIT THE HAY\n" +
                "3. Get OUT OF here\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "DRINK"; break;
            case 2: this.STATE = "REST"; break;
            case 3: this.STATE = "TOWN_LOOK"; break;}
    }

    public void drink() { // questy/potrzeba pieniedzy do kupowania alko + generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou get yourself a [DRINK]. It tastes [TASTE]. [GENERAL OPINION]. \n" +
                "It's time to\n" +
                "1. Buy yourself ANOTHER STIFF DRINK\n" +
                "2. Get a room and HIT THE HAY\n" +
                "3. Get OUT OF here\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "DRINK"; break;
            case 2: this.STATE = "REST"; break;
            case 3: this.STATE = "TOWN_LOOK"; break;}
    }

    public void rest() throws InterruptedException { // save + generator opisów DO ZAIMPLEMENTOWANIA
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
        this.STATE="TOWN";
    }

    public void fightChoose() { // generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou are NEAR THE TOWN GATES. It is [WEATHER]. You see THREE PATHS.\n" +
                "You decide to\n" +
                "1. Go EAST into the PLAINS\n" +
                "2. Go NORTH into the FOREST\n" +
                "3. Go WEST into the MOUNTAINS\n" +
                "4. Turn around and GO BACK to the TOWN\n> ");
        choiceN = input.nextInt();
        switch (choiceN)
        {case 1: this.STATE = "PLAINS"; break;
            case 2: this.STATE = "FOREST"; break;
            case 3: this.STATE = "MOUNTAINS"; break;
            case 4: this.STATE = "TOWN"; break;}
    }

    public void plains() throws InterruptedException { // generator opisów DO ZAIMPLEMENTOWANIA
        Enemy enemy = new Enemy(10, 0, 1, 10, 1);
        enemy.setName("Rat Bob");
        System.out.print("\nYou are now in the PLAINS. It is [WEATHER]. You see the lonely rat. \n" +
                "His name is " + enemy.getName() + ".\n" +
                "You decide to\n" +
                "1. Get closer to this creature!\n" +
                "2. Get back on the road!" +
                "\n> ");
        choiceN = input.nextInt();
        TimeUnit.MILLISECONDS.sleep(1000);
        switch (choiceN)
        {case 1: {
            boolean flag = true;
            System.out.println("\nThe rat seems nervous.");
            while(flag) {
                System.out.print("\nWhat do you do now?\n" +
                        "1. Attack!\n" +
                        "2. Wait.\n" +
                        "3. Get out of here.\n> ");
                String command = input.next();
                switch (command) {
                    case "1":
                        int attack1 = Combat.attack(player, enemy);
                        int attack2 = Combat.attack(enemy, player);
                        System.out.println("\nYou dealt " + attack1 +" dmg");
                        System.out.println(enemy.getName() + " bit u for " + attack2 + " dmg.");
                        break;

                    case "2":
                        int attack3 = Combat.attack(enemy, player);
                        System.out.println("\nYou did nothing and got hit!!!");
                        System.out.println(enemy.getName() + " bit u for " + attack3 + " dmg.");
                        break;
                    case "3":
                        flag=false;
                        break;
                }
                if (!flag) break;
                System.out.println("You got " + player.getHp() + " hp.");
                System.out.println(enemy.getName() + " has " + enemy.getHp() + " hp.");
                if (player.getHp()<1){
                    player.isAlive = false;
                    System.out.println("\nYou died. \nGAME OVER!");
                    this.STATE = "DEAD";
                    break;
                }
                if (enemy.getHp()<1){
                    System.out.println("\n" + enemy.getName()+ " is dead." +
                            "\n" + "You won!\n");
                    break;
                }
            }
            break;}
            case 2: this.STATE = "FIGHT_CHOOSE"; break; }
    }

    public void forest() throws InterruptedException { // generator opisów DO ZAIMPLEMENTOWANIA
        Enemy enemy = new Enemy(40, 10, 20, 100, 100);
        enemy.setName("Troll Martin");
        System.out.print("\nYou are now in the FOREST. It is [WEATHER]. You see a very big troll. \n" +
                "His name is " + enemy.getName() + ".\n" +
                "You decide to\n" +
                "1. Get closer to this monster!\n" +
                "2. Get back on the road!" +
                "\n> ");
        choiceN = input.nextInt();
        TimeUnit.MILLISECONDS.sleep(1000);
        switch (choiceN)
        {case 1: {
            boolean flag = true;
            System.out.println("\nEnormous troll furiously charges at you.");
            while(flag) {
                System.out.print("\nWhat do you do now?\n" +
                        "1. Attack!\n" +
                        "2. Wait.\n" +
                        "3. Get out of this cursed place.\n> ");
                String command = input.next();
                switch (command) {
                    case "1":
                        int attack1 = Combat.attack(player, enemy);
                        int attack2 = Combat.attack(enemy, player);
                        System.out.println("\nYou dealt " + attack1 +" dmg");
                        System.out.println(enemy.getName() + " smashed you for " + attack2 + " dmg.");
                        break;

                    case "2":
                        int attack3 = Combat.attack(enemy, player);
                        System.out.println("\nYou did nothing and got hit!!!");
                        System.out.println(enemy.getName() + " smashed you for " + attack3 + " dmg.");
                        break;
                    case "3":
                        flag=false;
                        break;
                }
                if (!flag) break;
                System.out.println("You got " + player.getHp() + " hp.");
                System.out.println(enemy.getName() + " has " + enemy.getHp() + " hp.");
                if (player.getHp()<1){
                    player.isAlive = false;
                    this.STATE = "DEAD";
                    System.out.println("\nYou died. \nGAME OVER!");
                    break;
                }
                if (enemy.getHp()<1){
                    System.out.println("\n" + enemy.getName()+ " is dead." +
                            "\n" + "You won!\n");
                    break;
                }
            }
            break;}
            case 2: this.STATE = "FIGHT_CHOOSE"; break; }
    }

    public void mountains() throws InterruptedException { // combat + generator opisów DO ZAIMPLEMENTOWANIA
        System.out.print("\nYou are now in the MOUNTAINS. It is [WEATHER]. There's ABSOLUTELY NOTHING AROUND.\n");
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.print("You GET THE HELL OUT OF HERE.\n");
        this.STATE = "FIGHT_CHOOSE";
    }



    public void play () throws InterruptedException {
        GameWorld game = new GameWorld();
        String savename = game.begin();
        game.opisPostaci(savename);
        game.STATE = "TOWN";
        while (game.STATE != "DEAD") {
            switch(game.STATE){
                case "CONTEMPLATE": game.contemplate(); break;
                case "TOWN": game.town(); break;
                case "TOWN_LOOK": game.townLook(); break;
                case "TAVERN": game.tavern(); break;
                case "DRINK": game.drink(); break;
                case "REST": game.rest(); break;
                case "SHOP": game.shop(); break;
                case "SELL": game.sell(); break;
                case "BUY": game.buy(); break;
                case "FIGHT_CHOOSE": game.fightChoose(); break;
                case "PLAINS": game.plains(); break;
                case "FOREST": game.forest(); break;
                case "MOUNTAINS": game.mountains(); break;
                case "DEAD": break;
            }
        }

    }


}
