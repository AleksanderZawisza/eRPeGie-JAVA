package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.creature.EnemyGenerator;
import game.creature.Player;
import game.state.GameWorld;

public class Forest {

    GameWorld gameworld;

    public Forest(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = GameWorld.player;
    Enemy enemy = GameWorld.currentEnemy;

    public void go() {

        Enemy enemy = EnemyGenerator.forestEnemy();
        GameWorld.currentEnemy = enemy;
        gameworld.ui.mainTextArea.setText("You are now in the FOREST. It is [WEATHER]. You see the \n" +
                enemy.getName() +
                "You decide to");

        gameworld.ui.choice1.setText("Get closer to this monster!");
        gameworld.ui.choice2.setText("Get back on the road!");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "FOREST_FIGHT_CHOOSE";
        gameworld.nextPosition2 = "FIGHT_CHOOSE";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

    public void fightChoose(){

        gameworld.ui.mainTextArea.setText("What do you do now?");

        gameworld.ui.choice1.setText("Attack!");
        gameworld.ui.choice2.setText("Wait.");
        gameworld.ui.choice3.setText("Get out of this cursed place.");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "FOREST_FIGHT";
        gameworld.nextPosition2 = "FOREST_WAIT";
        gameworld.nextPosition3 = "FOREST"; //FIGHT CHOOSE ? szukanie nowego przeciwnika
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
            gameworld.ui.mainTextArea.setText(enemy.getName() + " is dead.\n" +
                    "You won the fight!");
            gameworld.nextPosition1 = "FOREST";
        }
        else {
            gameworld.nextPosition1 = "FOREST_FIGHT_CHOOSE";
            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " dmg" +
                    enemy.getName() + " smashed u for " + attack2 + " dmg." +
                    enemy.getName() + " has " + enemy.getHp() + " hp.");
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
            gameworld.nextPosition1 = "FOREST_FIGHT_CHOOSE";
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

