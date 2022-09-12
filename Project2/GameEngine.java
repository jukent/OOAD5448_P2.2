import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    //Array list that contains all characters and Creatures
    ArrayList<Characters> CharacterList= new ArrayList<Characters>();
    ArrayList<Creatures> CreatureList= new ArrayList<Creatures>();

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

        for(Characters I: CharacterList){
            I.setLocation(0,1,1);
        }
        for(Creatures I: CreatureList){
            I.setLocation(DiceRolls.rollDice(3),DiceRolls.rollDice(3),DiceRolls.rollDice(3));//Set location of creatures to be random
        }


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
    }
    
    //Processes the decision making for one character. 
    //if a creature is in the room, it automatically fights
    //if no other creature is in the room, the character randomly
    //decides to search for treasure or move(). Decision of direction
    //to profile is specified in move()
    public  void process1Character(Characters A){
        //Processes character actions
        //Will include a character.move to process movement
        for(int i = 0; i<A.MoveCount;i++){
            if(){//if someone is in the room

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
        if(){//Need to check if someone is in the room


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






}
