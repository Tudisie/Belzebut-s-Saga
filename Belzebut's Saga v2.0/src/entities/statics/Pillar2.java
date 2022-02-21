package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Pillar2 extends StaticEntity {
    public Pillar2(Handler handler, float x, float y){
        super(handler,x,y, 64,128);

        bounds.x = 8;
        bounds.y = 80;
        bounds.width = 49;
        bounds.height = 25;

        maxHealth = health = 1000;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.pillar2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
