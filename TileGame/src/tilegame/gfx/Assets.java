package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, stone, tree, enemy1;
    public static BufferedImage[] player_down, player_up, player_right, player_left;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sheet.png"));

        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_right = new BufferedImage[3];
        player_left = new BufferedImage[3];

        player_down[0] = sheet.crop(width * 4, 0, width, height);
        player_down[1] = sheet.crop(width * 5, 0, width, height);
        player_down[2] = sheet.crop(width * 6, 0, width, height);
        player_up[0] = sheet.crop(width * 4, height * 3, width, height);
        player_up[1] = sheet.crop(width * 5, height * 3, width, height);
        player_up[2] = sheet.crop(width * 6, height * 3, width, height);
        player_right[0] = sheet.crop(width * 4, height * 2, width, height);
        player_right[1] = sheet.crop(width * 5, height * 2, width, height);
        player_right[2] = sheet.crop(width * 6, height * 2, width, height);
        player_left[0] = sheet.crop(width * 4, height, width, height);
        player_left[1] = sheet.crop(width * 5, height, width, height);
        player_left[2] = sheet.crop(width * 6, height, width, height);

        
        
        enemy1 = sheet.crop(width*7, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, 0, width, height * 2);
    }

}
