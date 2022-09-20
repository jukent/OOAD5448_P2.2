public class TreasureHunt {
    
    protected String TreasureType;


    /**
     * @param Type: String
     * 
     * This method calls treasure hunts based on type of behavior.
     */
    public TreasureHunt(String Type) {
        // This class represents an example of encapsulation. 
        // This class hides the treasure hunt behavior for each character.
        // Since this varies so much, we encapsulate it in its own
        // class. This is just like the Strategy design pattern. 
        // "Encapsulate what varies" 
        TreasureType = Type;
    }


    /**
     * @return int
     * 
     * This method searches for treasure by rolling dice.
     */
    public int searchTreasure() {
        if (TreasureType == "Character") {
            // Included for error prevention
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
        if (TreasureType == "Thief") {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;
        } else { 
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);
        }
    }
}
