package tilegame.worlds;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.entity.Entity;
import tilegame.entity.EntityManager;
import tilegame.entity.creatures.FirstBoss;
import tilegame.entity.creatures.Player;
import tilegame.entity.statics.Tree;
import tilegame.states.State;
import tilegame.tile.Tile;
import tilegame.utils.Utils;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    //Entities
    private EntityManager entityManager;

    private Player player;
    private Tree tree;
    private FirstBoss b1;

    public World(Handler handler, String path) {
        this.handler = handler;
        player = new Player(handler, 0, 0);
        b1 = new FirstBoss(handler, 300, 300);
        tree = new Tree(handler, 100, 250);
        //creating the player to the screen
        entityManager = new EntityManager(handler, player);
        //adding a tree Entity
        entityManager.addEntity(tree);
        //Test Boss
        entityManager.addEntity(b1);
        //loading the map to the screen
        loadWorld(path);
        //removing the tree Entity
        entityManager.removeEntity(tree);

        //Setting the spawn area of the Player
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

    }

    public void update() {

        entityManager.update();
        entityCollison();

    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.grassTile;
        }
        return t;

    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

            }
        }
    }

    public void entityCollison() {

        //Top of Enemy
        if (player.getCollisonBounds(0f, 0f).intersectsLine(b1.getX(), b1.getY(), b1.getX() + b1.getWidth(), b1.getY())) {
            State.setState(handler.getGame().battleState);
        }
        //Left Side
        if (player.getCollisonBounds(0f, 0f).intersectsLine(b1.getX(), b1.getY(), b1.getX(), b1.getY() + b1.getHeight())) {
            State.setState(handler.getGame().battleState);
        }
        //Right Side
        if (player.getCollisonBounds(0f, 0f).intersectsLine(b1.getX() + b1.getWidth(), b1.getY(), b1.getX(), b1.getY() + b1.getHeight())) {
            State.setState(handler.getGame().battleState);
        }
        //Bottom
        if (player.getCollisonBounds(0f, 0f).intersectsLine(b1.getX() + b1.getWidth(), b1.getY() + b1.getHeight(), b1.getX() + b1.getWidth(), b1.getY() + b1.getHeight())) {
            State.setState(handler.getGame().battleState);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
