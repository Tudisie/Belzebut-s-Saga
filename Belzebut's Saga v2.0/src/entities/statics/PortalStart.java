package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class PortalStart extends StaticEntity {
    public PortalStart(Handler handler, float x, float y){
        super(handler,x,y, (int)(66*1.8),(int)(66*1.8));

        bounds.x = 0;
        bounds.y = 75;
        bounds.width = 120;
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
        g.drawImage(Assets.portalStart,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
