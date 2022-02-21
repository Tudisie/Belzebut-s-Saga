package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class PortalEnd extends StaticEntity {
    public PortalEnd(Handler handler, float x, float y){
        super(handler,x,y, (int)(66*1.8),(int)(66*1.8));

        bounds.x = 0;
        bounds.y = 75;
        bounds.width = 120;
        bounds.height = 25;

        maxHealth = health = 1000;
    }

    @Override
    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getExp() >= handler.getWorld().getExpToNextLevel()){
            //opened portal
            float xpmove = handler.getWorld().getEntityManager().getPlayer().getxMove();
            float ypmove = handler.getWorld().getEntityManager().getPlayer().getyMove();
            if(getCollisionBounds(0,0).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(xpmove,ypmove)))
                handler.getWorld().finishLevel();
        }
    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.portalEnd,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
