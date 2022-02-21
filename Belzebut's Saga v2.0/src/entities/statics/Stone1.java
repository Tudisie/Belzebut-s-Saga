package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Stone1 extends StaticEntity {
    public Stone1(Handler handler, float x, float y){
        super(handler,x,y, 48,48);

        bounds.x = 6;
        bounds.y = 18;
        bounds.width = 38;
        bounds.height = 16;

        maxHealth = health = 16;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)(x + 15),(int)(y + 15)));
        if(chances(50))
            handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)(x - 10),(int)(y - 20)));
        handler.getWorld().getEntityManager().getPlayer().addExp(2);
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.stone1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
