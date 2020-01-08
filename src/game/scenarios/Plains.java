package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.creature.EnemyGenerator;
import game.creature.Player;
import game.state.GameWorld;

public class Plains {

    GameWorld gameworld;

    public Plains(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = GameWorld.player;
    Enemy enemy = GameWorld.currentEnemy;

    public void go() {

        Enemy enemy = EnemyGenerator.plainsEnemy();
        GameWorld.currentEnemy = enemy;
        gameworld.ui.mainTextArea.setText("You are now in the PLAINS. It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() +
                "\nYou decide to");

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

        gameworld.ui.mainTextArea.setText("What do you do now?");

        gameworld.ui.choice1.setText("Attack!");
        gameworld.ui.choice2.setText("Wait.");
        gameworld.ui.choice3.setText("Get out of here.");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "PLAINS_FIGHT";
        gameworld.nextPosition2 = "PLAINS_WAIT";
        gameworld.nextPosition3 = "PLAINS"; //FIGHT CHOOSE ? szukanie nowego przeciwnika
        gameworld.nextPosition4 = "";
    }

    public void fight(){

        enemy = GameWorld.currentEnemy;
        int attack1 = Combat.attack(player, enemy);
        int attack2 = Combat.attack(enemy, player);

        if (player.getHp() < 1) {
            gameworld.ui.mainTextArea.setText("You died.\n" +
                    "GAME OVER");
            gameworld.nextPosition1 = "DEAD";
        }
        else if (enemy.getHp() < 1) {
            gameworld.ui.mainTextArea.setText("The " + enemy.getRace() + " is dead.\n" +
                    "You won the fight!");
            gameworld.nextPosition1 = "PLAINS";
        }
        else {
            gameworld.nextPosition1 = "PLAINS_FIGHT_CHOOSE";
            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " dmg.\n" +
                    enemy.getName().toUpperCase() + " hurt u for " + attack2 + " dmg.\n" +
                    enemy.getName().toUpperCase() + " has " + enemy.getHp() + " hp.");
        }

        gameworld.vm.updateCurrentHPLabel(player.getHp()); //UPDATE HP

        gameworld.ui.choice1.setText(" > ");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        //first choice earlier
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";


    }

    public void waited(){

        enemy = GameWorld.currentEnemy;
        int attack2 = Combat.attack(enemy, player);

        if (player.getHp() < 1) {
            gameworld.ui.mainTextArea.setText("You died.\n" +
                    "GAME OVER");
            gameworld.nextPosition1 = "DEAD";
        }
        else {
            gameworld.ui.mainTextArea.setText("You did nothing and got hit!" +
                    enemy.getName() + " bit u for " + attack2 + " dmg.");
            gameworld.nextPosition1 = "PLAINS_FIGHT_CHOOSE";
        }

        gameworld.ui.choice1.setText(" > ");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        //first choice earlier
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

}

