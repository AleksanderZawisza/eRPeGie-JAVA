package game.scenarios;

import game.creature.Player;
import game.state.GameWorld;

public class Shop {

    GameWorld gameworld;

    public Shop(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You go to the SHOP. The SHOPKEEPER seems [EMOTION] when he sees you. He asks if you're interested in BUYING or SELLING.\n" +
                "You have " + player.getMoney() + " GOLD COINS on you.\n" +
                "You say that you want to...\n");

        gameworld.ui.choice1.setText("BUY stuff");
        gameworld.ui.choice2.setText("SELL stuff");
        gameworld.ui.choice3.setText("GET OUT of here");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "BUY";
        gameworld.nextPosition2 = "SELL";
        gameworld.nextPosition3 = "TOWN";
        gameworld.nextPosition4 = "";
    }
    public void buy(){ // sklep kupowanie + generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You want to BUY SOMETHING. You can't do that because there is no SHOP in the GAME (yet).\n" +
                "You have " + player.getMoney() + " GOLD COINS on you.\n" +
                "You say that you want to\n");

        gameworld.ui.choice1.setText("GET OUT of here.");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }

    public void sell(){ // sklep sprzedawanie + generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You want to SELL SOMETHING. You can't do that because there is no SHOP in the GAME (yet).\n" +
                "You have " + player.getMoney() + " GOLD COINS on you.\n" +
                "You say that you want to\n");

        gameworld.ui.choice1.setText("GET OUT of here.");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }
}
