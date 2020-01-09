package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.generators.EnemyGenerator;
import game.creature.Player;
import game.state.GameWorld;

public class Mountains {

    GameWorld gameworld;

    public Mountains(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;
    Enemy enemy = gameworld.currentEnemy;

    public void go() {

        enemy = gameworld.currentEnemy;

        if (!gameworld.fromInventory){
            enemy = EnemyGenerator.mountainEnemy();
            gameworld.currentEnemy = enemy;
        }
        gameworld.ui.mainTextArea.setText("You are now in the MOUNTAINS. It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() + "." +
                "\nYou decide to:");

        gameworld.ui.choice1.setText("GET CLOSER to this creature");
        gameworld.ui.choice2.setText("SEARCH for other enemies");
        gameworld.ui.choice3.setText("GET BACK on the road");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "MOUNTAINS_FIGHT_CHOOSE";
        gameworld.nextPosition2 = "MOUNTAINS";
        gameworld.nextPosition3 = "FIGHT_CHOOSE";
        gameworld.nextPosition4 = "";
    }

    public void fightChoose(){

        enemy = GameWorld.currentEnemy;
        gameworld.ui.mainTextArea.setText(enemy.getRace().toUpperCase() + " looks nervous.\n" +
                "What do you do now?");

        gameworld.ui.choice1.setText("Attack!");
        gameworld.ui.choice2.setText("Get out of here.");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "MOUNTAINS_FIGHT";
        gameworld.nextPosition2 = "MOUNTAINS"; //FIGHT CHOOSE ? szukanie nowego przeciwnika
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

    public void fight(){

        int attack1 = 0;
        int attack2 = 0;

        if(!gameworld.fromInventory){
            enemy = GameWorld.currentEnemy;
            attack1 = Combat.attack(player, enemy);
            attack2 = Combat.attack(enemy, player);

            gameworld.prevDmgDealt = attack1;
            gameworld.prevDmgTaken = attack2;
        }
        else {
            attack1 = gameworld.prevDmgDealt;
            attack2 = gameworld.prevDmgTaken;
        }

        if (player.getHp() < 1) {

            gameworld.ui.mainTextArea.setText("The "+ enemy.getName().toUpperCase() + " hurt u for "
                    + attack2 + " dmg.\n" +
                    "You died.\n" +
                    "GAME OVER");

            gameworld.ui.choice1.setText("Start new game");
            gameworld.ui.choice2.setText("");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "BEGIN";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else if (enemy.getHp() < 1) {
            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " dmg.\n" +
                    "The " + enemy.getRace() + " is dead.\n" +
                    "You won the fight!");

            if (!gameworld.fromInventory){
                // dead enemy does not deal dmg
                player.addHp(attack2);
            }

            gameworld.ui.choice1.setText("Look for more.");
            gameworld.ui.choice2.setText("Get back on the road.");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "MOUNTAINS";
            gameworld.nextPosition2 = "FIGHT_CHOOSE";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else {
            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " dmg.\n" +
                    "The "+ enemy.getName().toUpperCase() + " hurt u for " + attack2 + " dmg.\n" +
                    "This " + enemy.getRace().toUpperCase() + " has " + enemy.getHp() + " hp now." +
                    "\nWhat do you do now?");

            gameworld.ui.choice1.setText("Attack!");
            gameworld.ui.choice2.setText("Get out of here.");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "MOUNTAINS_FIGHT";
            gameworld.nextPosition2 = "MOUNTAINS";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        gameworld.vm.updateCurrentHPLabel(player.getHp()); //UPDATE HP
    }
}

