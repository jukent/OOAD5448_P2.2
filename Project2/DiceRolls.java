import java.util.concurrent.ThreadLocalRandom;

public interface DiceRolls{
    //Cohesion example. Object is only responsible
    //for various types of dice rolls. Accessable from anywhere
    static int rollDice(int a){
        return ThreadLocalRandom.current().nextInt(1, 1+a);
    }

}
