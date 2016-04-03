package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Game;
import tilegame.Handler;
import tilegame.gfx.ImageLoader;

public class MenuState extends State {

    private final BufferedImage Menu, MenuOverPlay, MenuOverHelp, MenuOverExit;
    private boolean isOverPlay = false, isOverHelp = false, isOverExit = false;

    public MenuState(Handler handler) {
        super(handler);
        Menu = ImageLoader.loadImage("/Main_Menu.png");
        MenuOverPlay = ImageLoader.loadImage("/Main_Menu_Play.png");
        MenuOverHelp = ImageLoader.loadImage("/Main_Menu_Help.png");
        MenuOverExit = ImageLoader.loadImage("/Main_Menu_Exit.png");

    }

    @Override
    public void update() {
        //Sets the volume level of main menu song
        Game.getMainSound().setVolume(-10.0f);
        //Plays the main menu song
        Game.getMainSound().play();
        //loops the music forever
        Game.getMainSound().loop(-1);

        //Setting the state to gameState
        if ((handler.getMouseManager().getMouseX() > 78 && handler.getMouseManager().getMouseX() < 526)
                && handler.getMouseManager().getMouseY() > 126 && handler.getMouseManager().getMouseY() < 222) {
            isOverPlay = true;
            if (handler.getMouseManager().isLeftPressed()) {
                //Setting the state
                State.setState(handler.getGame().gameState);
                //Pausing the main menu music 
                Game.getMainSound().stop();
                //resetting the game music
                Game.getSecondSound().reset();
                //playing the music
                Game.getSecondSound().play();
            }
        } else {
            isOverPlay = false;
        }
        //Setting state HelpState Menu
        if ((handler.getMouseManager().getMouseX() > 75 && handler.getMouseManager().getMouseX() < 526)
                && handler.getMouseManager().getMouseY() > 246 && handler.getMouseManager().getMouseY() < 354) {
            isOverHelp = true;
            if (handler.getMouseManager().isLeftPressed()) {
                State.setState(handler.getGame().helpState);

            }
        } else {
            isOverHelp = false;
        }
        //Closes Program
        if ((handler.getMouseManager().getMouseX() > 75 && handler.getMouseManager().getMouseX() < 526)
                && handler.getMouseManager().getMouseY() > 384 && handler.getMouseManager().getMouseY() < 481) {
            isOverExit = true;
            if (handler.getMouseManager().isLeftPressed()) {
                System.exit(0);
            }
        } else {
            isOverExit = false;
        }
        //Going to the hidden state class
        if ((handler.getMouseManager().getMouseX() > 0 && handler.getMouseManager().getMouseX() < 40)
                && handler.getMouseManager().getMouseY() > 0 && handler.getMouseManager().getMouseY() < 40) {

            if (handler.getMouseManager().isLeftPressed()) {
                State.setState(handler.getGame().hiddenState);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isOverPlay) {
            g.drawImage(MenuOverPlay, 0, 0, null);
        } else if (isOverHelp) {
            g.drawImage(MenuOverHelp, 0, 0, null);
        } else if (isOverExit) {
            g.drawImage(MenuOverExit, 0, 0, null);
        } else {
            g.drawImage(Menu, 0, 0, null);
        }
    }

}
