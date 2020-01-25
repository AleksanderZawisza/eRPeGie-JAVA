package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.generators.EnemyGenerator;
import game.creature.Player;
import game.item.*;
import game.state.GameWorld;

public class Mountains {

    GameWorld gameworld;

    public Mountains(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;
    Enemy enemy = gameworld.currentEnemy;
    Item drop;
    FightText fightText = new FightText(gameworld);

    public void go() {

        enemy = gameworld.currentEnemy;

        if (!gameworld.fromInventory){
            enemy = EnemyGenerator.mountainEnemy();
            gameworld.currentEnemy = enemy;
        }

        fightText.lookingAround("MOUNTAINS", enemy, gameworld);

        gameworld.nextPosition1 = "MOUNTAINS_FIGHT_CHOOSE";
        gameworld.nextPosition2 = "MOUNTAINS";
        gameworld.nextPosition3 = "FIGHT_CHOOSE";
        gameworld.nextPosition4 = "";
    }

    public void fightChoose(){

        enemy = GameWorld.currentEnemy;
        if (enemy.isSentient()) this.drop = enemy.getRandomDrop();

        fightText.whatNow(enemy, gameworld);

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
            fightText.youDied(attack2, enemy, gameworld);

            gameworld.nextPosition1 = "BEGIN";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else if (enemy.getHp() < 1) {    // DEAD ENEMY

            fightText.animalEnemyDead(enemy, attack1, attack2, gameworld);

            if (enemy.isSentient()) {
                Item item = this.drop;
                this.drop = item;
                fightText.sentientEnemyDead(enemy, attack1, attack2, item, gameworld);
            }

            if (!enemy.isSentient()){  //NOT SENTIENT ENEMY

                gameworld.nextPosition1 = "MOUNTAINS";
                gameworld.nextPosition2 = "FIGHT_CHOOSE";
                gameworld.nextPosition3 = "";
                gameworld.nextPosition4 = "";
            }
            else{  //SENTIENT ENEMY

                gameworld.nextPosition1 = "MOUNTAINS";
                gameworld.nextPosition2 = "MOUNTAINS_DROP";
                gameworld.nextPosition3 = "FIGHT_CHOOSE";
                gameworld.nextPosition4 = "";

            }
        }                                   //END DEAD ENEMY
        else {
            fightText.enemyStillNotDead(attack1, attack2, enemy, gameworld);

            gameworld.nextPosition1 = "MOUNTAINS_FIGHT";
            gameworld.nextPosition2 = "MOUNTAINS";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        gameworld.vm.updateCurrentHPLabel(player.getHp()); //UPDATE HP
        player.updateMaxHp();
    }

    public void inspectDroppedItem() {
        Item item = this.drop;
        fightText.inspectDropText(item, gameworld);

        gameworld.nextPosition1 = "MOUNTAINS_TAKE_DROP";
        gameworld.nextPosition2 = "MOUNTAINS";
        gameworld.nextPosition3 = "FIGHT_CHOOSE";
        gameworld.nextPosition4 = "";

        gameworld.vm.hideUselessChoiceButtons();
    }
    public void takeDroppedItem(){

        Item item = this.drop;
        if (player.howManyItemsInInv()==12) {
            fightText.cantPickUpDrop(item, gameworld);

            gameworld.nextPosition1 = "MOUNTAINS_DROP";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else{
            fightText.pickUpDrop(item, gameworld);
            player.take(item);

            gameworld.nextPosition1 = "MOUNTAINS";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }

    }
}
