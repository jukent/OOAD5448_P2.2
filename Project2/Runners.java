public class Runners extends Characters{//Example of inheritance
    Runners(int A,Dungeon map){
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        super.ID = A;
        super.MoveCount = 2;
        name = "Runner";
    }
    }