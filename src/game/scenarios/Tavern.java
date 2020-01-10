package game.scenarios;

import game.creature.Player;
import game.state.GameWorld;

public class Tavern {

    GameWorld gameworld;
    Player player;

    public Tavern(GameWorld gameworld) {
        this.gameworld = gameworld;
        this.player = gameworld.player;
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You manage to find the TAVERN. You see [RANDOM PERSON/GROUP] in the corner. The barmaid looks [EMOTION]. [OPTIONAL DESCRIPTION?].\n" +
                "It's time to...");

        gameworld.ui.choice1.setText("Buy yourself a STIFF DRINK");
        gameworld.ui.choice2.setText("Get a room and HIT THE HAY");
        gameworld.ui.choice3.setText("Get OUT OF here");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "DRINK";
        gameworld.nextPosition2 = "REST";
        gameworld.nextPosition3 = "TOWN";
        gameworld.nextPosition4 = "";
    }

    public void drink() { // questy/potrzeba pieniedzy do kupowania alko + generator opisów DO ZAIMPLEMENTOWANIA
        String tmp = "a ";
        if(gameworld.lastState.equals("DRINK")) tmp = "another STIFF DRINK. It's a ";

        gameworld.ui.mainTextArea.setText("You get yourself " + tmp + "[DRINK]. It tastes [TASTE]. [GENERAL OPINION]. ");

        gameworld.ui.choice1.setText("Buy yourself ANOTHER STIFF DRINK");
        gameworld.ui.choice2.setText("Get a room and HIT THE HAY");
        gameworld.ui.choice3.setText("Get OUT OF here");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "DRINK";
        gameworld.nextPosition2 = "REST";
        gameworld.nextPosition3 = "TOWN";
        gameworld.nextPosition4 = "";
    }

    public void rest(){ // save + generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You rent a room and decide to TAKE A BREATHER for the rest of the day." +
                "You lie down and MAKE AN ENTRY in your JOURNAL.\n" + "Your bed is [DESCRIPTION].\n" +
                "You hear [SOUND] in the background.\n" + "The pillows are [DESCRIPTION].\n" +
                "...\n" + "Rise and shine! It's a NEW DAY. The SUN is UP and SO ARE YOU. You get up and GET OUT.\n");

        player.setHp(player.getMaxhp());    // HEALOWANIE I UPDATE HP
        gameworld.vm.updateCurrentHPLabel(player.getHp());

        gameworld.ui.choice1.setText("Start a NEW DAY");
        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";
    }
}
