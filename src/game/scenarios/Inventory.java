package game.scenarios;

import game.engine.Helper;
import game.item.*;
import game.item.armor.*;
import game.state.GameWorld;

public class Inventory {

    GameWorld gameworld;
    String lastPosition;
    int lastLooked = 0;

    public Inventory(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void characterSheet(){

        gameworld.vm.showCharacterSheet();
        gameworld.ui.characterStatsArea.setText(
                "Current stats:" +
                "\n\nAttack: " + gameworld.player.getAttack() +
                "\nMax HP: " + gameworld.player.getMaxhp() +
                "\nArmor: " + gameworld.player.getArmor() +
                "\nTHREAT RATING: " + (int) gameworld.player.getExp() +
                "\nGold coins: " + gameworld.player.getMoney() +
                "\nKills today: " + "?" + //TODO
                "\n\nCurrent goals:\n" +
                "\nKill things." +
                "\nDon't get killed." +
                "\nHave fun! :)" //TODO
        );
        gameworld.ui.characterEqArea.setText(
                "Equipped items:" +
                "\n\nWeapon: " + gameworld.player.weapon.getName().toUpperCase() + " (+" +  gameworld.player.weapon.getDamage() + ")" +
                "\n\nHead: " + gameworld.player.head.getName().toUpperCase() + " (+" +  gameworld.player.head.getDefence() + ")" +
                "\n\nTorso: " + gameworld.player.torso.getName().toUpperCase() + " (+" +  gameworld.player.torso.getDefence() + ")" +
                "\n\nArms: " + gameworld.player.arms.getName().toUpperCase() + " (+" +  gameworld.player.arms.getDefence() + ")" +
                "\n\nLegs: " + gameworld.player.legs.getName().toUpperCase() + " (+" +  gameworld.player.legs.getDefence() + ")"

        );
    }

    public void manageInventory(){
        int i = 0;
        gameworld.vm.showInventory();
        gameworld.vm.changeBackButtonToExit();

        while (i < gameworld.player.inventory.size() ) {
            gameworld.ui.inventoryChoiceButtons[i].setText((i+1) + ". " +
                    gameworld.player.getItemFromInv(i).getName().substring(0,1).toUpperCase() +
                    gameworld.player.getItemFromInv(i).getName().substring(1) );
            gameworld.ui.inventoryChoiceButtons[i].setActionCommand("I" + i);
            i++;
        }
        while (i < 12 ) {
            gameworld.ui.inventoryChoiceButtons[i].setText("");
            gameworld.ui.inventoryChoiceButtons[i].setActionCommand("");
            gameworld.ui.inventoryChoiceButtons[i].setBorderPainted(false);
            i++;
        }
    }

    public void lookItem(Item item){
        gameworld.vm.showChoicesForLookingAtShit();
        gameworld.vm.changeExitButtonToGoBackFromLooking();
        String tmpText;
        tmpText = item.getName().toUpperCase();
        if (gameworld.player.getHp()>0) tmpText = "You look at the " + tmpText + ".";

        if (item instanceof Weapon) {
            gameworld.ui.mainTextArea.setText(tmpText +
                    "\n\nAttack: +" + ((Weapon) item).getDamage() +
                    "\nWorn weapon's attack: +" + gameworld.player.weapon.getDamage() +
                    "\nWorth: " + item.getPrice() + " GOLD COINS"
            );
        }
        if (item instanceof Armor){
            Armor tmp = new Armor();
            if (item instanceof Arms) tmp = gameworld.player.arms;
            if (item instanceof Torso) tmp = gameworld.player.torso;
            if (item instanceof Legs) tmp = gameworld.player.legs;
            if (item instanceof Head) tmp = gameworld.player.head;

            gameworld.ui.mainTextArea.setText(tmpText +
                    "\n\nDefence: +" + ((Armor) item).getDefence() +
                    "\nWorn armor's defence: +" + tmp.getDefence() +
                    "\nWorth: " + item.getPrice() + " GOLD COINS"
            );
        }
        if (item instanceof Healing) {

            gameworld.ui.mainTextArea.setText(tmpText +
                    "\n\nRestoration: " + ((Healing) item).getRestore() +
                    "\nCurrent missing HP: " + (gameworld.player.getMaxhp()-gameworld.player.getHp()) +
                    "\nWorth: " + item.getPrice() + " GOLD COINS"
            );
        }

        gameworld.ui.choice1.setText("USE this thing");
        gameworld.ui.choice2.setText("Look at the NEXT item");
        gameworld.ui.choice3.setText("Look at the PREVIOUS item");
        gameworld.ui.choice4.setText("YEET it AWAY");

        gameworld.nextPosition1 = "INVENTORY_USE";
        gameworld.nextPosition2 = "INVENTORY_LOOK_NEXT";
        gameworld.nextPosition3 = "INVENTORY_LOOK_PREV";
        gameworld.nextPosition4 = "INVENTORY_YEET";

        if (gameworld.player.getHp()==0){
            gameworld.ui.choice1.setText(">");
            gameworld.ui.choice2.setText("<");
            gameworld.ui.choice3.setText("");
            gameworld.ui.choice4.setText("");

            gameworld.nextPosition1 = "INVENTORY_LOOK_NEXT";
            gameworld.nextPosition2 = "INVENTORY_LOOK_PREV";
            gameworld.nextPosition3 = "";
            gameworld.nextPosition4 = "";
        }

        gameworld.vm.hideUselessChoiceButtons();
    }

    public void use(){
        Item tmp = gameworld.player.getItemFromInv(this.lastLooked);

        if (tmp instanceof Weapon) {
            gameworld.ui.mainTextArea.setText("You let go of the " + gameworld.player.weapon.getName().toUpperCase() +
                    " and arm yourself with the " + tmp.getName().toUpperCase() + "."
            );
        }
        if (tmp instanceof Head) {
            gameworld.ui.mainTextArea.setText("You take off the " + gameworld.player.head.getName().toUpperCase() +
                    " and put on the " + tmp.getName().toUpperCase() + "."
            );
        }
        if (tmp instanceof Torso) {
            gameworld.ui.mainTextArea.setText("You take off the " + gameworld.player.torso.getName().toUpperCase() +
                    " and put on the " + tmp.getName().toUpperCase() + "."
            );
        }
        if (tmp instanceof Arms) {
            gameworld.ui.mainTextArea.setText("You take off the " + gameworld.player.arms.getName().toUpperCase() +
                    " and put on the " + tmp.getName().toUpperCase() + "."
            );
        }
        if (tmp instanceof Legs) {
            gameworld.ui.mainTextArea.setText("You take off the " + gameworld.player.legs.getName().toUpperCase() +
                    " and put on the " + tmp.getName().toUpperCase() + "."
            );
        }
        if (tmp instanceof Healing) {
            gameworld.ui.mainTextArea.setText("You devour the " + tmp.getName().toUpperCase() + " and heal " +
                    Math.min(((Healing) tmp).getRestore(), gameworld.player.getMaxhp()-gameworld.player.getHp()) +
                    " HP."
            );
            gameworld.nextPosition1 = "INVENTORY";
        }
        else {gameworld.nextPosition1 = "INVENTORY_AFTER_EQUIP";}

        gameworld.ui.choice1.setText(">");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");


        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";

        if (tmp instanceof Weapon) gameworld.player.replaceItemInInvWith(this.lastLooked, gameworld.player.weapon);
        else if (tmp instanceof Head) gameworld.player.replaceItemInInvWith(this.lastLooked, gameworld.player.head);
        else if (tmp instanceof Torso) gameworld.player.replaceItemInInvWith(this.lastLooked, gameworld.player.torso);
        else if (tmp instanceof Arms) gameworld.player.replaceItemInInvWith(this.lastLooked, gameworld.player.arms);
        else if (tmp instanceof Legs) gameworld.player.replaceItemInInvWith(this.lastLooked, gameworld.player.legs);

        this.gameworld.player.useItem(tmp);


        if (tmp instanceof Healing) gameworld.player.remove(this.lastLooked);
        gameworld.vm.updateCurrentHPLabel(gameworld.player.getHp());
    }

    public void yeet(){
        gameworld.ui.mainTextArea.setText("You HURL the " + gameworld.player.getItemFromInv(this.lastLooked).getName().toUpperCase() +
                " straight into the sun. SEE YOU SPACE COWBOY..."
        );

        gameworld.player.yeetItemFromInv(this.lastLooked);

        gameworld.ui.choice1.setText(">");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "INVENTORY";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

    public void lookNext(){
        this.lastLooked += 1;
        if (this.lastLooked==gameworld.player.howManyItemsInInv()) this.lastLooked=0;
        lookItem(gameworld.player.getItemFromInv(this.lastLooked));
    }

    public void lookPrev(){
        this.lastLooked -= 1;
        if (this.lastLooked==-1) this.lastLooked=gameworld.player.howManyItemsInInv()-1;
        lookItem(gameworld.player.getItemFromInv(this.lastLooked));
    }

    public void lookAfterSwapping(){
        lookItem(gameworld.player.getItemFromInv(this.lastLooked));
    }

    public String getLastPosition() { return lastPosition;  }
    public void setLastPosition(String lastPosition) { this.lastPosition = lastPosition;  }

    public int getLastLooked() { return lastLooked; }
    public void setLastLooked(int lastLooked) { this.lastLooked = lastLooked; }

}
