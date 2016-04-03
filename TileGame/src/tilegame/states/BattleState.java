package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Handler;

import tilegame.entity.EntityManager;
import tilegame.entity.creatures.Player;
import tilegame.gfx.ImageLoader;
import tilegame.worlds.World;

public class BattleState extends State {

    BufferedImage battleScreen, attackScreen;

    private boolean isAttack = false;
    private EntityManager entityManager;
    private World world;
    private boolean isPlayerTurn = true;
    private boolean isBossTurn = false;

    public BattleState(Handler handler) {
        super(handler);

        battleScreen = ImageLoader.loadImage("/BattleState2.png");
        attackScreen = ImageLoader.loadImage("/BattleState1_Attack.png");
    }

    @Override
    public void update() {
       // System.out.println(handler.getMouseManager().getMouseX() + "X , Y" + handler.getMouseManager().getMouseY());

        //Run Button
        if (!isAttack) {
            if ((handler.getMouseManager().getMouseX() > 415 && handler.getMouseManager().getMouseX() < 580)
                    && handler.getMouseManager().getMouseY() > 436 && handler.getMouseManager().getMouseY() < 580) {
                if (handler.getMouseManager().isLeftPressed()) {

                    State.setState(handler.getGame().gameState);

                }

            }
        }
        //Attack Button
        if ((handler.getMouseManager().getMouseX() > 260 && handler.getMouseManager().getMouseX() < 414)
                && handler.getMouseManager().getMouseY() > 437 && handler.getMouseManager().getMouseY() < 580) {
            if (handler.getMouseManager().isLeftPressed()) {
                isAttack = true;
            }

        }
        if (isPlayerTurn) {
            // The First Attack Box
            if ((handler.getMouseManager().getMouseX() > 260 && handler.getMouseManager().getMouseX() < 416)
                    && handler.getMouseManager().getMouseY() > 437 && handler.getMouseManager().getMouseY() < 515) {
                if (handler.getMouseManager().isLeftPressed()) {
                    System.out.println("Is Boss Turn " + isBossTurn);
                    isPlayerTurn = false;
                    isBossTurn = true;
                    
                }

            }
            //The Second Attack box
            if ((handler.getMouseManager().getMouseX() > 416 && handler.getMouseManager().getMouseX() < 577)
                    && handler.getMouseManager().getMouseY() > 437 && handler.getMouseManager().getMouseY() < 515) {
                if (handler.getMouseManager().isLeftPressed()) {
                    isPlayerTurn = false;
                    isBossTurn = true;
                }

            }
            //The Third attack box
            if ((handler.getMouseManager().getMouseX() > 260 && handler.getMouseManager().getMouseX() < 416)
                    && handler.getMouseManager().getMouseY() > 515 && handler.getMouseManager().getMouseY() < 580) {
                if (handler.getMouseManager().isLeftPressed()) {
                    isPlayerTurn = false;
                    isBossTurn = true;
                }

            }
            //The Fourth Attack box
            if ((handler.getMouseManager().getMouseX() > 415 && handler.getMouseManager().getMouseX() < 580)
                    && handler.getMouseManager().getMouseY() > 515 && handler.getMouseManager().getMouseY() < 580) {
                if (handler.getMouseManager().isLeftPressed()) {
                    isPlayerTurn = false;
                    isBossTurn = true;
                }

            }
        }

    }

    @Override
    public void render(Graphics g) {

        if (isAttack) {
            g.drawImage(attackScreen, 0, 0, null);
        } else {
            g.drawImage(battleScreen, 0, 0, null);
        }
    }

}
