public class Brawlers extends Characters{
    Brawlers(int A){
        super.ID = A;
        name = "Brawler";
    }
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+2;
    }
}
