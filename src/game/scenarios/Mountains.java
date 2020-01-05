package game.scenarios;

import game.creature.Player;

import java.util.concurrent.TimeUnit;

public class Mountains {

    public void go(Player player) throws InterruptedException { // combat + generator opisów DO ZAIMPLEMENTOWANIA

        System.out.print("\nYou are now in the MOUNTAINS. It is [WEATHER]. There's ABSOLUTELY NOTHING AROUND.\n");
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.print("You GET THE HELL OUT OF HERE.\n");

        player.setSTATE("FIGHT_CHOOSE");
    }
}
