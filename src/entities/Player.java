package entities;

import utilization.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilization.Constants.PlayersConstants.*;

public class Player extends Entity {
    private BufferedImage[][] spriteAction;
    private int aniTick,aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 1.0f;



    public Player (float x, float y) {
        super(x, y);
        loadAnimations();
    }


    public void update() {



        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(spriteAction[playerAction][aniIndex],(int) x, (int) y,32*2,32*2, null);
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }


    private void setAnimation() {
        int startAni = playerAction;


        if (moving){
            playerAction = WALK;
        }
        else {
            playerAction = IDLE;
        }
        if (attacking){
            playerAction = ATTACK;
        }

        if (startAni != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down){
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {
            BufferedImage img = Load_Save.GetSpriteAtlas(Load_Save.PLAYER_ATLAS);

            spriteAction = new BufferedImage[9][13];
            for(int j = 0; j < spriteAction.length; j++) {
                for (int i = 0; i < spriteAction[j].length; i++) {
                    spriteAction[j][i] = img.getSubimage(i * 32, j * 32,32 , 32);
                }
            }
    }

    public void resetDirBoolean(){
        left = false;
        up = false;
        right = false;
        down = false;
    }



    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}

