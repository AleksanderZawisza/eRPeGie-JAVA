package game.state;

import java.util.Scanner;

public class Game {
    public static void main (String[]args) throws InterruptedException {
        GameWorld game = new GameWorld();
        game.play();
    }
}
