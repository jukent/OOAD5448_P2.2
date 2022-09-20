public class Main{


    public static void main(String[] args){
        //run1Game();
        runNGame(30);
        
    }

    public static void run1Game(){
        GameEngine Game1 = new GameEngine("ShowAll");
        Game1.runGame();
    }

    public static void runNGame(int N){
        for(int i = 0; i < N; i++){
            int num = i+1;
            System.out.println("Game Number: " + num);
            GameEngine Game1 = new GameEngine("ShowNone");
            Game1.runGame();

        }
        
    }
}


