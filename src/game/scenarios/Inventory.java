package game.scenarios;

import game.state.GameWorld;

public class Inventory {

    GameWorld gameworld;
    String lastPosition;

    public Inventory(GameWorld gameworld) {
        this.gameworld = gameworld;
    }

    public void manageInventory(){

        gameworld.vm.showInventory();

    }

    public void characterSheet(){

        gameworld.vm.showCharacterSheet();
        gameworld.ui.characterStatsArea.setText(
                "Current stats:" +
                "\n\nAttack: " + gameworld.player.getAttack() +
                "\nMax HP: " + gameworld.player.getMaxhp() +
                "\nArmor: " + gameworld.player.getArmor() +
                "\nTHREAT RATING: " + (int) gameworld.player.getExp() +
                "\nGold coins: " + gameworld.player.getMoney() +
                "\nKills today: " + "?" + //TODO
                "\n\nCurrent goals:" +
                "\nKill things." +
                "\nDon't get killed." +
                "\nHave fun! :)" //TODO
        );
        gameworld.ui.characterEqArea.setText(
                "Equipped items:" +
                "\n\nWeapon: " + gameworld.player.weapon.getName().toUpperCase() + " (+" +  gameworld.player.weapon.getDamage() + ")" +
                "\n\nHead: " + gameworld.player.head.getName().toUpperCase() + " (+" +  gameworld.player.head.getDefence() + ")" +
                "\n\nTorso: " + gameworld.player.torso.getName().toUpperCase() + " (+" +  gameworld.player.torso.getDefence() + ")" +
                "\n\nArms: " + gameworld.player.arms.getName().toUpperCase() + " (+" +  gameworld.player.arms.getDefence() + ")" +
                "\n\nLegs: " + gameworld.player.legs.getName().toUpperCase() + " (+" +  gameworld.player.legs.getDefence() + ")"

        );

    }

    public String getLastPosition() { return lastPosition;  }
    public void setLastPosition(String lastPosition) { this.lastPosition = lastPosition;  }

}
