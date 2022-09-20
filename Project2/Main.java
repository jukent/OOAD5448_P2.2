public class Main{


    /**
     * @param args: String[]
     * 
     * This is the main method that runs the game.
     */
    public static void main(String[] args) {
        run1Game();
        //runNGame(30);
    }


    /**
     * This method runs one game, verbose.
     */
    public static void run1Game() {
        GameEngine Game1 = new GameEngine("ShowAll");
        Game1.runGame();
    }


    /**
     * @param N: int
     * 
     * This method runs 'N' games.
     */
    public static void runNGame(int N) {
        for(int i = 0; i < N; i++) {
            int num = i+1;
            System.out.println("Game Number: " + num);
            GameEngine Game1 = new GameEngine("ShowNone");
            Game1.runGame();
        }
    }
}
