package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.creature.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Plains {
    public void go(Player player) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        String choiceN;

        Enemy enemy = new Enemy(10, 0, 1, 10, 1);
        enemy.setName("Rat Bob");
        System.out.print("\nYou are now in the PLAINS. It is [WEATHER]. You see the lonely rat. \n" +
                "His name is " + enemy.getName() + ".\n" +
                "You decide to\n" +
                "1. Get closer to this creature!\n" +
                "2. Get back on the road!" +
                "\n> ");

        choiceN = input.nextLine();
        TimeUnit.MILLISECONDS.sleep(1000);
        switch (choiceN) {
            case "1": {
                boolean flag = true;
                System.out.println("\nThe rat seems nervous.");

                while (flag) {
                    System.out.print("\nWhat do you do now?\n" +
                            "1. Attack!\n" +
                            "2. Wait.\n" +
                            "3. Get out of here.\n> ");
                    String command = input.next();
                    switch (command) {
                        case "1":
                            int attack1 = Combat.attack(player, enemy);
                            int attack2 = Combat.attack(enemy, player);
                            System.out.println("\nYou dealt " + attack1 + " dmg");
                            System.out.println(enemy.getName() + " bit u for " + attack2 + " dmg.");
                            break;

                        case "2":
                            int attack3 = Combat.attack(enemy, player);
                            System.out.println("\nYou did nothing and got hit!!!");
                            System.out.println(enemy.getName() + " bit u for " + attack3 + " dmg.");
                            break;
                        case "3":
                            flag = false;
                            break;
                    }
                    if (!flag) break;
                    System.out.println("You got " + player.getHp() + " hp.");
                    System.out.println(enemy.getName() + " has " + enemy.getHp() + " hp.");
                    if (player.getHp() < 1) {
                        System.out.println("\nYou died. \nGAME OVER!");
                        player.setSTATE("DEAD");
                        break;
                    }
                    if (enemy.getHp() < 1) {
                        System.out.println("\n" + enemy.getName() + " is dead." +
                                "\n" + "You won the fight!\n");
                        break;
                    }
                }
                break;
            }
            case "2":
                player.setSTATE("FIGHT_CHOOSE");
                break;
        }
    }
}

