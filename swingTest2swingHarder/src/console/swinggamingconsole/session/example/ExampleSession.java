package console.swinggamingconsole.session.example;

import console.swinggamingconsole.Game;
import console.swinggamingconsole.session.Session;

/**
 * This is an example session for all who may attempt to use this. The session
 * instance in simple, we display some text, and ask the player to provide
 * input, based on the situation. The standard session instance is used for a
 * single input, use staged input to have a longer session.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public class ExampleSession extends Session {

	/**
	 * The constructor for this class. The first super class definition is the
	 * name of the session, the second is the parent. In this case, null.
	 */
	public ExampleSession() {
		super("Example Session", null);
	}

	@Override
	public void start() {
		Game.getGameHandler().insertLineBreak();
		Game.getGameHandler().addConsoleText("This is an example session.");
		Game.getGameHandler().addConsoleText("Enter \"staged session\" to go to the staged session example.");
		Game.getGameHandler().addConsoleText("Enter anything else to repeat this session.");
	}

	@Override
	public void checkInput(String input) {
		if (input.equalsIgnoreCase("staged session")) 
			Game.getGameHandler().newSession(Game.getGameHandler().getExampleStagedSession());
		else
			Game.getGameHandler().newSession(Game.getGameHandler().getExampleSession());
	}
}