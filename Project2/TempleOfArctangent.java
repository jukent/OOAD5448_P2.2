import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.tools.DocumentationTool.Location;

public class TempleOfArctangent{
    public static void main(String[] args){



    }
}

class GameEngine{



}

abstract class Characters{
    public int ID = 0;
    protected int[] Location = new int[]{0,1,1};
    protected int HP = 3;
    protected int TreasureCount = 0;
    protected int MoveCount = 1;

    public void showStatus(){
        System.out.print("Character: ");
        System.out.print(ID);
        System.out.print("Location: ");
        System.out.print(Location);
        System.out.print("Health: ");
        System.out.print(HP);
        System.out.print("Treasure: ");
        System.out.print(TreasureCount);
    }
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }
    public int searchTreasure(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }
    public void move(){}


}

class Brawlers extends Characters{
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+2;
    }
}
class Sneakers extends Characters{
    public int fight(){
        if (DiceRolls.rollDice(2)== 1){
            return 0;
        }
        else{
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
    }
}
class Runners extends Characters{
    Runners(){
        super.MoveCount = 2;
    }
    }
class Thieves extends Characters{
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
    public int searchTreasure(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
}


abstract class Creatures {
    public int ID = 0;
    private String Location;
    private int HP = 1;
    private int MoveCount = 1;

    public void showStatus(){}
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }
    public void move(){}

}


class Orbiters extends Creatures{}
class Seekers extends Creatures{}
class Blinkers extends Creatures{}

interface DiceRolls{
    static int rollDice(int a){
        return ThreadLocalRandom.current().nextInt(1, a);
    }

}
