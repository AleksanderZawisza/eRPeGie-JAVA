package console.swinggamingconsole;

import console.swinggamingconsole.session.Session;
import console.swinggamingconsole.session.example.ExampleSession;
import console.swinggamingconsole.session.example.ExampleStagedSession;
import console.swinggamingconsole.ui.GameWindow;
import console.swinggamingconsole.util.StringUtils;

/**
 * The class that handles everything having to do with the console, and input
 * field. The core of the program.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public final class GameHandler {

	/**
	 * The game window receiving input from the user.
	 */
	private GameWindow window;

	/**
	 * The current <@link Session> the user is in.
	 */
	private Session currentSession;

	/*
	 * The different session instances, cascading setup for parents.
	 */

	/**
	 * The session instance for the example session.
	 */
	private ExampleSession exampleSession = new ExampleSession();

	/**
	 * The session instance for the example staged session.
	 */
	private ExampleStagedSession exampleStagedSession = new ExampleStagedSession(exampleSession);

	/**
	 * The class constructor.
	 * 
	 * @param window
	 *            The game window the user is operating.
	 */
	public GameHandler(GameWindow window) {
		this.window = window;
	}

	/**
	 * Starts a new session in the console.
	 * 
	 * @param newSession
	 *            The new desired session.
	 */
	public void newSession(Session newSession) {
		this.currentSession = newSession;
		this.currentSession.start();
	}

	/**
	 * Adds text to the console text area.
	 * 
	 * @param text
	 *            The string to add to the text area.
	 */
	public void addConsoleText(String text) {
		window.getConsole().setText(window.getConsole().getText().concat("\n" + text));
	}

	/**
	 * Inserts a line break of dashes into the console.
	 */
	public void insertLineBreak() {
		this.addConsoleText(StringUtils.repeat("-", Game.getGameHandler().getWindow().getConsole().getColumns()));
	}

	/**
	 * Returns the game window.
	 * 
	 * @return The game window.
	 */
	public GameWindow getWindow() {
		return window;
	}

	/**
	 * Returns the current session.
	 * 
	 * @return The current session.
	 */
	public Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Returns the example session.
	 * 
	 * @return The example session.
	 */
	public ExampleSession getExampleSession() {
		return exampleSession;
	}

	/**
	 * Returns the example staged session.
	 * 
	 * @return The example staged session.
	 */
	public ExampleStagedSession getExampleStagedSession() {
		return exampleStagedSession;
	}
}