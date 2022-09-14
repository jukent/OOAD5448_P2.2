import java.util.ArrayList;
import java.util.Collection;

public class GameEngine {

    //Array list that contains all characters and Creatures
    public ArrayList<Characters> CharacterList= new ArrayList<Characters>();
    ArrayList<Creatures> CreatureList= new ArrayList<Creatures>();

    protected Dungeon dungeon = new Dungeon();

    //Game variables that track win condition
    private int TreasureCount = 0;
    private int CreatureCount = 1;
    private int ID = 0;
    private boolean EndCondition = true;

    //Constructor to initialize board.
    GameEngine(){
        populateCharacters();
    }

    //Populate CharacterList and CreatureList with characters
    public void populateCharacters(){
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
            processTurn();
        }        
    }
    
    

    //Input a character and creature, and deducts health if 
    //a dice roll is larger than the other. If a character rolls
    // a -1, fight is skipped
    public static void simulateFight(Characters A, Creatures B){
        int CharacterRoll = A.fight();
        int CreatureRoll = B.fight();

        if(CharacterRoll > 0){
            if(CharacterRoll > CreatureRoll){
                B.loseHealth(1);
            }
            else if (CharacterRoll < CreatureRoll){
                A.loseHealth(1);
            }

        }

    }
    
    //Performs the character action of searching for treasure.
    //Adds to the characters treasure count
    public static void simulateTreasure(Characters A){
        if(A.searchTreasure()>=10){
            A.gainTreasure();
        }
    }
    
    //Processes the turns for each character and for each creature
    public  void processTurn(){
        for(Characters I: CharacterList){
            if(EndCondition){//Stops processing characters if end condition is met
            process1Character(I);//Process character
            checkWinCondition();}//Updates win conditions
            else{break;}
        }
        for(Creatures I: CreatureList){
            if(EndCondition){//Stops processing creatures if end condition is met
            process1Creature(I);
            checkWinCondition();}
            else{break;}
        }

        printDungeon();
        printCharacterStats();
    }
    

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

    private ArrayList<Characters> getCharactersInRoom(Room room) {
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
    public  void process1Character(Characters A){
        //Processes character actions
        Room current_room = A.getLocation();
        ArrayList<Creatures> creatures_in_room = getCreaturesInRoom(current_room);
        //Will include a character.move to process movement
        for(int i = 0; i<A.MoveCount;i++){
            if(creatures_in_room.size() > 0) {//if someone is in the room

                continue;
            }
            else{
            switch(DiceRolls.rollDice(2)){
                case 1:
                    A.move();
                    break;
                case 2:
                    A.searchTreasure();
                    break;}
            }
        }
    }
    
    //Processes the decision making for one creature. 
    //if a character is in the room, it automatically fights
    //if no other character is in the room, the moves according to
    //its profile specified in move()
    public void process1Creature(Creatures A){

        Room current_room = A.getLocation();
        ArrayList<Characters> characters_in_room = getCharactersInRoom(current_room);
        if(characters_in_room.size() > 0){//Need to check if someone is in the room


        }
        else{A.move();}
    }
    
    //Checks various end game conditions and modifies EndCondition accordingly
    public  void checkWinCondition(){
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

        //Change End Condition depending on the outcome
        if(TreasureCount >= 10){//10 Treasures
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("Adventurers collected 10 treasures!");
        }
        else if(CreatureList.size() <= 0){//All creatures eliminated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("Adventurers defeated all creatures!");
        }
        else if(CharacterList.size() <= 0){//All adventureers defeated
            EndCondition = false;
            System.out.println("Game Over");
            System.out.println("All adventurers defeated!");
        }
        else{EndCondition = true;}

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
