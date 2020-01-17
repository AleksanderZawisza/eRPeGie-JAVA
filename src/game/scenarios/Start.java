package game.scenarios;

import game.creature.Player;
import game.item.*;
import game.item.armor.*;
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
        System.out.print("<br>" + savename + ", right? [Y/N]<br>> ");
        String answer = input.nextLine();
        answer = answer.toLowerCase();
        while (!answer.toLowerCase().startsWith("y")) {
            System.out.print("<br>Well then, what is it?<br>> ");
            savename = input.nextLine();
            savename = savename.toUpperCase();
            System.out.print("<br>" + savename + "? [Y/N]<br>> ");
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
            player.inventory.add(new Weapon("poop on a stick", 1, 1));
            gameworld.ui.mainTextArea.setText("Fine then, be like that." +
            "<br>Your NAME now is " + player.savename + ", and that's the NAME you're going to have TILL YOU DIE." +
            "<br>Have fun with that, dumbass.");
            gameworld.ui.choice1.setText("Uh oh");
            if (player.fartmasterCount>=4){
                gameworld.ui.mainTextArea.setText("<center>Why are you like this</center>");
                gameworld.ui.choice1.setText("¯\\_(ツ)_/¯");
            }
        }
        else if (player.savename.equals("TESTMASTER")) {
            player.inventory.add(new Weapon("ak-47", 9000, 420));
            player.inventory.add(new Head("cool shades", 9000, 6969));
            gameworld.ui.mainTextArea.setText("Sweet shades, bro." +
                    "<br>Happy hunting " + player.savename + "!");
            gameworld.ui.choice1.setText("Sure");
        } //cheaty
        else {
            gameworld.ui.mainTextArea.setText("Your NAME now is " + player.savename + "." +
                    "<br>GOOD LUCK on your quest, " + player.savename + "!");
            gameworld.ui.choice1.setText("Yeah!");
        }

        gameworld.ui.choice2.setText("");
        gameworld.ui.choice3.setText("");
        gameworld.ui.choice4.setText("");

        gameworld.nextPosition1 = "TOWN";
        gameworld.nextPosition2 = "";
        gameworld.nextPosition3 = "";
        gameworld.nextPosition4 = "";

        gameworld.vm.showChoicesWithoutPlayerPanel();
}
}
