package game.scenarios;

import game.creature.Player;
import game.generators.HealingGenerator;
import game.generators.ItemGenerator;
import game.item.*;
import game.item.armor.*;
import game.state.GameWorld;

import java.util.ArrayList;
import java.util.Random;

public class Shop {

    GameWorld gameworld;
    Player player = gameworld.player;
    ArrayList<Item> shopStock;
    Random rand = new Random();

    public Shop(GameWorld gameworld) {
        this.gameworld = gameworld;
        this.shopStock = new ArrayList<Item>();
        restockShopInventory();
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.vm.showChoices();
        gameworld.vm.changeBackButtonToExit();
        String restock = "";
        if (gameworld.player.getLastShopRestock() != gameworld.player.getDayCount()) {
            restockShopInventory();
            restock = "He also says the he's got NEW STUFF you haven't seen yet.<br>";
            gameworld.player.setLastShopRestock(gameworld.player.getDayCount());
        }

        gameworld.ui.mainTextArea.setText("You are in the SHOP. The SHOPKEEPER seems [EMOTION] when he sees you. He asks if you're interested in BUYING or SELLING.<br>" +
                restock +
                "<br>You have " + player.getMoney() + " GOLD COINS on you.<br>" +
                "<br>You say that you want to...<br>");

        gameworld.ui.choice1.setText("BUY stuff");
        gameworld.ui.choice2.setText("SELL stuff");
        gameworld.ui.choice3.setText("GET OUT of here");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "BUY";
        gameworld.nextPosition2 = "SELL";
        gameworld.nextPosition3 = "TOWN";
        gameworld.nextPosition4 = "";
    }
    public void buy(){ // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.shopTextArea.setText("You want to BUY SOMETHING. You start BROWSING AROUND." +
                "<br><br>Current worn items:" +
                "<br><br>Weapon ATT: " + player.weapon.getStat() +
                "<br>Head DEF: " + player.head.getStat() +
                "<br>Torso DEF: " + player.torso.getStat() +
                "<br>Arm DEF: " + player.arms.getStat() +
                "<br>Leg DEF: " + player.legs.getStat() +
                "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" );

        updateBuyList();

        gameworld.vm.showShopScreen();
        gameworld.vm.changeExitButtonToGoBackFromShop();
    }

    public void sell(){ // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.shopTextArea.setText("You want to SELL SOMETHING. Now you just have to decide what, exactly, you want to GET RID OF." +
                "<br><br>Current worn items:" +
                "<br><br>Weapon ATT: " + player.weapon.getStat() +
                "<br>Head DEF: " + player.head.getStat() +
                "<br>Torso DEF: " + player.torso.getStat() +
                "<br>Arm DEF: " + player.arms.getStat() +
                "<br>Leg DEF: " + player.legs.getStat() +
                "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" );

        updateSellList();

        gameworld.vm.showShopScreen();
        gameworld.vm.changeExitButtonToGoBackFromShop();

    }

    public void restockShopInventory() {
            this.shopStock.clear();
            int i = 0;
            int itemCount = rand.nextInt(4) + 4;
            int healCount = itemCount + rand.nextInt(3) + 3;
            while (i<itemCount) {this.shopStock.add(ItemGenerator.newItem()); i++;}
            while (i<healCount) {this.shopStock.add(HealingGenerator.newHealingNotPotion()); i++; }
    }

    public int howManyItemsInShop(){
        return this.shopStock.size();
    }

    public void buyAThing(int numberOfThing){
        if (gameworld.player.howManyItemsInInv()==12){
            gameworld.ui.shopTextArea.setText("You sigh wistfully. You really wanna BUY THIS THING but you CAN'T because there's NO SPACE left in your BAG!" +
                    "<br><br>Current worn items:" +
                    "<br><br>Weapon ATT: " + player.weapon.getStat() +
                    "<br>Head DEF: " + player.head.getStat() +
                    "<br>Torso DEF: " + player.torso.getStat() +
                    "<br>Arm DEF: " + player.arms.getStat() +
                    "<br>Leg DEF: " + player.legs.getStat() +
                    "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" ); //update text
        }
        else if (player.getMoney()<this.shopStock.get(numberOfThing).getPrice()){
            gameworld.ui.shopTextArea.setText("You sigh wistfully. You really wanna BUY THIS THING but you CAN'T because you're TOO BROKE!" +
                    "<br><br>Current worn items:" +
                    "<br><br>Weapon ATT: " + player.weapon.getStat() +
                    "<br>Head DEF: " + player.head.getStat() +
                    "<br>Torso DEF: " + player.torso.getStat() +
                    "<br>Arm DEF: " + player.arms.getStat() +
                    "<br>Leg DEF: " + player.legs.getStat() +
                    "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" ); //update text
        }
        else{
            player.lowerMoney(shopStock.get(numberOfThing).getPrice());
            player.addItemToInv(shopStock.get(numberOfThing));
            shopStock.remove(numberOfThing);
            gameworld.ui.shopTextArea.setText("Wonder of wonders, you had enough GOLD COINS so THE THING is now YOURS. Nice!" +
                    "<br><br>Weapon ATT: " + player.weapon.getStat() +
                    "<br>Head DEF: " + player.head.getStat() +
                    "<br>Torso DEF: " + player.torso.getStat() +
                    "<br>Arm DEF: " + player.arms.getStat() +
                    "<br>Leg DEF: " + player.legs.getStat() +
                    "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" ); //update text
            updateBuyList();
        }
    }

    public void sellAThing(int numberOfThing){
        player.addMoney( player.getItemFromInv(numberOfThing).getPrice() );
        this.shopStock.add( player.getItemFromInv(numberOfThing) );
        player.removeItemFromInv(numberOfThing);

        gameworld.ui.shopTextArea.setText("GOOD RIDDANCE. Anything else?" +
                "<br><br>Current worn items:" +
                "<br><br>Weapon ATT: " + player.weapon.getStat() +
                "<br>Head DEF: " + player.head.getStat() +
                "<br>Torso DEF: " + player.torso.getStat() +
                "<br>Arm DEF: " + player.arms.getStat() +
                "<br>Leg DEF: " + player.legs.getStat() +
                "<br><br>You have " + player.getMoney() + " GOLD COINS on you.<br>" ); //update text

        updateSellList();
    }

    public void updateBuyList(){
        int i = 0;
        while (i < howManyItemsInShop() && i < 12 ) {
            gameworld.ui.shopChoiceButtons[i].setText(
                    this.shopStock.get(i).getName().toUpperCase() +
                    " [+" + this.shopStock.get(i).getStat() + "] {" +
                    this.shopStock.get(i).getPrice() + " G}"
            );
            gameworld.ui.shopChoiceButtons[i].setActionCommand("B" + i);
            gameworld.ui.shopChoiceButtons[i].setBorderPainted(true);
            i++;
        }
        while (i < 12 ) {
            gameworld.ui.shopChoiceButtons[i].setText("");
            gameworld.ui.shopChoiceButtons[i].setActionCommand("");
            gameworld.ui.shopChoiceButtons[i].setBorderPainted(false);
            i++;
        }
    }

    public void updateSellList(){
        int i = 0;
        while (i < gameworld.player.howManyItemsInInv() ) {
            gameworld.ui.shopChoiceButtons[i].setText(
                    gameworld.player.getItemFromInv(i).getName().toUpperCase() +
                    " [+" + gameworld.player.getItemFromInv(i).getStat() + "] {" +
                    gameworld.player.getItemFromInv(i).getPrice() + " G}"
            );
            gameworld.ui.shopChoiceButtons[i].setActionCommand("S" + i);
            gameworld.ui.shopChoiceButtons[i].setBorderPainted(true);
            i++;
        }
        while (i < 12 ) {
            gameworld.ui.shopChoiceButtons[i].setText("");
            gameworld.ui.shopChoiceButtons[i].setActionCommand("");
            gameworld.ui.shopChoiceButtons[i].setBorderPainted(false);
            i++;
        }
    }

}
