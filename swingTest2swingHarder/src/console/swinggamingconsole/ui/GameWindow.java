package console.swinggamingconsole.ui;

import console.swinggamingconsole.Game;

import javax.swing.*;
import java.awt.*;

/**
 * The base class for the game window, which will contain the console and the
 * text entry box.
 * 
 * @author Tyler Buchanan <t.buchananx97@gmail.com>
 */
public final class GameWindow extends JFrame {

	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -3892065636553883051L;

	/**
	 * Default width of JFrame.
	 */
	private static int WIDTH = 800;

	/**
	 * Default height of JFrame.
	 */
	private static int HEIGHT = 600;

	/**
	 * Create an empty <@link JTextArea> object, as this will need to be access
	 * from everywhere.
	 */
	private static JTextArea console;

	/**
	 * Create an empty <@link JTextField> object, as this will need to be access
	 * from everywhere.
	 */
	private static JTextField input;

	/**
	 * The constructor for this class.
	 */
	private GameWindow() {
		super(Game.GAME_NAME);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.createComponents(getContentPane());
		this.setVisible(true);
		setForeground(Color.BLACK);
		setBackground(Color.BLACK);
	}

	/**
	 * A method to add all the components to the content pane.
	 * 
	 * @param container
	 *            The content pane container.
	 */
	private void createComponents(Container container) {
		console = new JTextArea("Welcome to " + Game.GAME_NAME);
		console.setEditable(false);
		console.setColumns(140);

		JScrollPane scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		input = new JTextField();
		input.addActionListener(ae -> {
			if (!ae.getActionCommand().trim().equals("") && !ae.getActionCommand().equals(null)) {
				Game.getGameHandler().addConsoleText(ae.getActionCommand());
				Game.getGameHandler().getCurrentSession().checkInput(ae.getActionCommand());
				input.setText(null);
			}
		});

		container.add(scrollPane, BorderLayout.CENTER);
		container.add(input, BorderLayout.PAGE_END);
	}

	/**
	 * Returns the <@link JTextArea> for the console.
	 * 
	 * @return The console text area.
	 */
	public JTextArea getConsole() {
		return console;
	}

	/**
	 * Returns the <@link JTextField> for the input.
	 * 
	 * @return The input text area.
	 */
	public JTextField getInput() {
		return input;
	}

	/**
	 * This method constructs a new instance of the game window.
	 * 
	 * @return The new game window instance.
	 */
	public static GameWindow createGameWindow() {
		return new GameWindow();
	}
}
