package adventure;

public final class GameWorld {

    private final GUI gui;

    private final Player player = new Player(15, "Knife");
    private boolean silverRing = false;
    private int goblinHP = 20;

    public GameWorld(GUI gui) {
        this.gui = gui;
    }

    private void updatePlayerHP(int hp, boolean isDamage) {
        if (isDamage) {
            player.receiveDamage(hp);
        } else {
            player.receiveHealing(hp);
        }
        gui.updateCurrentHPLabel(player.getHP());
    }

    private void updatePlayerWeapon(String weapon) {
        player.equipWeapon(weapon);
        gui.updateCurrentWeaponLabel(weapon);
    }

    public String getPlayerPosition() {
        return this.player.getPosition();
    }

    public boolean playerHasSilverRing() {
        if (this.silverRing) {
            gui.updateMainTextArea("Guard: Oh you killed that goblin?\nThank you so much. You are true hero!\nWelcome to our town!\n\n<THE END>");
            gui.hideChoiceButtons();
        }
        return silverRing;
    }

    public boolean playerIsAlive() {
        boolean isAlive = player.getHP() > 0;

        if (!isAlive) {
            gui.updateMainTextArea("You are dead!\n\n<GAME OVER>");
            gui.hideChoiceButtons();
        }
        return isAlive;
    }

    public boolean goblinIsAlive() {
        boolean isAlive = goblinHP > 0;

        if (!isAlive) {
            player.movePosition("playedKilledGoblin");

            gui.updateMainTextArea("You defeated the goblin!\nThe goblin dropped a ring!\n\n(You obtained a Silver Ring)");
            gui.updateChoiceButtons("Go East", "", "", "");

            silverRing = true;
        }
        return isAlive;
    }

    public void startGame() {
        gui.updateCurrentHPLabel(player.getHP());
        gui.updateCurrentWeaponLabel(player.getWeapon());

        townGate();
    }

    public void townGate() {
        player.movePosition("townGate");

        gui.updateMainTextArea("You are at the gate of the town.\nA guard is standing in front of you.\n\nWhat do you do?");
        gui.updateChoiceButtons("Talk to the guard", "Attack the guard", "Leave", "");
    }

    public void talkToGuard() {
        player.movePosition("playerTalkedToGuard");

        gui.updateMainTextArea("Guard: Hello stranger. I have never seen your face.\nI'm sorry but we cannot let a stranger enter our town.");
        gui.updateChoiceButtonsNoActions();
    }

    public void attackGuard() {
        player.movePosition("playerAttackedGuard");

        gui.updateMainTextArea("Guard: Hey don't be stupid!!\n\nThe guard fought back and hit you hard.\n(You receive 3 damage)");
        gui.updateChoiceButtonsNoActions();

        updatePlayerHP(3, true);
    }

    public void crossRoad() {
        player.movePosition("crossRoad");

        gui.updateMainTextArea("You are at a cross road.\nIf you go south, you will go back to the town.");
        gui.updateChoiceButtons("Go North", "Go East", "Go South", "Go West");
    }

    public void north() {
        player.movePosition("north");

        gui.updateMainTextArea("There is a river.\nYou drink the water and rest and the riverside.\n(Your HP is recovered by 2)");
        gui.updateChoiceButtons("Go South", "", "", "");

        updatePlayerHP(2, false);
    }

    public void east() {
        player.movePosition("east");

        gui.updateMainTextArea("You walked into a forest and found a long sword!\n\n(You obtained a Long Sword)");
        gui.updateChoiceButtons("Go West", "", "", "");

        updatePlayerWeapon("Long Sword");
    }

    public void west() {
        player.movePosition("west");

        if (goblinHP > 0) {
            gui.updateMainTextArea("You encounter a goblin!");
            gui.updateChoiceButtons("Fight", "Run", "", "");
        } else {
            gui.updateMainTextArea("There is a dead goblin on the ground.");
            gui.updateChoiceButtonsNoActions();
        }
    }

    public void fightGoblin() {
        player.movePosition("goblinBattle");

        gui.updateMainTextArea("Goblin's HP: " + goblinHP + "\n\nWhat do you do?");
        gui.updateChoiceButtons("Fight", "Run", "", "");
    }

    public void attackGoblin() {
        player.movePosition("playerAttackedGoblin");

        int playerDamage = 0;
        if (player.getWeapon().equals("Knife")) {
            playerDamage = new java.util.Random().nextInt(3);
        } else if (player.getWeapon().equals("Long Sword")) {
            playerDamage = new java.util.Random().nextInt(10);
        }

        gui.updateMainTextArea("You attacked the goblin and gave " + playerDamage + " damage!");
        gui.updateChoiceButtonsNoActions();

        goblinHP -= playerDamage;
    }

    public void goblinAttacks() {
        player.movePosition("goblinAttackedPlayer");

        int goblinDamage = new java.util.Random().nextInt(6);

        gui.updateMainTextArea("The goblin attacked you and gave " + goblinDamage + " damage!");
        gui.updateChoiceButtonsNoActions();

        updatePlayerHP(goblinDamage, true);
    }

}
