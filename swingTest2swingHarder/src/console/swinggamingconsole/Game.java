package console.swinggamingconsole;

import console.swinggamingconsole.ui.GameWindow;

/**
 * This is the main class of the Swing Gaming Console.
 * 
 * Swing gaming console is a ready-to-use release of the system found in my two
 * projects, Game Store Manager, and Dragon Slayer. It uses a custom GUI made in
 * Swing to interact with the player, used to create fun text based games for
 * those who wish to.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public class Game {

	/**
	 * The name of our game, the UI calls this in the beginning for what to
	 * welcome the player with.
	 */
	public static final String GAME_NAME = "SWING GAMING CONSOLE";

	/**
	 * The instance of the <@link GameWindow> object.
	 */
	private static GameWindow gameWindow;

	/**
	 * The class that handles all the core game methods, to keep this class from
	 * becoming cluttered.
	 */
	private static GameHandler gameHandler;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            The console arguments.
	 */
	public static void main(String[] args) {
		gameWindow = GameWindow.createGameWindow();
		gameHandler = new GameHandler(gameWindow);

		gameHandler.newSession(gameHandler.getExampleSession());
	}

	/**
	 * Gets the <@link GameHandler> instance in this class.
	 * 
	 * @return The game handler instance.
	 */
	public static GameHandler getGameHandler() {
		return gameHandler;
	}
}