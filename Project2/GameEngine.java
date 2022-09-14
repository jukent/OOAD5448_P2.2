import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {

    //Array list that contains all characters and Creatures
    public ArrayList<Characters> CharacterList= new ArrayList<Characters>();
    ArrayList<Creatures> CreatureList= new ArrayList<Creatures>();

    protected Dungeon dungeon = new Dungeon();

    //Game variables that track win condition
    private int TreasureCount = 0;
    private int CreatureCount = 1;
    private int CharacterCount = 0;
    private int RoundCounter = 0;
    private int ID = 0;
    private boolean EndCondition = true;
    private Scanner A = new java.util.Scanner(System.in);

    //Constructor to initialize board.
    public GameEngine(){
        populateCharacters();
        System.out.println("Starting Game!");
        System.out.println("Press Enter To Continue...");
        A.nextLine();
        
    }

    //Populate CharacterList and CreatureList with characters
    private void populateCharacters(){
        CharacterList.add(new Runners(ID,dungeon));
        ID++;
        CharacterList.add(new Sneakers(ID,dungeon));
        ID++;
        CharacterList.add(new Thieves(ID,dungeon));
        ID++;
        CharacterList.add(new Brawlers(ID,dungeon));
        ID++;

        CreatureList.add(new Seekers(ID,dungeon,CharacterList));
        ID++;
        //CreatureList.add(new Orbiters(ID,dungeon));
        //ID++;
        //CreatureList.add(new Blinkers(ID,dungeon));
        //ID++;
        CreatureList.add(new Seekers(ID,dungeon, CharacterList));
        ID++;
        //CreatureList.add(new Orbiters(ID,dungeon));
        //ID++;
        //CreatureList.add(new Blinkers(ID,dungeon));
        //ID++;
    }
    
    //Run the game by simulating processing each turn which includes
    //characters and creatures. Ends if the end condition is completed
    public  void runGame(){
        checkWinCondition();
        while(EndCondition){
            RoundCounter++;
            processTurn();
        }        
        A.close();
    }
    
    //Input a character and creature, and deducts health if 
    //a dice roll is larger than the other. If a character rolls
    // a -1, fight is skipped
    private static void simulateFight(Characters A, Creatures B){
        int CharacterRoll = A.fight();
        int CreatureRoll = B.fight();

        if(CharacterRoll > 0){
            if(CharacterRoll > CreatureRoll){
                B.loseHealth(1);
                System.out.print("Fight: ");
                System.out.print(A.getClass().getSimpleName() + ": ");
                System.out.print(CharacterRoll);
                System.out.print(" "+ B.getClass().getSimpleName()+": ");
                System.out.print(CreatureRoll);
                System.out.println(" "+ A.getClass().getSimpleName() +" Wins :D ");
            }
            else if (CharacterRoll < CreatureRoll){
                A.loseHealth(1);
                System.out.print("Fight: ");
                System.out.print(A.getClass().getSimpleName() + ": ");
                System.out.print(CharacterRoll);
                System.out.print(" "+ B.getClass().getSimpleName()+": ");
                System.out.print(CreatureRoll);
                System.out.println(" Creature Wins :( ");
            }

        }
        else{System.out.println(" Fight Skipped");}

    }
    
    //Performs the character action of searching for treasure.
    //Adds to the characters treasure count
    private static void simulateTreasure(Characters A){
        if(A.searchTreasure()>=10){
            A.gainTreasure();
            System.out.print("Treasure Hunt: ");
            System.out.println("Success!");
        }
        else{System.out.print("Treasure Hunt: ");
        System.out.println("fail :(");}
    }
    
    //Processes the turns for each character and for each creature
    private void processTurn(){
        for(Characters I: CharacterList){
            if(EndCondition){//Stops processing characters if end condition is met
                System.out.print("\033[H\033[2J");
                System.out.flush();
                showGameStatus();
                printDungeon();
                printCharacterStats();
                process1Character(I);//Process character
                checkWinCondition();//Updates win conditions}
                System.out.println("Press Enter To Continue...");
                A.nextLine();
            }
            else{break;}
        }
        for(Creatures I: CreatureList){
            if(EndCondition){//Stops processing creatures if end condition is met
                System.out.print("\033[H\033[2J");
                System.out.flush();
                showGameStatus();
                printDungeon();
                printCharacterStats();
                process1Creature(I);
                checkWinCondition();
                System.out.println("Press Enter To Continue...");
                A.nextLine();
            }
            else{break;}
        }
    }
    
    private void setCharactersInRoom(Room room) {
        ArrayList<Characters> characters_in_room = new ArrayList<>();
        for (Characters c:CharacterList) {
            Room character_location = c.getLocation();
            if (character_location == room) {
                characters_in_room.add(c);
            } 
        }
        room.setCharactersInRoom(characters_in_room);  
    }

    //Function to get creatures from a particular room
    private void setCreaturesInRoom(Room room) {
        ArrayList<Creatures> creatures_in_room = new ArrayList<>();
        for (Creatures c:CreatureList) {
            Room creature_location = c.getLocation();
            if (creature_location == room) {
                creatures_in_room.add(c);
            } 
        }
        room.setCreaturesInRoom(creatures_in_room);  
    }

    //Processes the decision making for one character. 
    //if a creature is in the room, it automatically fights
    //if no other creature is in the room, the character randomly
    //decides to search for treasure or move(). Decision of direction
    //to profile is specified in move()
    private  void process1Character(Characters A){
 
        //Process turn counts for characters. Mostly 1 but runners have 2
        for(int i = 0; i<A.MoveCount;i++){
            A.move();
            Room new_room = A.getLocation();
            setCharactersInRoom(new_room); // Tell Room that character is there

            // Look for creatures
            setCreaturesInRoom(new_room);
            ArrayList<Creatures> creatures_in_room = new_room.getCreaturesInRoom();
            if(creatures_in_room.size() > 0) {
                for (Creatures c:creatures_in_room) {
                    simulateFight(A, c);
                }
                continue;
            } else{
                simulateTreasure(A);
                break;
            }
        }
    }
    
    //Processes the decision making for one creature. 
    //if a character is in the room, it automatically fights
    //if no other character is in the room, the moves according to
    //its profile specified in move()
    private void process1Creature(Creatures A){


        System.out.println("Creature turn");

        //Get Room information and characters in the room
        Room current_room = A.getLocation();
        //setCharactersInRoom(current_room);
        ArrayList<Characters> characters_in_room = current_room.getCharactersInRoom();
        
        //Process decision making for creatures
        if(characters_in_room.size() > 0){
            // If there is a character, don't move, fight
            for (Characters c:characters_in_room) {
                simulateFight(c, A);
            }
        }
        else{
            // If no character, move
            System.out.println("Creature Move Behavior");
            A.move();
            Room new_room = A.getLocation();
            setCreaturesInRoom(new_room); // Tell Room that Creature is there
            
            // Check for characters in new room
            //setCharactersInRoom(new_room);
            ArrayList<Characters> characters_in_new_room = current_room.getCharactersInRoom();

            // If characters in new room, fight
            for (Characters c:characters_in_new_room) {
                simulateFight(c, A);
            }
        }
    }
    
    //Checks various end game conditions and modifies EndCondition accordingly
    private void checkWinCondition(){
        int TC = 0;

        //Removes creatuers from board if it runs out of health
        for(Creatures I: CreatureList){
            if(I.getHealth() <= 0){
                CreatureList.remove(I);
            }
        }
        //Removes character from board if it runs out of health
        //Only counts treasures if player is alive
        for(Characters I: CharacterList){
            if(I.getHealth() <= 0){
                CharacterList.remove(I);
            }
            else{TC += I.getTreasure();}
        }

        //Update game tracking variables
        TreasureCount = TC;
        CreatureCount = CreatureList.size();
        CharacterCount = CharacterList.size();

        //Change End Condition depending on the outcome
        if(TreasureCount >= 10){//10 Treasures
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("Adventurers collected 10 treasures!");
        }
        else if(CreatureCount <= 0){//All creatures eliminated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("Adventurers defeated all creatures!");
        }
        else if(CharacterCount <= 0){//All adventureers defeated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("All adventurers defeated!");
        }
        else{EndCondition = true;}

    }

    //Shows an overview of game information such as win conditions and entitys
    private void showGameStatus(){
        System.out.print("Game Status: ");
        System.out.print(" Round: ");
        System.out.print(RoundCounter);
        System.out.print(" Characters: ");
        System.out.print(CharacterCount);
        System.out.print(" Creatures: ");
        System.out.print(CreatureCount);
        System.out.print(" Treasures Collected: ");
        System.out.println(TreasureCount);

    }

    private String getOccupancyString(Room room){
        setCharactersInRoom(room);
        setCreaturesInRoom(room);

        ArrayList<Characters> characters_in_room = room.getCharactersInRoom();
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        ArrayList<Creatures> creatures_in_room = room.getCreaturesInRoom();
        String creature_string = new String();
        for (Creatures c:creatures_in_room) {
            creature_string += c.getName();
            creature_string += " ";
        }
        String occupancy_string = new String(room.getName() + ": " + char_string + " : " + creature_string);
        return occupancy_string;
    }

    private void printRowString (Integer level, Integer row) {
        ArrayList<Room> row_rooms = new ArrayList<Room>();
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));
        String row_string = new String();
        for (Room r:row_rooms) {
            row_string += getOccupancyString(r);
            row_string += "    ";
        }
        System.out.println(row_string);
    }

    private void printLevel (Integer level) {
        System.out.println("Level " + level);
        for (int r = 0; r <= 2; ++r) {
            printRowString(level, r);
        }
    }

    private void printDungeon() {
        // Level 0 
        System.out.println("Level 0");
        Room starting_room = dungeon.getRoom("(0-1-1)");
        //System.out.println(starting_room);
        String occupancy_string = getOccupancyString(starting_room);
        System.out.println(occupancy_string);

        // Levels 1, 2, 3, 4
        for (int l = 1; l <= 4; ++l) {
            printLevel(l);
        }
    }

    private void printCharacterStats() {
        for (Characters c:CharacterList) {
            String name = c.getName();
            Integer g = c.getTreasure();
            Integer hp = 3-c.getHealth();

            String char_stats = new String(name + " - " + g + " Treasure(s) - " + hp + " Damage");
            System.out.println(char_stats);
        }
    }
}


