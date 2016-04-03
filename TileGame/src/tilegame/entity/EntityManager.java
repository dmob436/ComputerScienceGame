package tilegame.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import tilegame.Handler;
import tilegame.entity.creatures.Player;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                return - 1;
            }
            return 1;
        }
        
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.update();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {

        for (Entity e : entities) {

            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }
    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEntity(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
