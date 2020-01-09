package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.generators.EnemyGenerator;
import game.creature.Player;
import game.state.GameWorld;

public class Plains {

    GameWorld gameworld;

    public Plains(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;
    Enemy enemy = gameworld.currentEnemy;

    public void go() {

        Enemy enemy = EnemyGenerator.plainsEnemy();
        GameWorld.currentEnemy = enemy;
        gameworld.ui.mainTextArea.setText("You are now in the PLAINS. It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() + "." +
                "\nYou decide to:");

        gameworld.ui.choice1.setText("Get closer to this creature!");
        gameworld.ui.choice2.setText("Get back on the road!");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "PLAINS_FIGHT_CHOOSE";
        gameworld.nextPosition2 = "FIGHT_CHOOSE";
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

        gameworld.nextPosition1 = "PLAINS_FIGHT";
        gameworld.nextPosition2 = "PLAINS"; //FIGHT CHOOSE ? szukanie nowego przeciwnika
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

    public void fight(){

        enemy = GameWorld.currentEnemy;
        int attack1 = Combat.attack(player, enemy);
        int attack2 = Combat.attack(enemy, player);

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

            gameworld.ui.choice1.setText("Look for more.");
            gameworld.ui.choice2.setText("Get back on the road.");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "PLAINS";
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

            gameworld.nextPosition1 = "PLAINS_FIGHT";
            gameworld.nextPosition2 = "PLAINS";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        gameworld.vm.updateCurrentHPLabel(player.getHp()); //UPDATE HP
    }
}

