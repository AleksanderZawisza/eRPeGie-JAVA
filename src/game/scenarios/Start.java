package game.scenarios;

import game.creature.Player;
import game.state.GameWorld;


public class Start {

    GameWorld gameworld;

    public Start(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = GameWorld.player;
    int startHp = 100;
    int startMaxhp = 100;
    int startAttack = 2;
    int startExp = 0;
    int startMoney = 0;
    int startArmor = 0;


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

        player.setHp(startHp);
        player.setMaxhp(startMaxhp);
        player.setAttack(startAttack);
        player.setExp(startExp);
        player.setMoney(startMoney);
        player.setArmor(startArmor);


        gameworld.ui.mainTextArea.setText("Your NAME is " + player.savename);

        //It is currently [DAY/WEATHER]. You are a [TRAIT], [TRAIT] [AGE] [GENDER].\n" +
        //"You have a fondness for [HOBBY] and are an ASPIRING [JOB]. You like to [HOBBY] but are NOT VERY GOOD AT IT. \n" +
          //      "You also enjoy KILLING THINGS sometimes.

        gameworld.ui.choice1.setText("Understood.");
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
