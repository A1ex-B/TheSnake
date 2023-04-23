import Game.GlobalConfiguration;
import Game.SceneRunner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        if (args.length > 0 && args[0].equalsIgnoreCase("debug")) {
            GlobalConfiguration.DebugModeIsOn = true;
        }
        try {
            var game = new SceneRunner();
            game.Start();
        } catch (Exception e) {
            System.out.println(e);
            for (var el: e.getStackTrace()) {
                System.out.println(el.toString());
            }
        }
        
    }
}