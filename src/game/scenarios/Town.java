package game.scenarios;

import game.creature.Player;
import game.state.GameWorld;

public class Town {

    GameWorld gameworld;

    public Town(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You are currently in TOWN. It is [WEATHER]. The poeple around seem [EMOTION]. [OPTIONAL DESCRIPTION?].");

        gameworld.ui.choice1.setText("Look AROUND");
        gameworld.ui.choice2.setText("CONTEMPLATE your life choices");
        gameworld.ui.choice3.setText("Get OUT OF here");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN_LOOK";
        gameworld.nextPosition2 = "CONTEMPLATE";
        gameworld.nextPosition3 = "FIGHT_CHOOSE";
        gameworld.nextPosition4 = "";
    }

    public void look() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You start WANDERING AROUND. You see [RANDOM PERSON/GROUP]. [SHE/HE/THEY] seem [EMOTION]. [OPTIONAL DESCRIPTION?].");

        gameworld.ui.choice1.setText("Find the TAVERN");
        gameworld.ui.choice2.setText("Go to the SHOP");
        gameworld.ui.choice3.setText("Stop wandering around");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TAVERN";
        gameworld.nextPosition2 = "SHOP";
        gameworld.nextPosition3 = "TOWN";
        gameworld.nextPosition4 = "";
    }

    public void contemplate() {

        gameworld.ui.mainTextArea.setText("Your NAME is [NAME]. It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n"+
                "You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT.\n" +
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
