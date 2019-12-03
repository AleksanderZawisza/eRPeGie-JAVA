package console.swinggamingconsole.session;

/**
 * This class defines a session. A session is the name in this application for
 * what is currently going on in the console.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public abstract class Session {

	/**
	 * The name of the session instance.
	 */
	private String sessionName;

	/**
	 * The parent session of the session instance. If there is no parent, the
	 * value is null.
	 */
	private Session parent;

	/**
	 * The constructor for this class.
	 * 
	 * @param sessionName
	 *            The name of the session instance.
	 * @param parent
	 *            The parent session of the session instance.
	 */
	public Session(String sessionName, Session parent) {
		this.sessionName = sessionName;
		this.parent = parent;
	}

	/**
	 * Returns the name of the current session.
	 * 
	 * @return The name of the current session.
	 */
	public String getSessionName() {
		return this.sessionName;
	}

	/**
	 * Returns the name of the parent session.
	 * 
	 * @return The name of the parent session.
	 */
	public Session getParent() {
		return this.parent;
	}

	/**
	 * The method that executes first when the session is added to the game
	 * window.
	 */
	public abstract void start();

	/**
	 * What types of input to expect from the user.
	 * 
	 * @param input
	 *            The users input, to be checked
	 */
	public abstract void checkInput(String input);
}