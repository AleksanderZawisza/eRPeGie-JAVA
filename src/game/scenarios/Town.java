package game.scenarios;

import game.state.GameWorld;

public class Town {

    GameWorld gameworld;

    public Town(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.vm.showChoices();

        gameworld.ui.mainTextArea.setText("You are currently in <font color='red'>TOWN</font>. It is [WEATHER]. " +
                "The poeple around seem [EMOTION]. [OPTIONAL DESCRIPTION?].");

        gameworld.ui.choice1.setText("Find the TAVERN");
        gameworld.ui.choice2.setText("Go to the SHOP");
        gameworld.ui.choice3.setText("CONTEMPLATE your life choices");
        gameworld.ui.choice4.setText("Get OUT OF here");

        gameworld.nextPosition1 = "TAVERN";
        gameworld.nextPosition2 = "SHOP";
        gameworld.nextPosition3 = "CONTEMPLATE";
        gameworld.nextPosition4 = "FIGHT_CHOOSE";
    }

    public void contemplate() {

        gameworld.ui.mainTextArea.setText("Your NAME is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].<br>"+
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT.<br>" +
                "You also enjoy KILLING THINGS sometimes.");

        gameworld.ui.choice1.setText("Get me THE HELL OUT OF HERE");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }
}
