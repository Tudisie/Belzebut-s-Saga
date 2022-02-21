package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Gold2 extends StaticEntity {
    public Gold2(Handler handler, float x, float y){
        super(handler,x,y, 48,96);

        bounds.x = 8;
        bounds.y = 56;
        bounds.width = 32;
        bounds.height = 14;

        maxHealth = health = 100;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(12);
        if(chances(70))
            handler.getWorld().getItemManager().addItem(Item.goldItem.createNew((int)(x - 10),(int)(y + 7)));
        if(chances(25))
            handler.getWorld().getItemManager().addItem(Item.goldItem.createNew((int)(x + 15),(int)(y - 27)));
        if(chances(8))
            handler.getWorld().getItemManager().addItem(Item.goldItem.createNew((int)(x - 20),(int)(y + 30)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.gold2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
