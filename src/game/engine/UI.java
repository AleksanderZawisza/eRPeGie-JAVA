package game.engine;

import javax.swing.*;
import java.awt.*;

public class UI {

    public JFrame window;
    public JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, inputPanel, nameTextPanel,
                    inventoryPanel, goBackPanel, characterPanel;
    public JLabel titleNameLabel, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel, nameTextLabel;
    Font titleFont = new Font("Monospaced", Font.PLAIN, 90);
    Font normalFont = new Font("Monospaced", Font.PLAIN, 19);
    Font smallFont = new Font("Monospaced", Font.PLAIN, 14);
    public JButton startButton, choice1, choice2, choice3, choice4, enterB, inventoryButton, characterButton, goBackButton;
    public JTextArea mainTextArea, characterStatsArea, characterEqArea;
    public JTextField jtf;
    public JButton[] inventoryChoiceButtons;

    public void createUI(ChoiceHandler cHandler) {

        // WINDOW
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setResizable(false);


        // TITLE SCREEN
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("RPG GAME");
        titleNameLabel.setFont(titleFont);
        titleNameLabel.setForeground(Color.white);
        titleNamePanel.add(titleNameLabel);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(cHandler);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);

        // GAME SCREEN
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);

        mainTextArea = new JTextArea("main text");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);

        window.add(mainTextPanel);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(100, 380, 600, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1,7,7));

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.setOpaque(false);
        choice1.addActionListener(cHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.setOpaque(false);
        choice2.addActionListener(cHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.setOpaque(false);
        choice3.addActionListener(cHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.setOpaque(false);
        choice4.addActionListener(cHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        choice1.setBorderPainted(true);
        choice2.setBorderPainted(true);
        choice3.setBorderPainted(true);
        choice4.setBorderPainted(true);

        window.add(choiceButtonPanel);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1,4,15,15));

        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpNumberLabel = new JLabel();
        hpNumberLabel.setFont(normalFont);
        hpNumberLabel.setForeground(Color.white);
        playerPanel.add(hpNumberLabel);

        /**
        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponNameLabel = new JLabel();
        weaponNameLabel.setFont(normalFont);
        weaponNameLabel.setForeground(Color.white);
        playerPanel.add(weaponNameLabel);
         **/

        inventoryButton = new JButton("Inventory");
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.setFont(smallFont);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setOpaque(false);
        inventoryButton.addActionListener(cHandler);
        inventoryButton.setActionCommand("inventory");


        characterButton = new JButton("Journal");
        characterButton.setBackground(Color.black);
        characterButton.setForeground(Color.white);
        characterButton.setFont(smallFont);
        characterButton.setFocusPainted(false);
        characterButton.setOpaque(false);
        characterButton.addActionListener(cHandler);
        characterButton.setActionCommand("characterSheet");

        playerPanel.add(characterButton);
        playerPanel.add(inventoryButton);

        window.add(playerPanel);

        // INPUT PANEL
        nameTextPanel = new JPanel();
        nameTextPanel.setBounds(150,200,500,100);
        nameTextPanel.setBackground(Color.black);
        nameTextLabel = new JLabel("Hi there. What's your NAME?");
        nameTextLabel.setForeground(Color.white);
        nameTextLabel.setFont(normalFont);
        nameTextPanel.add(nameTextLabel);

        inputPanel = new JPanel();
        inputPanel.setBounds(150,450,500,50);
        inputPanel.setBackground(Color.black);
        inputPanel.setLayout(new GridLayout(1,2));

        jtf = new JTextField();
        jtf.setFont(normalFont);
        jtf.setBackground(Color.black);
        jtf.setForeground(Color.white);
        jtf.setHorizontalAlignment(JTextField.CENTER);
        inputPanel.add(jtf);

        enterB = new JButton("ENTER");
        enterB.setActionCommand("input");
        enterB.setForeground(Color.white);
        enterB.setBackground(Color.black);
        enterB.addActionListener(cHandler);
        inputPanel.add(enterB);

        window.add(nameTextPanel);
        window.add(inputPanel);

        window.setVisible(true);

        // INVENTORY SCREEN
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(100, 90, 600, 420);
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setLayout(new GridLayout(6,2, 15,15));

        inventoryChoiceButtons = new JButton[12];
        for (int i = 0; i < inventoryChoiceButtons.length; i++) {
            inventoryChoiceButtons[i] = new JButton("Inventory Inventory Inventory " + i);
            inventoryChoiceButtons[i].setOpaque(true);
            inventoryChoiceButtons[i].setFocusPainted(false);
            inventoryChoiceButtons[i].setBackground(Color.black);
            inventoryChoiceButtons[i].setForeground(Color.white);
            inventoryChoiceButtons[i].setFont(smallFont);
            inventoryChoiceButtons[i].addActionListener(cHandler);
            inventoryChoiceButtons[i].setActionCommand("i" + i);

            inventoryPanel.add(inventoryChoiceButtons[i]);
        }

        goBackPanel = new JPanel();
        goBackPanel.setBounds(320, 25, 160, 40);
        goBackPanel.setBackground(Color.black);
        goBackPanel.setLayout(new GridLayout(1,1));

        goBackButton = new JButton("EXIT");
        goBackButton.setBackground(Color.black);
        goBackButton.setForeground(Color.white);
        goBackButton.setFocusPainted(false);
        goBackButton.setOpaque(false);
        goBackButton.addActionListener(cHandler);
        goBackButton.setActionCommand("exit");

        goBackPanel.add(goBackButton);

        window.add(goBackPanel);
        window.add(inventoryPanel);
        window.setVisible(true);

        // CHARACTER SCREEN
        characterPanel = new JPanel();
        characterPanel.setBounds(80, 90, 640, 510);
        characterPanel.setBackground(Color.black);
        characterPanel.setLayout(new GridLayout(1,2));

        characterStatsArea = new JTextArea("main text");
        characterStatsArea.setBackground(Color.black);
        characterStatsArea.setForeground(Color.white);
        characterStatsArea.setFont(normalFont);
        characterStatsArea.setLineWrap(true);
        characterStatsArea.setWrapStyleWord(true);
        characterStatsArea.setEditable(false);

        characterEqArea = new JTextArea("main text");
        characterEqArea.setBackground(Color.black);
        characterEqArea.setForeground(Color.white);
        characterEqArea.setFont(normalFont);
        characterEqArea.setLineWrap(true);
        characterEqArea.setWrapStyleWord(true);
        characterEqArea.setEditable(false);


        characterPanel.add(characterStatsArea);
        characterPanel.add(characterEqArea);
        window.add(characterPanel);

    }

}
