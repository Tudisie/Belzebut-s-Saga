package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Gold1 extends StaticEntity {
    public Gold1(Handler handler, float x, float y){
        super(handler,x,y, 48,48);

        bounds.x = 8;
        bounds.y = 14;
        bounds.width = 32;
        bounds.height = 20;

        maxHealth = health = 70;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(10);
        if(chances(80))
            handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)(x - 10),(int)(y + 20)));
        if(chances(75))
            handler.getWorld().getItemManager().addItem(Item.goldItem.createNew((int)(x + 20),(int)(y + 60)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.gold1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
