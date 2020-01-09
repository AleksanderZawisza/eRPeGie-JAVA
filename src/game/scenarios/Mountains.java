package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.creature.EnemyGenerator;
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

        Enemy enemy = EnemyGenerator.mountainEnemy();
        GameWorld.currentEnemy = enemy;
        gameworld.ui.mainTextArea.setText("You are now in the MOUNTAINS. It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() + "." +
                "\nYou decide to:");

        gameworld.ui.choice1.setText("Get closer to this creature!");
        gameworld.ui.choice2.setText("Get back on the road!");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "MOUNTAINS_FIGHT_CHOOSE";
        gameworld.nextPosition2 = "MOUNTAINS_CHOOSE";
        gameworld.nextPosition3 = "";
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

        enemy = GameWorld.currentEnemy;
        int attack1 = Combat.attack(player, enemy);
        int attack2 = Combat.attack(enemy, player);

        if (player.getHp() < 1) {
            gameworld.ui.mainTextArea.setText("The "+ enemy.getName().toUpperCase() + " hurt u for " + attack2 + " dmg.\n" +
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

