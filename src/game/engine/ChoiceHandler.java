package game.engine;

import game.creature.Player;
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

    Player player = GameWorld.getPlayer();

    public void actionPerformed(ActionEvent event){

        String choice = event.getActionCommand();

        switch(choice) {

            case "input":
                gameworld.setFromInventory(false);
                String text = gameworld.getUi().jtf.getText();
                gameworld.getUi().nameTextLabel.setText("Hi there. What's your NAME?");

                if (text.equals("") || text.equals(" ") || text.equals("  ") || text.equals("   ") || text.equals("    ")) {
                    gameworld.getUi().nameTextLabel.setText("...Could use a little more CREATIVITY.");
                    bullshitCount++;
                    if (bullshitCount>=2) {
                        gameworld.getUi().nameTextLabel.setText("C'mon, I'm not asking for much here.");}
                    if (bullshitCount>=3) {
                        gameworld.getUi().nameTextLabel.setText("Really now?");}
                    if (bullshitCount>=4) {
                        gameworld.getUi().nameTextLabel.setText("So what's it gonne be, FARTMASTER?");
                        player.setSaveName("FARTMASTER");
                        try {
                            gameworld.selectPosition("DESCRIPTION");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    player.fartmasterCount = bullshitCount;
                    break; }
                player.setSaveName(text);
                player.fartmasterCount = bullshitCount;
                try {
                    gameworld.selectPosition("DESCRIPTION");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "start":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition("BEGIN");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c1":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition(gameworld.getNextPosition1());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c2":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition(gameworld.getNextPosition2());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c3":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition(gameworld.getNextPosition3());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "c4":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition(gameworld.getNextPosition4());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "goBackFromLooking":
                gameworld.getVm().changeBackButtonToExit();

            case "inventory":
                gameworld.setFromInventory(true);
                try {
                    gameworld.selectPosition("INVENTORY");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "characterSheet":
                gameworld.setFromInventory(true);
                try {
                    gameworld.selectPosition("CHARACTER_SHEET");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "exit":
                gameworld.setFromInventory(true);
                gameworld.getVm().showChoices();
                try {
                    gameworld.selectPosition(gameworld.getInventory().getLastPosition());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "goBackFromShop":
                gameworld.setFromInventory(false);
                try {
                    gameworld.selectPosition("SHOP");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "end_this": System.exit(0);  break;

        }

        if (choice.startsWith("I")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.getInventory().lookItem(player.getItemFromInv(i));
            gameworld.getInventory().setLastLooked(i);
        }

        if (choice.startsWith("S")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.getShop().sellAThing(i);
        }

        if (choice.startsWith("B")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.getShop().buyAThing(i);
        }

    }

}