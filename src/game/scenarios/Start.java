package game.scenarios;

import game.creature.Player;
import game.state.GameWorld;


public class Start {

    GameWorld gameworld;

    public Start(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = gameworld.player;

    public void begin(){

        gameworld.vm.toBegin();
        gameworld.vm.updateCurrentHPLabel(player.getHp());

        /* EW DO IMPLEMENTACJI
        System.out.print("\n" + savename + ", right? [Y/N]\n> ");
        String answer = input.nextLine();
        answer = answer.toLowerCase();
        while (!answer.toLowerCase().startsWith("y")) {
            System.out.print("\nWell then, what is it?\n> ");
            savename = input.nextLine();
            savename = savename.toUpperCase();
            System.out.print("\n" + savename + "? [Y/N]\n> ");
            answer = input.nextLine();
            answer = answer.toLowerCase();
        }  // savename - do wczytywania save'ow DO ZAIMPLEMENTOWANIA
        */
    }

    public void description() {

        String savename = player.savename;
        savename = savename.toUpperCase();
        player.setSavename(savename);
        player.setEverythingToDefault(); // tu do zmiany jak ładowanie postaci
        player.setHp(player.getMaxhp());
        gameworld.vm.updateCurrentHPLabel(player.getHp());

        if (player.savename.equals("FARTMASTER")) {
            gameworld.ui.mainTextArea.setText("Fine then, be like that." +
            "\nYour NAME now is " + player.savename + ", and that's the NAME you're going to have TILL YOU DIE." +
            "\nGOOD LUCK on your adventure, " + player.savename + "!");
            gameworld.ui.choice1.setText("Uh oh");
        }
        else {
            gameworld.ui.mainTextArea.setText("Your NAME now is " + player.savename + "." +
                    "\nGOOD LUCK on your adventure, " + player.savename + "!");
            gameworld.ui.choice1.setText("Yeah!");
        }
        //It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
        //"You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
          //      "You also enjoy KILLING THINGS sometimes.

        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";

        gameworld.vm.showChoices();
}
}
