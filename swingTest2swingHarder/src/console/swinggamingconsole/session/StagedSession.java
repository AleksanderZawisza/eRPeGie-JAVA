package console.swinggamingconsole.session;

/**
 * The following class is a type of session, but this one has multiple stages
 * where it will accept different input's at different times, based on the
 * stage.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public abstract class StagedSession extends Session {

	/**
	 * The number value for the stage we are on.
	 */
	private int stage = 0;

	/**
	 * The constructor of this class.
	 * 
	 * @param sessionName
	 *            The name of the session.
	 * @param parent
	 *            This session's parent.
	 */
	public StagedSession(String sessionName, Session parent) {
		super(sessionName, parent);
	}

	/**
	 * Here we override start(), to run the first stage of execute().
	 */
	@Override
	public void start() {
		execute();
	}

	/**
	 * Returns the stage number value.
	 * 
	 * @return The stage's number value.
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * This advanced the stage variable to the next integer value.
	 */
	public void nextStage() {
		this.stage++;
		execute();
	}

	/**
	 * Sets the stage to a different one.
	 * 
	 * @param newStage
	 *            The new stage.
	 */
	public void setStage(int newStage) {
		this.stage = newStage;
	}

	/**
	 * This method is the replacement for start(), as it will be executed on
	 * command, rather than only when the session in changed.
	 */
	public abstract void execute();

}