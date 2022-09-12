import java.util.concurrent.ThreadLocalRandom;

public interface DiceRolls{
    static int rollDice(int a){
        return ThreadLocalRandom.current().nextInt(1, a);
    }

}
