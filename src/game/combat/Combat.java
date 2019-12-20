package game.combat;

import game.creature.Creature;

import java.util.Random;

public class Combat {

    public static int fight(Creature attacker, Creature attacked){
        attacked.lowerHp(  Math.max(attacker.getAttack()-attacked.getArmor(), 0 )  );
        return Math.max(attacker.getAttack()-attacked.getArmor(), 0 );  //ile attacker zadal obrazen
    }

    public static int attack(Creature attacker, Creature attacked){

        // simulate dice roll

        Random random = new Random();
        int mod = random.nextInt(6) + 1;
        int potDamage = attacker.getAttack(); // + bonusy z eq
        if (mod == 1){
            potDamage = 0;
            // missed
        }
        else if (mod == 6){
            potDamage = potDamage * 2;
            // critical hit
        }
        int armor = attacked.getArmor();
        int actDamage = (int) Math.ceil(potDamage * (1 - (((double) armor ) / (armor + 50))));

        if (attacked.getHp() < actDamage){
            int actDamage1 = attacked.getHp();
            attacked.lowerHp(actDamage1);
            return actDamage1;
        }
        attacked.lowerHp(actDamage);
        return actDamage;
    }

}
