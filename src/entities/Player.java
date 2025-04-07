package entities;

import main.Game;
import utilization.Load_Save;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilization.Constants.PlayersConstants.*;
import static utilization.HelpMethods.*;

public class Player extends Entity {
    private BufferedImage[][] spriteAction;
    private int aniTick,aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f;
    private int[][] lvlData;
    private float xDrawOffset = 7 * Game.SCALE;
    private float yDrawOffset = 6 * Game.SCALE;


    //For physics
    private float airSpeed = 0f;
    private float gravity = 0.04f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;



    public Player (float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initiaHitbox(x, y,16 * Game.SCALE, 25 * Game.SCALE);
    }


    public void update() {

        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(spriteAction[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        //drawHitbox(g);
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

        if (inAir){
            playerAction = JUMP;
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

        if (jump){
            jump();
        }

        if(!left && !right && !inAir){
            return;
        }

        float xSpeed= 0;

        if (left ){
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        if (!inAir){
            if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        }


        if (inAir){
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData )){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                }else {
                    airSpeed = fallSpeedAfterCollision;
                    updateXPos(xSpeed);
                }
            }

        }else {
            updateXPos(xSpeed);
            moving = true;
        }



//        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
//            hitbox.x += xSpeed;
//            hitbox.y += ySpeed;
//            moving = true;
//        }

    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;

    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
                if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
                    hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
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

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if(!IsEntityOnFloor(hitbox, lvlData)){
            inAir = true;
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
    public void setJump(boolean jump) {
        this.jump = jump;
    }

}

