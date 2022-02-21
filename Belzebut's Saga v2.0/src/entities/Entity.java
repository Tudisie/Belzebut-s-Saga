package entities;

import java.awt.*;


import entities.statics.*;
import entities.creatures.*;
import game.Handler;
import gfx.Assets;

public abstract class Entity {
    public static final int DEFAULT_HEALTH = 4;

    protected Handler handler;
    protected float x,y;
    protected int width,height;
    protected int maxHealth;
    protected int health;
    protected boolean active = true; //false = the entity will disappear
    protected Rectangle bounds; // For collision
    protected int damage;
    protected int resting; //if hurt. while resting show health bar

    public Entity(Handler handler,float x,float y,int width,int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        maxHealth = health = DEFAULT_HEALTH;

        damage = 0; //by default

        bounds = new Rectangle(0,0,0,0); // by default
    }

    public float getX(){
        return x;
    }
    public void setX(float x){
        this.x = x;
    }
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public abstract void tick();

    public void renderHealth(Graphics g){
        if(resting > 0){
            --resting;
            int xbar = (int)(-handler.getGameCamera().getxOffset() + x + width/2 - 40);
            int ybar = (int)(-handler.getGameCamera().getyOffset() + y + height);
            int xwid = 80;
            int xhei = 10;
            float percent = (float)health / maxHealth;
            g.drawImage(Assets.expbarFilled,xbar,ybar,(int)(xbar + (double)xwid * percent),ybar + xhei,0,0,276 ,25,null);
            g.drawImage(Assets.expbar,xbar,ybar, xwid,xhei,null);
        }
    }

    public abstract void render(Graphics g);

    public abstract void die();

    public boolean dealsDamage(){
        return false;
    } //will be implemented only for damaging entitites

    public void hurt(int amt){
        health -= amt;
        resting = 60;
        if(health <= 0) {
            die();
            active = false;
        }
    }

    public boolean isActive(){
        return active;
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset))) {
                if(e.dealsDamage())
                {
                    this.hurt(e.damage);
                }
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public int getDamage(){
        return damage;
    }
    public void setDamage(int dmg){ damage = dmg;}

    //Sablon Abstract Factory
    private static Entity e = null;
    public static Entity getEntity(String opt,Handler handler, int x,int y){

        if(opt.equals("Tree1"))
            e = new Tree1(handler,x,y);
        else if(opt.equals("Tree2"))
            e = new Tree2(handler,x,y);
        else if(opt.equals("Tree3"))
            e = new Tree3(handler,x,y);
        else if(opt.equals("Tree4"))
            e = new Tree4(handler,x,y);
        else if(opt.equals("Arbust"))
            e = new Arbust(handler,x,y);
        else if(opt.equals("Stone1"))
            e = new Stone1(handler,x,y);
        else if(opt.equals("Bronze"))
            e = new Bronze(handler,x,y);
        else if(opt.equals("Iron1"))
            e = new Iron1(handler,x,y);
        else if(opt.equals("Iron2"))
            e = new Iron2(handler,x,y);
        else if(opt.equals("Gold1"))
            e = new Gold1(handler,x,y);
        else if(opt.equals("Gold2"))
            e = new Gold2(handler,x,y);
        else if(opt.equals("fireTrap"))
            e = new fireTrap(handler,x,y);
        else if(opt.equals("Cactus1"))
            e = new Cactus1(handler,x,y);
        else if(opt.equals("Cactus2"))
            e = new Cactus2(handler,x,y);
        else if(opt.equals("Flower_Blue"))
            e = new Flower_Blue(handler,x,y);
        else if(opt.equals("Flower_Orange"))
            e = new Flower_Orange(handler,x,y);
        else if(opt.equals("Flower_Yellow"))
            e = new Flower_Yellow(handler,x,y);
        else if(opt.equals("Mushroom1"))
            e = new Mushroom1(handler,x,y);
        else if(opt.equals("Mushroom2"))
            e = new Mushroom2(handler,x,y);
        else if(opt.equals("Rocks1"))
            e = new Rocks1(handler,x,y);
        else if(opt.equals("Rocks2"))
            e = new Rocks2(handler,x,y);
        else if(opt.equals("Pillar1"))
            e = new Pillar1(handler,x,y);
        else if(opt.equals("Pillar2"))
            e = new Pillar2(handler,x,y);
        else if(opt.equals("Statue1"))
            e = new Statue1(handler,x,y);
        else if(opt.equals("Statue2"))
            e = new Statue2(handler,x,y);
        else if(opt.equals("Statue3"))
            e = new Statue3(handler,x,y);
        else if(opt.equals("Angel1"))
            e = new Angel1(handler,x,y);
        else if(opt.equals("Angel2"))
            e = new Angel2(handler,x,y);
        else if(opt.equals("PortalStart"))
            e = new PortalStart(handler,x,y);
        else if(opt.equals("PortalEnd"))
            e = new PortalEnd(handler,x,y);
        else if(opt.equals("Monster1"))
            e = new Monster1(handler,x,y);
        else if(opt.equals("Monster2"))
            e = new Monster2(handler,x,y);
        else if(opt.equals("Monster3"))
            e = new Monster3(handler,x,y);
        else if(opt.equals("Monster4"))
            e = new Monster4(handler,x,y);
        else if(opt.equals("Monster5"))
            e = new Monster5(handler,x,y);
        return e;
    }
}
