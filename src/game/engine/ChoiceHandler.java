package game.engine;

import game.state.GameWorld;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;


public class ChoiceHandler implements ActionListener{

    GameWorld gameworld;
    int bullshitCount = 0;

    public ChoiceHandler(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void actionPerformed(ActionEvent event){

        String choice = event.getActionCommand();

        switch(choice) {

            case "input":
                gameworld.fromInventory = false;
                String text = gameworld.ui.jtf.getText();
                gameworld.ui.nameTextLabel.setText("Hi there. What's your NAME?");

                if (text.equals("") || text.equals(" ") || text.equals("  ") || text.equals("   ") || text.equals("   ")) {
                    gameworld.ui.nameTextLabel.setText("...Could use a little more CREATIVITY.");
                    bullshitCount++;
                    if (bullshitCount>=2) {
                        gameworld.ui.nameTextLabel.setText("C'mon, I'm not asking for much here.");}
                    if (bullshitCount>=3) {
                        gameworld.ui.nameTextLabel.setText("Really now?");}
                    if (bullshitCount>=4) {
                        gameworld.ui.nameTextLabel.setText("So what's it gonne be, FARTMASTER?");
                        gameworld.player.setSavename("FARTMASTER");
                        try {
                            gameworld.selectPosition("DESCRIPTION");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    gameworld.player.fartmasterCount = bullshitCount;
                    break; }
                gameworld.player.setSavename(text);
                gameworld.player.fartmasterCount = bullshitCount;
                try {
                    gameworld.selectPosition("DESCRIPTION");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "start":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition("BEGIN");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c1":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition(gameworld.nextPosition1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c2":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition(gameworld.nextPosition2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c3":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition(gameworld.nextPosition3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c4":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition(gameworld.nextPosition4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "goBackFromLooking":
                gameworld.vm.changeBackButtonToExit();

            case "inventory":
                gameworld.fromInventory = true;
                try {
                    gameworld.selectPosition("INVENTORY");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "characterSheet":
                gameworld.fromInventory = true;
                try {
                    gameworld.selectPosition("CHARACTER_SHEET");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "exit":
                gameworld.fromInventory = true;
                gameworld.vm.showChoices();
                try {
                    gameworld.selectPosition(gameworld.inventory.getLastPosition());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "goBackFromShop":
                gameworld.fromInventory = false;
                try {
                    gameworld.selectPosition("SHOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

        if (choice.startsWith("I")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.inventory.lookItem(gameworld.player.getItemFromInv(i));
            gameworld.inventory.setLastLooked(i);
        }

        if (choice.startsWith("S")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.shop.sellAThing(i);
        }

        if (choice.startsWith("B")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.shop.buyAThing(i);
        }

    }

}