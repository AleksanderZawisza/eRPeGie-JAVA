package game.state;

import game.creature.Player;

public class Game {
    public static void main (String[]args) throws InterruptedException {
        Player player = new Player(100,0,2,0,0);
        GameWorld game = new GameWorld(player);
        game.play();
    }
}
