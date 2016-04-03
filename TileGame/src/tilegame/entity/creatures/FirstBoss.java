package tilegame.entity.creatures;

import java.awt.Graphics;
import java.util.Random;
import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.states.State;

public class FirstBoss extends Creature {
    
    private Player player;
    
    public FirstBoss(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_WIDTH);
        bounds.x = 18;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
    }
    
    @Override
    public void update() {
        if(State.getState() == handler.getGame().battleState){
            //firstBossAttacks();
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy1, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    public void firstBossAttacks() {
        
        Random ran = new Random();
        
        switch (ran.nextInt(4)) {
            case 0:
                player.setHealth(player.health - 2);
                break;
            case 1:
                player.setHealth(player.health - 4);
                break;
            
            case 2:
                player.setHealth(player.health - 1);
                break;
            case 3:
                player.setHealth(player.health - 5);
                break;
        }
    }
    
}
