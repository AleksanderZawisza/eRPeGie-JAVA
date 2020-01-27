package game.scenarios;

import game.creature.Player;
import game.item.*;
import game.item.armor.*;
import game.state.GameWorld;

import java.io.*;


public class Start {

    GameWorld gameworld;

    public Start(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    Player player = GameWorld.getPlayer();

    public void begin(){

        gameworld.getVm().setEverythingWhite();
        gameworld.getVm().toBegin();
        gameworld.getVm().updateCurrentHPLabel(player.getHp());


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


    public void description() throws IOException {

        String savename = player.savename;
        savename = savename.toUpperCase();
        player.setSavename(savename);
        File file = new File(savename+".txt");
        player.setEverythingToDefault(); // tu do zmiany jak ładowanie postaci
        player.setHp(player.getMaxhp());
        String loaded = "";

        if (file.exists() && !savename.equals("TESTMASTER")){
            loaded = "Welcome back!<br>";
            BufferedReader br = new BufferedReader(new FileReader(file));
            player.setMaxhp(Integer.parseInt(br.readLine()));
            player.setHp(player.getMaxhp());
            player.setArmor(Integer.parseInt(br.readLine()));
            player.setAttack(Integer.parseInt(br.readLine()));
            player.setMoney(Integer.parseInt(br.readLine()));
            player.setExp((float) Double.parseDouble(br.readLine()));
            player.setCurrentQuest(br.readLine());
            player.setQuestCount(Integer.parseInt(br.readLine()));
            player.setMaxQuestCount(Integer.parseInt(br.readLine()));
            player.setKilledPlainsBoss(Boolean.parseBoolean(br.readLine()));
            player.setKilledForestBoss(Boolean.parseBoolean(br.readLine()));
            player.setKilledMountainsBoss(Boolean.parseBoolean(br.readLine()));
            int num = Integer.parseInt(br.readLine());
            for (int i = 0; i < num; i++){
                String clazzName = br.readLine();
                String name = br.readLine();
                int stat = Integer.parseInt(br.readLine());
                int price = Integer.parseInt(br.readLine());
                Item item;
                switch (clazzName){
                    case "Weapon": item = new Weapon(name, stat, price); break;
                    case "Head": item = new Head(name, stat, price); break;
                    case "Arms": item = new Arms(name, stat, price); break;
                    case "Torso": item = new Torso(name, stat, price); break;
                    case "Legs": item = new Legs(name, stat, price); break;
                    case "Healing" : item = new Healing(name, stat, price); break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + clazzName);
                }
                player.take(item);
            }
            // equipped weapon
            String name1 = br.readLine();
            int stat1 = Integer.parseInt(br.readLine());
            int price1 = Integer.parseInt(br.readLine());
            Weapon weapon = new Weapon(name1, stat1, price1);
            player.setWeapon(weapon);
            // equipped head
            String name2 = br.readLine();
            int stat2 = Integer.parseInt(br.readLine());
            int price2 = Integer.parseInt(br.readLine());
            Head head = new Head(name2, stat2, price2);
            player.setHead(head);
            // equipped arms
            String name3 = br.readLine();
            int stat3 = Integer.parseInt(br.readLine());
            int price3 = Integer.parseInt(br.readLine());
            Arms arms = new Arms(name3, stat3, price3);
            player.setArms(arms);
            // equipped torso
            String name4 = br.readLine();
            int stat4 = Integer.parseInt(br.readLine());
            int price4 = Integer.parseInt(br.readLine());
            Torso torso = new Torso(name4, stat4, price4);
            player.setTorso(torso);
            // equipped legs
            String name5 = br.readLine();
            int stat5 = Integer.parseInt(br.readLine());
            int price5 = Integer.parseInt(br.readLine());
            Legs legs = new Legs(name5, stat5, price5);
            player.setLegs(legs);

        }

        if (player.savename.equals("FARTMASTER") && !file.exists()) {
            player.inventory.add(new Weapon("poop on a stick", 1, 1));
            gameworld.getUi().mainTextArea.setText("Fine then, be like that." +
            "<br>Your NAME now is " + player.savename + ", and that's the NAME you're going to have TILL YOU DIE." +
            "<br>Have fun with that, dumbass.");
            gameworld.getUi().choice1.setText("Uh oh");
            if (player.fartmasterCount>=4){
                gameworld.getUi().mainTextArea.setText("<center>Why are you like this</center>");
                gameworld.getUi().choice1.setText("¯\\_(ツ)_/¯");
            }
        }
        else if (player.savename.equals("TESTMASTER")) {
            player.inventory.add(new Weapon("ak-47", 9000, 420));
            player.inventory.add(new Head("cool shades", 9000, 6969));
            player.inventory.add(new Healing("yellow gatorade", 500, 1000));
            player.inventory.add(new Healing("red gatorade", 1000, 5000));
            player.inventory.add(new Healing("blue gatorade", 2000, 10000));
            gameworld.getUi().mainTextArea.setText("Sweet shades, bro." +
                    "<br>Happy hunting " + player.savename + "!");
            gameworld.getUi().choice1.setText("Sure");
        } //cheaty
        else {
            gameworld.getUi().mainTextArea.setText(loaded+"Your NAME now is " + player.savename + "." +
                    "<br>GOOD LUCK on your quest, " + player.savename + "!");
            gameworld.getUi().choice1.setText("Yeah!");
        }

        gameworld.getVm().updateCurrentHPLabel(player.getHp());
        gameworld.getVm().showChoicesWithoutPlayerPanel();
        gameworld.getShop().restockShopInventory();

        gameworld.getUi().choice2.setText("");
        gameworld.getUi().choice3.setText("");
        gameworld.getUi().choice4.setText("");

        gameworld.setNextPosition1("TOWN");
        gameworld.setNextPosition2("");
        gameworld.setNextPosition3("");
        gameworld.setNextPosition4("");


}
}
