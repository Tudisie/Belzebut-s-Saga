package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Statue1 extends StaticEntity {
    public Statue1(Handler handler, float x, float y){
        super(handler,x,y, 64,128);

        bounds.x = 0;
        bounds.y = 80;
        bounds.width = 65;
        bounds.height = 25;

        maxHealth =  health = 1000;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.statue1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
