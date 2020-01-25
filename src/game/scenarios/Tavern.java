package game.scenarios;

import game.creature.Player;
import game.item.Armor;
import game.item.Healing;
import game.item.Weapon;
import game.state.GameWorld;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Tavern {

    GameWorld gameworld;
    Player player;

    public Tavern(GameWorld gameworld) {
        this.gameworld = gameworld;
        this.player = gameworld.player;
    }

    public void go() { // generator opisów DO ZAIMPLEMENTOWANIA

        gameworld.ui.mainTextArea.setText("You manage to find the TAVERN. You see [RANDOM PERSON/GROUP] in the corner. The barmaid looks [EMOTION]. [OPTIONAL DESCRIPTION?].<br>" +
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
        if(gameworld.trueLastState.equals("DRINK")) tmp = "another STIFF DRINK. It's a ";

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
                "<br>You lie down and MAKE AN ENTRY in your JOURNAL.<br>" + "Your bed is [DESCRIPTION].<br>" +
                "You hear [SOUND] in the background.<br>" + "The pillows are [DESCRIPTION].<br>" +
                "...<br>" + "Rise and shine! It's a NEW DAY. The SUN is UP and SO ARE YOU. You get up and GET OUT.<br>");

        player.setHp(player.getMaxhp());    // HEALOWANIE I UPDATE HP
        gameworld.vm.updateCurrentHPLabel(player.getHp());

        player.addOneDay(); //+1 do licznika dni i reset dailyKillCount
        player.resetDailyKillCount();

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(player.savename + ".txt"));
            bw.write(""+player.getHp()); bw.newLine();
            bw.write(""+player.getArmor()); bw.newLine();
            bw.write(""+player.getAttack()); bw.newLine();
            bw.write(""+player.getMoney()); bw.newLine();
            bw.write(""+player.getExp()); bw.newLine();
            bw.write(""+player.howManyItemsInInv()); bw.newLine();
            for (int i = 0; i < player.howManyItemsInInv(); i++){
                String p = String.valueOf(player.getItemFromInv(i).getClass().getSimpleName());
                bw.write(p); bw.newLine();
                bw.write(player.getItemFromInv(i).getName()); bw.newLine();
                bw.write(""+player.getItemFromInv(i).getStat()); bw.newLine();
                bw.write(""+player.getItemFromInv(i).getPrice()); bw.newLine();
            }
            // equipped weapon
            bw.write(player.getWeapon().getName()); bw.newLine();
            bw.write(""+player.getWeapon().getStat()); bw.newLine();
            bw.write(""+player.getWeapon().getPrice()); bw.newLine();
            // equipped head
            bw.write(player.getHead().getName()); bw.newLine();
            bw.write(""+player.getHead().getStat()); bw.newLine();
            bw.write(""+player.getHead().getPrice()); bw.newLine();
            // equipped arms
            bw.write(player.getArms().getName()); bw.newLine();
            bw.write(""+player.getArms().getStat()); bw.newLine();
            bw.write(""+player.getArms().getPrice()); bw.newLine();
            // equipped torso
            bw.write(player.getTorso().getName()); bw.newLine();
            bw.write(""+player.getTorso().getStat()); bw.newLine();
            bw.write(""+player.getTorso().getPrice()); bw.newLine();
            // equipped legs
            bw.write(player.getLegs().getName()); bw.newLine();
            bw.write(""+player.getLegs().getStat()); bw.newLine();
            bw.write(""+player.getLegs().getPrice()); bw.newLine();

            bw.close();
        }
        catch (Exception e){
            System.err.format("Exception: %s%n", e);
        }

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
