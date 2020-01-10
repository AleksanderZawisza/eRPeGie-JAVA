package game.engine;

import game.state.GameWorld;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


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
                    if (bullshitCount>=3) {
                        gameworld.ui.nameTextLabel.setText("C'mon, I'm not asking for much here.");}
                    if (bullshitCount>=9) {
                        gameworld.ui.nameTextLabel.setText("Really now?");}
                    if (bullshitCount>=16) {
                        gameworld.ui.nameTextLabel.setText("So what's it gonne be, FARTMASTER?");
                        gameworld.player.setSavename("fartmaster");
                        gameworld.selectPosition("DESCRIPTION");
                        }
                    break; }
                gameworld.player.setSavename(text);
                gameworld.selectPosition("DESCRIPTION");
                break;

            case "start":
                gameworld.fromInventory = false;
                gameworld.selectPosition("BEGIN");
                break;

            case "c1":
                gameworld.fromInventory = false;
                gameworld.selectPosition(gameworld.nextPosition1);
                break;

            case "c2":
                gameworld.fromInventory = false;
                gameworld.selectPosition(gameworld.nextPosition2);
                break;

            case "c3":
                gameworld.fromInventory = false;
                gameworld.selectPosition(gameworld.nextPosition3);
                break;

            case "c4":
                gameworld.fromInventory = false;
                gameworld.selectPosition(gameworld.nextPosition4);
                break;

            case "goBackFromLooking":
                gameworld.vm.changeBackButtonToExit();

            case "inventory":
                gameworld.fromInventory = true;
                gameworld.selectPosition("INVENTORY");
                break;

            case "characterSheet":
                gameworld.fromInventory = true;
                gameworld.selectPosition("CHARACTER_SHEET");
                break;

            case "exit":
                gameworld.fromInventory = true;
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