
package tilegame.entity.statics;

//Entity That do not Move 

import tilegame.Handler;
import tilegame.entity.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
    
}
