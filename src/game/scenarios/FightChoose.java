package game.scenarios;

import game.state.GameWorld;

public class FightChoose {

    GameWorld gameworld;

    public FightChoose(GameWorld gameworld) {
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
}
