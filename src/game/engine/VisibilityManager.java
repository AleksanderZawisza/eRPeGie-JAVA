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
    }

    public void showTitleScreen(){

        ui.startButtonPanel.setVisible(true);
        ui.titleNamePanel.setVisible(true);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(false);
        ui.nameTextPanel.setVisible(false);
    }

    public void toBegin(){

        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.inputPanel.setVisible(true);
        ui.nameTextPanel.setVisible(true);

    }
}
