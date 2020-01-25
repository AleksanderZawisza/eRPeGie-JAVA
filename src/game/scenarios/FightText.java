package game.scenarios;

import game.creature.Enemy;
import game.item.*;
import game.item.armor.*;
import game.state.GameWorld;

public class FightText {

    GameWorld gameworld;

    public FightText(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You are NEAR THE TOWN GATES. It is [WEATHER]. You see THREE PATHS.<br>" +
                "You decide to:");

        gameworld.ui.choice1.setText("Go EAST, towards the PLAINS");
        gameworld.ui.choice2.setText("Go NORTH, towards the FOREST");
        gameworld.ui.choice3.setText("Go WEST, towards the MOUNTAINS");
        gameworld.ui.choice4.setText("Turn around and GO BACK to the TOWN");

        gameworld.nextPosition1 = "PLAINS";
        gameworld.nextPosition2 = "FOREST";
        gameworld.nextPosition3 = "MOUNTAINS";
        gameworld.nextPosition4 = "TOWN";
    }

    public static void lookingAround(String where, Enemy enemy, GameWorld gameworld) {
        gameworld.ui.mainTextArea.setText("You are now in the "+where+". It is [WEATHER]. You see a " +
                enemy.getName().toUpperCase() + "." +
                "<br>You decide to:");

        gameworld.vm.showChoices();

        gameworld.ui.choice1.setText("GET CLOSER to this creature");
        gameworld.ui.choice2.setText("SEARCH for other enemies");
        gameworld.ui.choice3.setText("GET BACK on the road");
        gameworld.ui.choice4.setText("");
    }

    public static void whatNow(Enemy enemy, GameWorld gameworld){
        gameworld.ui.mainTextArea.setText("The "+enemy.getRace().toUpperCase() + " looks nervous.<br>" +
                "What do you do now?");

        gameworld.ui.choice1.setText("ATTACK");
        gameworld.ui.choice2.setText("LEAVE it be");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");
    }

    public static void inspectDropText(Item item, GameWorld gameworld){
        gameworld.vm.showChoices();

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
    }

    public static void cantPickUpDrop(Item item, GameWorld gameworld){
        gameworld.ui.mainTextArea.setText("You're carrying TOO MANY ITEMS and can't fit the " +
                item.getName().toUpperCase() + " in your bag. Maybe if you THROW something OUT...?");

        gameworld.vm.showChoicesWithoutPlayerPanel();

        gameworld.ui.choice1.setText(">");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");
    }

    public static void pickUpDrop(Item item, GameWorld gameworld){
        gameworld.ui.mainTextArea.setText("You manage to fit the " +
                item.getName().toUpperCase() + " in your bag. Nice!");

        gameworld.vm.showChoicesWithoutPlayerPanel();

        gameworld.ui.choice1.setText(">");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");
    }

    public static void youDied(int attack2, Enemy enemy, GameWorld gameworld){
        gameworld.ui.mainTextArea.setText("The "+ enemy.getName().toUpperCase() + " hurt you for "
                + attack2 + " DMG.<br><br>" +
                "You DIED.<br><br>" +
                "GAME OVER");

        gameworld.ui.choice1.setText("Start a NEW GAME");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");
    }

    public static void enemyStillNotDead(int attack1, int attack2, Enemy enemy, GameWorld gameworld){
        gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " DMG.<br>" +
                "The "+ enemy.getName().toUpperCase() + " hurt you for " + attack2 + " DMG.<br>" +
                "This " + enemy.getRace().toUpperCase() + " has " + enemy.getHp() + " HP now.");

        gameworld.ui.choice1.setText("ATTACK");
        gameworld.ui.choice2.setText("RUN for your life");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");
    }

    public static void animalEnemyDead(Enemy enemy, int attack1, int attack2, GameWorld gameworld){
        String lvlUp = "";

        float receivedExp = enemy.getExp()/20;
        if (receivedExp + gameworld.player.expWithoutLevel()>=1) lvlUp = "You feel more confident. You're pretty sure your THREAT RATING just went up!";

        gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " DMG.<br>" +
                "The " + enemy.getRace().toUpperCase() + " is DEAD.<br>" +
                "You WON the fight!<br><br>" +
                "You think you can sell parts from the DEAD " + enemy.getRace().toUpperCase() +
                " for about " + enemy.getMoney() + " GOLD COINS.<br><br>" +
                lvlUp);

        gameworld.ui.choice1.setText("LOOK for MORE");
        gameworld.ui.choice2.setText("Get BACK on the ROAD");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");


        if (!gameworld.fromInventory){
            //no double rewards + dead enemy doesnt deal dmg
            gameworld.player.addHp(attack2);
            gameworld.player.addMoney(enemy.getMoney());
            gameworld.player.addExp(receivedExp);
            gameworld.player.addDailyKillCount();
            gameworld.player.addKillCount();
        }

    }

    public static void sentientEnemyDead(Enemy enemy, int attack1, int attack2, Item item, GameWorld gameworld){
        String lvlUp = "";

        float receivedExp = enemy.getExp()/20;
        if (receivedExp + gameworld.player.expWithoutLevel()>=1) lvlUp = "You feel more confident. You're pretty sure your THREAT RATING just went up!";
        String plural = "s";
        if (item.getName().endsWith("s")) plural = "";

        gameworld.ui.mainTextArea.setText("You dealt " + attack1 + " DMG.<br>" +
                "The " + enemy.getRace().toUpperCase() + " is DEAD.<br>" +
                "You WON the fight!<br><br>" +
                "You think the DEAD " + enemy.getRace().toUpperCase() + "'S " +
                item.getName().toUpperCase() + " look"+ plural +" pretty serviceable.<br><br>" +
                lvlUp);

        gameworld.ui.choice1.setText("LOOK for MORE");
        gameworld.ui.choice2.setText("INSPECT the ITEM");
        gameworld.ui.choice3.setText("Get BACK on the ROAD");
        gameworld.ui.choice4.setText("");

        if (!gameworld.fromInventory){
            //no double rewards + dead enemy doesnt deal dmg
            gameworld.player.addHp(attack2);
            gameworld.player.addExp(receivedExp);
            gameworld.player.addDailyKillCount();
            gameworld.player.addKillCount();
        }

    }

}
