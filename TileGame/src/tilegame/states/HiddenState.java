package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Handler;
import tilegame.gfx.ImageLoader;

public class HiddenState extends State{

    private BufferedImage sparrow;
    
    public HiddenState(Handler handler) {
        super(handler);
        sparrow = ImageLoader.loadImage("/sparrow.png");
        
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sparrow, -100, 0, null);
    }
    
}
