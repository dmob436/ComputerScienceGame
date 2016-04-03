package tilegame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import tilegame.Handler;

public abstract class Entity {

    protected Handler handler;
    public float x, y;
    protected int width, height;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public boolean checkEntityCollisons(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this)){
                continue;
            }
            if (e.getCollisonBounds(0f, 0f).intersects(getCollisonBounds(xOffset, yOffset))) {
                return true;
            }
        
        }
        return false;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public Rectangle getCollisonBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
