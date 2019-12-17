package game.combat;

import game.creature.Creature;
import game.creature.Player;

public class Combat {
    public static int fight(Creature attacker, Creature attacked){
        attacked.setHp(attacked.getHp()-
                Math.max(attacker.getAttack()-attacked.getArmor(), 0 )  );
        return Math.max(attacker.getAttack()-attacked.getArmor(), 0 );  //ile attacker zadal obrazen
    }

}
