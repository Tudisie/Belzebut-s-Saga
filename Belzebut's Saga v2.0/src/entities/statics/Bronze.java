package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Bronze extends StaticEntity {
    public Bronze(Handler handler, float x, float y){
        super(handler,x,y, 48,48);

        bounds.x = 6;
        bounds.y = 18;
        bounds.width = 38;
        bounds.height = 16;

        maxHealth = health = 35;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(5);
        if(chances(80))
            handler.getWorld().getItemManager().addItem(Item.bronzeItem.createNew((int)(x + 15),(int)(y + 15)));
        if(chances(30))
            handler.getWorld().getItemManager().addItem(Item.bronzeItem.createNew((int)(x),(int)(y + 35)));
        if(chances(5))
            handler.getWorld().getItemManager().addItem(Item.bronzeItem.createNew((int)(x - 15),(int)(y - 15)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.bronze,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
