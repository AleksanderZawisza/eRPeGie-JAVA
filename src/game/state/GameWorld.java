package game.state;

import game.creature.*;
import game.engine.UI;
import game.engine.VisibilityManager;
import game.scenarios.*;


public class GameWorld {

    public static Player player = new Player(100,0,2,0,0);
    public static Enemy currentEnemy = new Enemy();
    public UI ui;
    public Game game;
    public VisibilityManager vm;

    public GameWorld(Game game, UI ui, VisibilityManager vm) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
    }

    public String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    Start start = new Start(this);
    Town town = new Town(this);
    Tavern tavern = new Tavern(this);
    Shop shop = new Shop(this);
    FightChoose fightChoose = new FightChoose(this);
    Plains plains = new Plains(this);
    Forest forest = new Forest(this);
    Mountains mountains = new Mountains(this);


    public void selectPosition(String nextPosition){
        switch(nextPosition){
            case "BEGIN": start.begin(); break;
            case "DESCRIPTION": start.description(); break;
            case "CONTEMPLATE": town.contemplate(); break;
            case "TOWN": town.go(); break;
            case "TOWN_LOOK": town.look(); break;
            case "TAVERN": tavern.go(); break;
            case "DRINK": tavern.drink(); break;
            case "REST": tavern.rest(); break;
            case "SHOP": shop.go(); break;
            case "SELL": shop.sell(); break;
            case "BUY": shop.buy(); break;
            case "FIGHT_CHOOSE": fightChoose.go(); break;
            case "PLAINS": plains.go(); break;
            case "PLAINS_FIGHT_CHOOSE": plains.fightChoose(); break;
            case "PLAINS_FIGHT": plains.fight(); break;
            case "PLAINS_WAIT": plains.waited(); break;
            case "FOREST": forest.go(); break;
            case "FOREST_FIGHT_CHOOSE": forest.fightChoose(); break;
            case "FOREST_FIGHT": forest.fight(); break;
            case "FOREST_WAIT": forest.waited(); break;
            case "MOUNTAINS": mountains.go(); break;
            case "MOUNTAINS_FIGHT_CHOOSE": mountains.fightChoose(); break;
            case "MOUNTAINS_FIGHT": mountains.fight(); break;
            case "MOUNTAINS_WAIT": mountains.waited(); break;
            case "DEAD": break;
        }
    }
}
