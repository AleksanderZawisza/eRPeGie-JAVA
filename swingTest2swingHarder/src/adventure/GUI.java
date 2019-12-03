package adventure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public final class GUI {

    private final ActionListener choiceHandler;
    private final ActionListener titleScreenHandler;

    private Container container;
    private JPanel titleNamePanel, startButtonPanel;
    private JLabel currentHPLabel, currentWeaponLabel;
    private JTextArea mainTextArea;
    private JButton[] choiceButtons;

    private final Font normalFont = new Font("Monospaced", Font.PLAIN, 16);

    public GUI(ActionListener choiceHandler, ActionListener titleScreenHandler) {
        this.choiceHandler = choiceHandler;
        this.titleScreenHandler = titleScreenHandler;
    }

    public void initializeStartScreen() {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        // Title Name Panel
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.BLACK);

        JLabel titleNameLabel = new JLabel("ADVENTURE");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 26));

        titleNamePanel.add(titleNameLabel);

        // Start Button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);

        JButton startButton = new JButton("START");
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);

        startButtonPanel.add(startButton);

        // Add to Container
        container = window.getContentPane();
        container.add(titleNamePanel);
        container.add(startButtonPanel);

        window.setVisible(true);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        // Player Panel
        JPanel playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1, 4));

        JLabel hpLabel = new JLabel("HP:");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(normalFont);

        currentHPLabel = new JLabel();
        currentHPLabel.setForeground(Color.WHITE);
        currentHPLabel.setFont(normalFont);

        JLabel weaponLabel = new JLabel("Weapon:");
        weaponLabel.setForeground(Color.WHITE);
        weaponLabel.setFont(normalFont);

        currentWeaponLabel = new JLabel();
        currentWeaponLabel.setForeground(Color.WHITE);
        currentWeaponLabel.setFont(normalFont);

        playerPanel.add(hpLabel);
        playerPanel.add(currentHPLabel);
        playerPanel.add(weaponLabel);
        playerPanel.add(currentWeaponLabel);

        // Main Text Panel
        JPanel mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.BLACK);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);

        // Choice Button Panel
        JPanel choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choiceButtons = new JButton[4];
        for (int i = 0; i < choiceButtons.length; i++) {
            choiceButtons[i] = new JButton("Choice " + (i + 1));
            choiceButtons[i].setOpaque(true);
            choiceButtons[i].setBorderPainted(false);
            choiceButtons[i].setFocusPainted(false);
            choiceButtons[i].setBackground(Color.BLACK);
            choiceButtons[i].setForeground(Color.WHITE);
            choiceButtons[i].setFont(normalFont);
            choiceButtons[i].addActionListener(choiceHandler);
            choiceButtons[i].setActionCommand("c" + (i + 1));

            choiceButtonPanel.add(choiceButtons[i]);
        }

        // Add to Container
        container.add(playerPanel);
        container.add(mainTextPanel);
        container.add(choiceButtonPanel);
    }

    public void updateCurrentHPLabel(int currentHP) {
        currentHPLabel.setText(Integer.toString(currentHP));
    }

    public void updateCurrentWeaponLabel(String weapon) {
        currentWeaponLabel.setText(weapon);
    }

    public void updateMainTextArea(String mainText) {
        mainTextArea.setText(mainText);
    }

    public void updateChoiceButtons(String choice1, String choice2, String choice3, String choice4) {
        choiceButtons[0].setText(choice1);
        choiceButtons[1].setText(choice2);
        choiceButtons[2].setText(choice3);
        choiceButtons[3].setText(choice4);
    }

    public void updateChoiceButtonsNoActions() {
        choiceButtons[0].setText(">");
        choiceButtons[1].setText("");
        choiceButtons[2].setText("");
        choiceButtons[3].setText("");
    }

    public void hideChoiceButtons() {
        for (JButton choiceButton : choiceButtons) {
            choiceButton.setText("");
            choiceButton.setVisible(false);
        }
    }

}
