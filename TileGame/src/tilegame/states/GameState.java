package tilegame.states;

import java.awt.Graphics;
import tilegame.Game;
import tilegame.Handler;
import tilegame.worlds.World;

public class GameState extends State {

    private World world;
  

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "World1.txt");
       
        handler.setWorld(world);
    }

    @Override
    public void update() {
       world.update();
       //Going back to main menu 
       if(handler.getKeyManager().exit){
           State.setState(handler.getGame().menuState);
           //Pausing the Sound
           Game.getSecondSound().stop();
       }
       //Closing the game
       if (handler.getKeyManager().quit){
           System.exit(0);
       }
      
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        
        
    }

}
