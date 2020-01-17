package game.scenarios;

import game.combat.Combat;
import game.creature.Enemy;
import game.generators.EnemyGenerator;
import game.creature.Player;
import game.item.*;
import game.item.armor.*;
import game.state.GameWorld;

public class Mountains {

    GameWorld gameworld;

    public Mountains(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;
    Enemy enemy = gameworld.currentEnemy;
    Item drop;

    public void go() {

        enemy = gameworld.currentEnemy;

        if (!gameworld.fromInventory){
            enemy = EnemyGenerator.mountainEnemy();
            gameworld.currentEnemy = enemy;
        }
        gameworld.ui.mainTextArea.setText("You are now in the MOUNTAINS. It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() + "." +
                "<br>You decide to:");

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
        if (enemy.isSentient()) this.drop = enemy.getRandomDrop();
        gameworld.ui.mainTextArea.setText("The " + enemy.getRace().toUpperCase() + " looks nervous.<br>" +
                "What do you do now?");

        gameworld.ui.choice1.setText("ATTACK");
        gameworld.ui.choice2.setText("LEAVE it be");
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

            gameworld.ui.mainTextArea.setText("The "+ enemy.getName().toUpperCase() + " hurt you for "
                    + attack2 + " DMG.<br><br>" +
                    "You DIED.<br><br>" +
                    "GAME OVER");

            gameworld.ui.choice1.setText("Start a NEW GAME");
            gameworld.ui.choice2.setText("");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "BEGIN";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else if (enemy.getHp() < 1) {    // DEAD ENEMY
            String lvlUp = "";
            String moneyOrItem = "You think you can sell parts from the DEAD " + enemy.getRace().toUpperCase() +
                    " for about " + enemy.getMoney() + " GOLD COINS.<br><br>";

            float receivedExp = enemy.getExp()/20;
            if (receivedExp + player.expWithoutLevel()>=1) lvlUp = "You feel more confident. You're pretty sure your THREAT RATING just went up!";
            if (enemy.isSentient()) {
                Item item = this.drop;
                this.drop = item;
                String plural = "s";
                if (item.getName().endsWith("s")) plural = "";
                moneyOrItem = "You think the DEAD " + enemy.getRace().toUpperCase() + "'S " +
                        item.getName().toUpperCase() + " look"+ plural +" pretty serviceable.<br><br>";
            }

            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " DMG.<br>" +
                    "The " + enemy.getRace().toUpperCase() + " is DEAD.<br>" +
                    "You WON the fight!<br><br>" +
                    moneyOrItem +
                    lvlUp);

            player.addExp(receivedExp);

            if (!enemy.isSentient()){  //NOT SENTIENT ENEMY
                player.addMoney(enemy.getMoney());
                if (!gameworld.fromInventory){
                    // dead enemy does not deal dmg
                    player.addHp(attack2);
                }
                if (gameworld.fromInventory){
                    // no double rewards
                    player.lowerExp(receivedExp);
                    player.lowerMoney(enemy.getMoney());
                }
                gameworld.ui.choice1.setText("LOOK for MORE");
                gameworld.ui.choice2.setText("Get BACK on the ROAD");
                gameworld.ui.choice3.setText("");
                gameworld.ui.choice4.setText("");

                gameworld.nextPosition1 = "MOUNTAINS";
                gameworld.nextPosition2 = "FIGHT_CHOOSE";
                gameworld.nextPosition3 = "";
                gameworld.nextPosition4 = "";
            }
            else{  //SENTIENT ENEMY

                if (!gameworld.fromInventory){
                    // dead enemy does not deal dmg
                    player.addHp(attack2);
                }
                if (gameworld.fromInventory){
                    // no double rewards
                    player.lowerExp(receivedExp);
                }
                gameworld.ui.choice1.setText("LOOK for MORE");
                gameworld.ui.choice2.setText("INSPECT the ITEM");
                gameworld.ui.choice3.setText("Get BACK on the ROAD");
                gameworld.ui.choice4.setText("");

                gameworld.nextPosition1 = "MOUNTAINS";
                gameworld.nextPosition2 = "MOUNTAINS_DROP";
                gameworld.nextPosition3 = "FIGHT_CHOOSE";
                gameworld.nextPosition4 = "";

            }
        }                                   //END DEAD ENEMY
        else {
            gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " DMG.<br>" +
                    "The "+ enemy.getName().toUpperCase() + " hurt you for " + attack2 + " DMG.<br>" +
                    "This " + enemy.getRace().toUpperCase() + " has " + enemy.getHp() + " HP now.");

            gameworld.ui.choice1.setText("ATTACK");
            gameworld.ui.choice2.setText("RUN for your life");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

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
        String tmpText;
        tmpText = "You inspect the " + item.getName().toUpperCase() + ".";
        if (item instanceof Weapon) {
            gameworld.ui.mainTextArea.setText(tmpText +
                    "<br><br>Attack: +" + ((Weapon) item).getDamage() +
                    "<br>Worn weapon's attack: +" + gameworld.player.weapon.getDamage() +
                    "<br>Worth: " + item.getPrice() + " GOLD COINS"
            );
        }
        if (item instanceof Armor){
            Armor tmp = new Armor();
            if (item instanceof Arms) tmp = gameworld.player.arms;
            if (item instanceof Torso) tmp = gameworld.player.torso;
            if (item instanceof Legs) tmp = gameworld.player.legs;
            if (item instanceof Head) tmp = gameworld.player.head;

            gameworld.ui.mainTextArea.setText(tmpText +
                    "<br><br>Defence: +" + ((Armor) item).getDefence() +
                    "<br>Worn armor's defence: +" + tmp.getDefence() +
                    "<br>Worth: " + item.getPrice() + " GOLD COINS"
            );
        }
        if (item instanceof Healing) {
            gameworld.ui.mainTextArea.setText(tmpText +
                    "<br><br>Restoration: " + ((Healing) item).getRestore() +
                    "<br>Current missing HP: " + (gameworld.player.getMaxhp()-gameworld.player.getHp()) +
                    "<br>Worth: " + item.getPrice() + " GOLD COINS"
            );
        }

        gameworld.ui.choice1.setText("TAKE it");
        gameworld.ui.choice2.setText("FORGET it and go look for MORE ENEMIES");
        gameworld.ui.choice3.setText("Get BACK on the ROAD");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "MOUNTAINS_TAKE_DROP";
        gameworld.nextPosition2 = "MOUNTAINS";
        gameworld.nextPosition3 = "FIGHT_CHOOSE";
        gameworld.nextPosition4 = "";

        gameworld.vm.hideUselessChoiceButtons();
    }
    public void takeDroppedItem(){

        Item item = this.drop;
        if (player.howManyItemsInInv()==12) {
            gameworld.ui.mainTextArea.setText("You're carrying TOO MANY ITEMS and can't fit the " +
                    item.getName().toUpperCase() + " in your bag. Maybe if you THROW something OUT...?");

            gameworld.ui.choice1.setText(">");
            gameworld.ui.choice2.setText("");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "MOUNTAINS_DROP";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }
        else{
            gameworld.ui.mainTextArea.setText("You manage to fit the " +
                    item.getName().toUpperCase() + " in your bag. Nice!");

            player.take(item);

            gameworld.ui.choice1.setText(">");
            gameworld.ui.choice2.setText("");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "MOUNTAINS";
            gameworld.nextPosition2 = "";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }

    }
}

