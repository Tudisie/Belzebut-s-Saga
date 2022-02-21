package entities.creatures;

import game.Handler;
import gfx.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Monsters extends Creature{

    protected Animation animDown,animUp,animLeft,animRight,animIdle;
    protected int range; // range to follow player
    protected int rest = 0;
    private float xMoveRest, yMoveRest;

    public Monsters(Handler handler, float x, float y) {
        super(handler, x, y, 80, 80);

        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 50;
        bounds.height = 55;
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        if(rest <= 0) {
            checkPlayerInRange();

            boolean intersectsLeft,intersectsUp,intersectsDown,intersectsRight;
            intersectsDown = intersectsLeft = intersectsRight = intersectsUp = false;

            if (getCollisionBounds(xMove, 0).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0))){
                if(xMove > 0)
                    intersectsLeft = true;
                else
                    intersectsRight = true;
            }
            if (getCollisionBounds(0, yMove).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0))){
                if(yMove > 0)
                    intersectsDown = true;
                else
                    intersectsUp = true;
            }


            if (intersectsDown || intersectsLeft || intersectsUp || intersectsRight)
            {
                rest = 150;
                boolean playertakesdamage = true;

                if (handler.getWorld().getEntityManager().getPlayer().attack){
                    if(intersectsDown && !handler.getWorld().getEntityManager().getPlayer().lastYDir)
                        playertakesdamage = false;
                    else if(intersectsUp && handler.getWorld().getEntityManager().getPlayer().lastYDir)
                        playertakesdamage = false;
                    else if(intersectsRight && handler.getWorld().getEntityManager().getPlayer().lastXDir)
                        playertakesdamage = false;
                    else if(intersectsLeft && !handler.getWorld().getEntityManager().getPlayer().lastXDir)
                        playertakesdamage = false;
                }

                if (!playertakesdamage) { //monster takes damage
                    //Don't call hurt() method because the monster will take damage in Entities.java : this.hurt(e.damage);
                    xMoveRest = -xMove / 2;
                    yMoveRest = -yMove / 2;
                } else { // player takes damage
                    if(handler.getWorld().getEntityManager().getPlayer().getX() < x)
                        handler.getWorld().getEntityManager().getPlayer().lastXDir = true;
                    else
                        handler.getWorld().getEntityManager().getPlayer().lastXDir = false;

                    if(handler.getWorld().getEntityManager().getPlayer().getY() < y)
                        handler.getWorld().getEntityManager().getPlayer().lastYDir = false;
                    else
                        handler.getWorld().getEntityManager().getPlayer().lastYDir = true;

                    handler.getWorld().getEntityManager().getPlayer().hurt(damage);
                    xMoveRest = yMoveRest = 0;
                }
            }
        } else {
            --rest;
            xMove = xMoveRest;
            yMove = yMoveRest;
        }
        move(); //Creatures.java
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width,height,null);
    }

    private BufferedImage getCurrentAnimationFrame(){

        if(xMove == 0 && yMove == 0)
            return animIdle.getCurrentFrame();
        if(Math.abs(xMove) > Math.abs(yMove)){
            if(xMove < 0)
                return animLeft.getCurrentFrame();
            return animRight.getCurrentFrame();
        }else{
            if(yMove > 0)
                return animDown.getCurrentFrame();
            return animUp.getCurrentFrame();
        }

    }

    @Override
    public void die() {}

    private void checkPlayerInRange(){
        //Center of player
        int xp = (int)handler.getWorld().getEntityManager().getPlayer().getX() + 80;
        int yp = (int)handler.getWorld().getEntityManager().getPlayer().getY() + 90;
        int xm = (int)(x + 40);
        int ym = (int)(y + 40);

        int actualRange = (int)Math.sqrt( Math.abs((xp-xm)*(xp-xm)) + Math.abs((yp-ym)*(yp-ym)) );
        if(range >= actualRange){
            xMove = speed * Math.abs(xp-xm) / actualRange;
            yMove = speed * Math.abs(yp-ym) / actualRange;
            if(xp < xm)
                xMove *= -1;
            if(yp < ym)
                yMove *= -1;
        }
        else{
            xMove = 0;
            yMove = 0;
        }
    }
}
