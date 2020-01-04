package game.scenarios;
import game.combat.Combat;
import game.creature.Creature;
import game.creature.Enemy;
import game.creature.Player;

import java.util.Scanner;

public class Scenario1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        Enemy rat = new Enemy(5, 0, 1, 100, 1);
        rat.setName("rat Bob II");
        Player player = new Player(10, 0, 1, 0, 0);

        System.out.println("there is a rat in front of you");
        System.out.println("his name is " + rat.getName());


        while(flag) {
            System.out.println("wot do");
            System.out.println("1. attack!!!");
            System.out.println("2. run");
            System.out.println("3. exit");
            System.out.print("> ");
            String command = input.next();
            switch (command) {
                case "1":
                    int attack1 = Combat.attack(player, rat);
                    int attack2 = Combat.attack(rat, player);
                    System.out.println("u dealt " + attack1 +" dmg");
                    System.out.println(rat.getName() + " bit u for " + attack2 + " dmg");
                    break;

                case "2":
                    int attack3 = Combat.attack(rat, player);
                    System.out.println("cant run gotta fight!!!");
                    System.out.println(rat.getName() + " bit u for " + attack3 + " dmg");
                    break;
                case "3":
                    flag=false;
                    break;
            }
            if (flag==false) break;
            System.out.println("u got " + player.getHp() + " hp");
            System.out.println(rat.getName() + "'s got " + rat.getHp() + " hp");
            if (player.getHp()<1){
                System.out.println("u ded");
                break;
            }
            if (rat.getHp()<1){
                System.out.println( rat.getName()+ " is ded");
                break;
            }
            System.out.println("----------");
        }

    }
}
