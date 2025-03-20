package levels;

import main.Game;
import utilization.Load_Save;
import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
       //levelSprite = Load_Save.GetSpriteAtlas(Load_Save.LEVEL_ATLAS);
        importOutsideSprites();
        //levelOne = new Level(Load_Save.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = Load_Save.GetSpriteAtlas(Load_Save.LEVEL_ATLAS);
        levelSprite = new BufferedImage[1000];
        for (int j = 0; j < 8; j++){
            for ( int i = 0; i < 12; i++){
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
//        for (int j = 0; j < Game.GAME_HEIGHT; j++){
//            for (int i = 0; i < Game.GAME_WIDTH; i++){
//                int index = levelOne.getSpriteIndex(i,j);
//                g.drawImage(levelSprite[index],TILES_SIZE*i,TILES_SIZE*j,TILES_SIZE,TILES_SIZE,null);
//            }
//        }
        for ( int j = 0; j < 25; j++){
            for ( int i = 0; i < 40; i++){
                int index = j*40 + i;
                g.drawImage(levelSprite[index], i*32, j*32, 32, 32, null);
            }
        }
//        g.drawImage(levelSprite[0], 1, 0, null);
//        g.drawImage(levelSprite[1], 35, 35, null);
    }

    public void update(){

    }


}
