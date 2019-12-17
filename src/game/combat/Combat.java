package game.combat;

import game.creature.Creature;

public class Combat {
    public static int fight(Creature attacker, Creature attacked){
        attacked.lowerHp(  Math.max(attacker.getAttack()-attacked.getArmor(), 0 )  );
        return Math.max(attacker.getAttack()-attacked.getArmor(), 0 );  //ile attacker zadal obrazen
    }
    public static int attack(Creature attacker, Creature attacked){
        int potDamage = attacker.getAttack();
        int armor = attacked.getArmor();
        int actDamage = (int) Math.ceil(potDamage * (1 - (((double) armor )/ (armor + 50))));
        attacked.lowerHp(actDamage);
        return actDamage;
    }


}
