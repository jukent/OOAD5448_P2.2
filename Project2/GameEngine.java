import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    ArrayList<Characters> CharacterList= new ArrayList<Characters>();
    ArrayList<Creatures> CreatureList= new ArrayList<Creatures>();

    private int TreasureCount = 0;
    int CreatureCount = 0;
    int ID = 0;
    boolean EndCondition = false;

    GameEngine(){
        populateCharacters();



    } //Constructor. Initializes the board
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
    public static void runGame(){}
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
    public  void processTurn(){
        //Processes all characters
    }
    public  void process1Character(){
        //Processes character actions
        //Will include a character.move to process movement
    }
    public  void checkWinCondition(){
        int TC = 0;
        for(Creatures I: CreatureList){
            if(I.getHealth() <= 0){
                CreatureList.remove(I);
            }
        }
        for(Characters I: CharacterList){
            if(I.getHealth() <= 0){
                CharacterList.remove(I);
            }
            TC += I.getTreasure();
        }
        TreasureCount = TC;
        if(TreasureCount >= 10){
            EndCondition = true;
        }
        else if(CreatureList.size() <= 0){
            EndCondition = true;
        }
        else{EndCondition = false;}

    }






}
