package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import tilegame.audio.Sound;
import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.BattleState;
import tilegame.states.GameState;
import tilegame.states.HelpState;
import tilegame.states.HiddenState;
import tilegame.states.MenuState;
import tilegame.states.State;

public class Game implements Runnable {

    private Display display;

    private int width, height;
    public String title;

    private boolean isRunning = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    
 
    //States 
    public State gameState;
    public State menuState;
    public State helpState;
    public State battleState;
    public static State hiddenState;

    //Input 
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    //Sounds 
    private static Sound a, b;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init() {

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        //Sounds 
        a = new Sound("KissesinParadise.wav");
        b = new Sound("cartoon001.wav");

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        helpState = new HelpState(handler);
        hiddenState = new HiddenState(handler);
        battleState = new BattleState(handler);

        State.setState(battleState);

    }

    private void update() {
        keyManager.update();
       
        if (State.getState() != null) {
            State.getState().update();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                System.out.println("Update and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }

        }
        stop();
    }

    public static Sound getMainSound() {
        return a;
    }

    public static Sound getSecondSound() {
        return b;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    //Game Width and Height
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
