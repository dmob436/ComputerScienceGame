
package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys; 
    public boolean up, down, left, right, exit, quit, battle;
    
    public KeyManager(){
        keys  = new boolean [256];
    }
    public void update(){
        up  = keys[KeyEvent.VK_UP];
        down  = keys[KeyEvent.VK_DOWN];
        left  = keys[KeyEvent.VK_LEFT];
        right  = keys[KeyEvent.VK_RIGHT];
        battle = keys[KeyEvent.VK_SPACE];
        exit = keys[KeyEvent.VK_ESCAPE];
        quit = keys[KeyEvent.VK_Q];
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
      keys [e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
      keys [e.getKeyCode()] = false;
    }
    
}
