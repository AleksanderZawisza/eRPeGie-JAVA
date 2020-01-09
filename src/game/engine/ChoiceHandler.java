package game.engine;

import game.state.GameWorld;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


public class ChoiceHandler implements ActionListener{

    GameWorld gameworld;

    public ChoiceHandler(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void actionPerformed(ActionEvent event){

        String choice = event.getActionCommand();

        switch(choice) {

            case "input":
                String text = gameworld.ui.jtf.getText();
                gameworld.player.setSavename(text);
                gameworld.selectPosition("DESCRIPTION");
                break;

            case "start":
                gameworld.selectPosition("BEGIN");
                break;

            case "c1":
                gameworld.selectPosition(gameworld.nextPosition1);
                break;

            case "c2":
                gameworld.selectPosition(gameworld.nextPosition2);
                break;

            case "c3":
                gameworld.selectPosition(gameworld.nextPosition3);
                break;

            case "c4":
                gameworld.selectPosition(gameworld.nextPosition4);
                break;

            case "goBackFromLooking":  gameworld.vm.changeBackButtonToExit();
            case "inventory":
                gameworld.selectPosition("INVENTORY");
                break;

            case "characterSheet":
                gameworld.selectPosition("CHARACTER_SHEET");
                break;

            case "exit":
                gameworld.vm.showChoices();
                gameworld.selectPosition(gameworld.inventory.getLastPosition());
        }

        if (choice.contains("I")) {
            int i = Integer.parseInt(choice.substring(1));
            gameworld.inventory.lookItem(gameworld.player.getItemFromInv(i));
            gameworld.inventory.setLastLooked(i);
        }



    }

}