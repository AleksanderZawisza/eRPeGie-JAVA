package console.swinggamingconsole.session.example;

import console.swinggamingconsole.Game;
import console.swinggamingconsole.session.Session;
import console.swinggamingconsole.session.StagedSession;

/**
 * The example staged session. This session is setup to interact with the player
 * on multiple levels, such as with dialog or in battles and such.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public class ExampleStagedSession extends StagedSession {

	public ExampleStagedSession(Session parent) {
		super("Example Staged Session", parent);
	}

	@Override
	public void execute() {
		switch (getStage()) {

		case 0:
			Game.getGameHandler().insertLineBreak();
			Game.getGameHandler().addConsoleText("This is a staged session.");
			Game.getGameHandler().addConsoleText("Insert any text to go to the next stage.");
			break;

		case 1:
			Game.getGameHandler().addConsoleText("This is still the staged session.");
			Game.getGameHandler().addConsoleText("Insert any text again to go back to session.");
			break;

		}
	}

	@Override
	public void checkInput(String input) {
		switch (getStage()) {

		case 0:
			nextStage();
			break;

		case 1:
			Game.getGameHandler().newSession(Game.getGameHandler().getExampleSession());
			break;

		}
	}

}