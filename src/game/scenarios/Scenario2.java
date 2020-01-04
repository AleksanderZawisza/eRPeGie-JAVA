package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.creature.Player;

import java.util.Scanner;

public class Scenario2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        Player player = new Player(500, 0, 10, 0, 0);
        Enemy enemy = new Enemy(400, 0, 10, 100, 1);
        enemy.setName("troll Martin");


        System.out.println("there is a troll in front of you");
        System.out.println("his name is " + enemy.getName());


        while(flag) {
            System.out.println("What do u do ?");
            System.out.println("1. attack!!!");
            System.out.println("2. wait");
            System.out.println("3. exit");
            System.out.print("> ");
            String command = input.next();
            switch (command) {
                case "1":
                    int attack1 = Combat.attack(player, enemy);
                    int attack2 = Combat.attack(enemy, player);
                    System.out.println("u dealt " + attack1 +" dmg");
                    System.out.println(enemy.getName() + " smashed u for " + attack2 + " dmg");
                    break;

                case "2":
                    int attack3 = Combat.attack(enemy, player);
                    System.out.println("cant run gotta fight!!!");
                    System.out.println(enemy.getName() + " smashed u for " + attack3 + " dmg");
                    break;
                case "3":
                    flag=false;
                    break;
            }
            if (!flag) break;
            System.out.println("u got " + player.getHp() + " hp");
            System.out.println(enemy.getName() + "'s got " + enemy.getHp() + " hp");
            if (player.getHp()<1){
                System.out.println("u died");
                break;
            }
            if (enemy.getHp()<1){
                System.out.println( enemy.getName()+ " is dead");
                break;
            }
            System.out.println("----------");
        }

    }

}

