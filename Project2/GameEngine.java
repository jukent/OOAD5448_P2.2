import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collection;

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

    //Constructor to initialize board.
    GameEngine(){
        populateCharacters();
    }

    //Populate CharacterList and CreatureList with characters
    private void populateCharacters(){
        CharacterList.add(new Runners(ID));
        ID++;
        CharacterList.add(new Sneakers(ID));
        ID++;
        CharacterList.add(new Thieves(ID));
        ID++;
        CharacterList.add(new Brawlers(ID));
        ID++;
        CreatureList.add(new Seekers(ID));
        ID++;
        CreatureList.add(new Orbiters(ID));
        ID++;
        CreatureList.add(new Blinkers(ID));
        ID++;
        CreatureList.add(new Seekers(ID));
        ID++;
        CreatureList.add(new Orbiters(ID));
        ID++;
        CreatureList.add(new Blinkers(ID));
        ID++;
    }
    
    //Run the game by simulating processing each turn which includes
    //characters and creatures. Ends if the end condition is completed
    public  void runGame(){
        while(EndCondition){
            RoundCounter++;
            processTurn();
            
        }        
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
                System.out.print(B.getClass().getSimpleName()+": ");
                System.out.print(CreatureRoll);
                System.out.println(A.getClass().getSimpleName() +" Wins :D ");
            }
            else if (CharacterRoll < CreatureRoll){
                A.loseHealth(1);
                System.out.print("Fight: ");
                System.out.print(A.getClass().getSimpleName() + ": ");
                System.out.print(CharacterRoll);
                System.out.print(B.getClass().getSimpleName()+": ");
                System.out.print(CreatureRoll);
                System.out.println("Creature Wins :( ");
            }

        }
        else{System.out.println("Fight Skipped");}

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
                System.out.flush();
                showGameStatus();
                showMap();
                process1Character(I);//Process character
                checkWinCondition();//Updates win conditions
                TimeUnit.SECONDS.sleep(1);}
            else{break;}
        }
        for(Creatures I: CreatureList){
            if(EndCondition){//Stops processing creatures if end condition is met
                System.out.flush();
                showGameStatus();
                showMap();
                process1Creature(I);
                checkWinCondition();
                TimeUnit.SECONDS.sleep(1);}
            else{break;}
        }

        printDungeon();
        printCharacterStats();
    }
    
    //Function to get creatures from a particular room
    private ArrayList<Creatures> getCreaturesInRoom(Room room) {
        ArrayList<Creatures> creatures_in_room = new ArrayList<>();
        for (Creatures c:CreatureList) {
            Room creature_location = c.getLocation();
            if (creature_location == room) {
                creatures_in_room.add(c);
            } 
        }
        return creatures_in_room;  
    }


    // This one is public because Seekers are capable of knowing 
    public ArrayList<Characters> getCharactersInRoom(Room room) {
        ArrayList<Characters> characters_in_room = new ArrayList<>();
        for (Characters c:CharacterList) {
            Room character_location = c.getLocation();
            if (character_location == room) {
                characters_in_room.add(c);
            } 
        }
        return characters_in_room;  
    }

    //Processes the decision making for one character. 
    //if a creature is in the room, it automatically fights
    //if no other creature is in the room, the character randomly
    //decides to search for treasure or move(). Decision of direction
    //to profile is specified in move()
    private  void process1Character(Characters A){
        //Get Current room and gives a list of creatures in the room
        Room current_room = A.getLocation();
        ArrayList<Creatures> creatures_in_room = getCreaturesInRoom(current_room);
        
        //Process turn counts for characters. Mostly 1 but runners have 2
        for(int i = 0; i<A.MoveCount;i++){
            if(creatures_in_room.size() > 0) {
                simulateFight(A, creatures_in_room.get(0));
                continue;
            }
            else{
            switch(DiceRolls.rollDice(2)){
                case 1:
                    A.move();
                    break;
                case 2:
                    simulateTreasure(A);
                    break;}
            }
        }
    }
    
    //Processes the decision making for one creature. 
    //if a character is in the room, it automatically fights
    //if no other character is in the room, the moves according to
    //its profile specified in move()
    private void process1Creature(Creatures A){
        //Get Room informationa and characters in the room
        Room current_room = A.getLocation();
        ArrayList<Characters> characters_in_room = getCharactersInRoom(current_room);
        
        //Process decision making for creatures
        if(characters_in_room.size() > 0){
            simulateFight(characters_in_room.get(0), A);
        }
        else{A.move();}
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
        System.out.print(" Round");
        System.out.print(RoundCounter);
        System.out.print(" Characters: ");
        System.out.print(CharacterCount);
        System.out.print(" Creatures: ");
        System.out.print(CreatureCount);
        System.out.print(" Treasures Collected: ");
        System.out.println(TreasureCount);



    }


    private String getOccupancyString(Room room){
        ArrayList<Characters> characters_in_room = getCharactersInRoom(room);
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        ArrayList<Creatures> creatures_in_room = getCreaturesInRoom(room);
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
        row_rooms.add(dungeon.getRoom(level + "-" + row + "-0"));
        row_rooms.add(dungeon.getRoom(level + "-" + row + "-1"));
        row_rooms.add(dungeon.getRoom(level + "-" + row + "-2"));
        String row_string = new String();
        for (Room r:row_rooms) {
            row_string += getOccupancyString(r);
            row_string += "    ";
        }
        System.out.println(row_string);
    }


    private void printLevel (Integer level) {
        System.out.println("Level " + level);
        for (int r = 0; r < 2; ++r) {
            printRowString(level, r);
        }
    }


    private void printDungeon() {
        // Level 0 
        System.out.println("Level 0");
        Room starting_room = dungeon.getRoom("0-1-1");
        String occupancy_string = getOccupancyString(starting_room);
        System.out.println(occupancy_string);

        // Levels 1, 2, 3, 4
        for (int l = 1; l < 4; ++l) {
            printLevel(l);
        }
    }

    private void printCharacterStats() {
        for (Characters c:CharacterList) {
            String name = c.getName();
            Integer g = c.getTreasure();
            Integer hp = c.getHealth();

            String char_stats = new String(name + " - " + g + " Treasure(s) - " + hp + " Damage");
            System.out.println(char_stats);
        }
    }
}


