package game.state;

import game.creature.*;
import game.scenarios.*;


public class GameWorld {

    public GameWorld(Player player) {
        this.player = player;
    }

    Player player;
    Begin begin = new Begin();
    CharacterDescription description = new CharacterDescription();
    Town town = new Town();
    Tavern tavern = new Tavern();
    Shop shop = new Shop();
    FightChoose fightChoose = new FightChoose();
    Plains plains = new Plains();
    Forest forest = new Forest();
    Mountains mountains = new Mountains();


    public void play () throws InterruptedException {
        begin.go(player);
        description.go(player);
        while (!player.getSTATE().equals("DEAD")) {
            switch(player.getSTATE()){
                case "CONTEMPLATE": tavern.contemplate(player); break;
                case "TOWN": town.go(player); break;
                case "TOWN_LOOK": town.look(player); break;
                case "TAVERN": tavern.go(player); break;
                case "DRINK": tavern.drink(player); break;
                case "REST": tavern.rest(player); break;
                case "SHOP": shop.go(player); break;
                case "SELL": shop.sell(player); break;
                case "BUY": shop.buy(player); break;
                case "FIGHT_CHOOSE": fightChoose.go(player); break;
                case "PLAINS": plains.go(player); break;
                case "FOREST": forest.go(player); break;
                case "MOUNTAINS": mountains.go(player); break;
                case "DEAD": break;
            }
        }

    }
}
