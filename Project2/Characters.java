public abstract class Characters {
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



