package game.engine;

public class VisibilityManager {

    UI ui;

    public VisibilityManager(UI userInterface) {
        this.ui = userInterface;
    }

    public  void showChoices(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);

        ui.inventoryPanel.setVisible(false);
        ui.characterPanel.setVisible(false);
        ui.goBackPanel.setVisible(false);
    }

    public  void showChoicesForLookingAtShit(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);

        ui.inventoryPanel.setVisible(false);
        ui.characterPanel.setVisible(false);
        ui.goBackPanel.setVisible(true);
    }

    public void showTitleScreen(){

        ui.startButtonPanel.setVisible(true);
        ui.titleNamePanel.setVisible(true);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);

        ui.inventoryPanel.setVisible(false);
        ui.characterPanel.setVisible(false);
        ui.goBackPanel.setVisible(false);
    }

    public void toBegin(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(true);
        ui.nameTextPanel.setVisible(true);

        ui.inventoryPanel.setVisible(false);
        ui.characterPanel.setVisible(false);
        ui.goBackPanel.setVisible(false);
    }

    public void showInventory(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);

        ui.inventoryPanel.setVisible(true);
        ui.characterPanel.setVisible(false);
        ui.goBackPanel.setVisible(true);
    }

    public void showCharacterSheet(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);

        ui.inventoryPanel.setVisible(false);
        ui.characterPanel.setVisible(true);
        ui.goBackPanel.setVisible(true);
    }

    public void updateCurrentHPLabel(int currentHP) {
        ui.hpNumberLabel.setText(Integer.toString(currentHP));
    }
    public void changeBackButtonToExit(){
        ui.goBackButton.setActionCommand("exit");
        ui.goBackButton.setText("EXIT");
    }
    public void changeExitButtonToGoBackFromLooking(){
        ui.goBackButton.setActionCommand("goBackFromLooking");
        ui.goBackButton.setText("BACK");
    }
}
