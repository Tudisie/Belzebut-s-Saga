package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Arbust extends StaticEntity {
    public Arbust(Handler handler, float x, float y){
        super(handler,x,y, 64,64);

        bounds.x = 5;
        bounds.y = 5;
        bounds.width = 54;
        bounds.height = 48;
        maxHealth = health = 3;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(1);
        if(chances(80))
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 20),(int)(y + 40)));
        if(chances(33))
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x - 10),(int)(y - 15)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.arbust,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
