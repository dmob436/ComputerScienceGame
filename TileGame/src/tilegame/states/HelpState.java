package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Game;
import tilegame.Handler;
import tilegame.gfx.ImageLoader;

public class HelpState extends State {

    private BufferedImage helpMenu;

    public HelpState(Handler handler) {
        super(handler);
        helpMenu = ImageLoader.loadImage("/Help_Menu.png");
    }

    @Override
    public void update() {
        if (handler.getMouseManager().isLeftPressed()) {
            if ((handler.getMouseManager().getMouseX() > 18 && handler.getMouseManager().getMouseX() < 138)
                    && (handler.getMouseManager().getMouseY() > 20 && handler.getMouseManager().getMouseY() < 60)) {
                State.setState(handler.getGame().menuState);
                Game.getMainSound().stop();

            }
        }
        if (handler.getKeyManager().exit) {
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(helpMenu, 0, 0, null);
    }

}
