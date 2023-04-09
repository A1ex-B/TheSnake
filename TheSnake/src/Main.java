import Game.Game;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        try {
            var game = new Game();
            game.Start();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}