public abstract class Creatures {
    public int ID = 0;
    //room location sotred in an array in form [level, x,y] 
    //Level Range [0-4], X range [1-3], Y Range [1-3]
    protected int[] Location = new int[]{0,1,1};
    protected int HP = 1;
    protected int MoveCount = 1;

    public void showStatus(){}
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }
    //Template function for specific move directions
    public void move(){}

    //Manually set characters location
    //No influence from other rooms
    public void setLocation(int l, int x, int y){\
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


}