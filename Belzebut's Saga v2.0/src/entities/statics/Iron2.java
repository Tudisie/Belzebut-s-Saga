package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Iron2 extends StaticEntity {
    public Iron2(Handler handler, float x, float y){
        super(handler,x,y, 48,96);

        bounds.x = 8;
        bounds.y = 56;
        bounds.width = 32;
        bounds.height = 14;

        maxHealth = health = 90;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(10);
        handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x + 20),(int)(y + 60)));
        if(chances(85))
            handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x - 10),(int)(y + 7)));
        if(chances(30))
            handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x + 15),(int)(y - 27)));
        if(chances(8))
            handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x - 20),(int)(y + 30)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.iron2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
