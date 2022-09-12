public abstract class Creatures {
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