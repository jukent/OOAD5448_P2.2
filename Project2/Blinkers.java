public class Blinkers extends Creatures{
    Blinkers(int A){
        super.ID = A;

    //Blinkers start anywhere on the 4th level
    Dungeon dungeon = new Dungeon();
    protected Room Location = dungeon.getRoom("0-1-1");
    }

    //Template function for specific move behaviors. Should include set location
    public void move(){}
}
