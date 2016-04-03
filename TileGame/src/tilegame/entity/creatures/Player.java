package tilegame.entity.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Handler;
import tilegame.entity.EntityManager;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.states.BattleState;
import tilegame.states.State;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;

    private int playerHealth = Creature.DEFAULT_HEALTH;
    private EntityManager entityManager;
    private FirstBoss b1;
    private BattleState battle;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 18;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animations 
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

    }

    @Override
    public void update() {
//        playerAttacks();
        //Animations
        animDown.update();
        animUp.update();
        animLeft.update();
        animRight.update();

        //Movement
        getInput();
        move();

        handler.getGameCamera().centreOnEntity(this);

    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
        if (handler.getKeyManager().battle) {
            State.setState(handler.getGame().battleState);
        }

    }

    public void death() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        //Collision Detection deBuging
        //g.setColor(Color.BLUE);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        //     (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
        // bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    public void playerAttacks() {
    
    }

    public int getPlayerHealth() {
        return playerHealth;
    }
}
