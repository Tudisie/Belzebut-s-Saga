package entities.statics;

import entities.statics.StaticEntity;
import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Cactus1 extends StaticEntity {

    public Cactus1(Handler handler, float x, float y){
        super(handler,x,y, 38*2,47*2);

        bounds.x = 30;
        bounds.y = 60;
        bounds.width = 22;
        bounds.height = 10;

        damage = 1;
        maxHealth = health = 8;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(2);
    }

    public boolean dealsDamage(){
        return true;
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.cactus1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
