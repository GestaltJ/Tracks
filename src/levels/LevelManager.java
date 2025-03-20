package levels;

import main.Game;
import utilization.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
       //levelSprite = Load_Save.GetSpriteAtlas(Load_Save.LEVEL_ATLAS);
        importOutsideSprites();
    }

    private void importOutsideSprites() {
        BufferedImage img = Load_Save.GetSpriteAtlas(Load_Save.LEVEL_ATLAS);
        levelSprite = new BufferedImage[96];
        for (int j = 0; j < 8; j++){
            for ( int i = 0; i < 12; i++){
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite[2], 0, 0, null);
    }

    public void update(){

    }


}
