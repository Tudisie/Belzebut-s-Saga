package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Iron1 extends StaticEntity {
    public Iron1(Handler handler, float x, float y){
        super(handler,x,y, 48,48);

        bounds.x = 8;
        bounds.y = 14;
        bounds.width = 32;
        bounds.height = 20;

        maxHealth = health = 50;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(8);
        if(chances(90))
            handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)(x),(int)(y + 3)));
        if(chances(70))
            handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x + 20),(int)(y + 40)));
        if(chances(30))
            handler.getWorld().getItemManager().addItem(Item.ironItem.createNew((int)(x - 10),(int)(y + 7)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.iron1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
