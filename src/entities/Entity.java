package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;


    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //initiaHitbox();
    }

    protected void drawHitbox(Graphics g){
        //debugging hitbox
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }


    protected void initiaHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float( x, y, width, height);
    }

//    public void updateHitbox(){
//        hitbox.x = (int) x;
//        hitbox.y = (int) y;
//    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }




}