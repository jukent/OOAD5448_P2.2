public abstract class Characters {
    public int ID = 0;
    //room location sotred in an array in form [level, x,y] 
    //Level Range [0-4], X range [1-3], Y Range [1-3]
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
    //Template function for specific move directions
    public void move(){}

    //Manually set characters location
    //No influence from other rooms
    public void setLocation(int l, int x, int y){ 
        Location[0] = l;
        Location[1] = x;
        Location[2] = y;
    }
    public void loseHealth(int n){
        HP = HP -n;
    }
    public int getHealth(){
        return HP;
    }
    public int getTreasure(){
        return TreasureCount;
    }

}



