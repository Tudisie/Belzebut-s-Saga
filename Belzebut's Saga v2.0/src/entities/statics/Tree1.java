package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Tree1 extends StaticEntity {
    public Tree1(Handler handler, float x, float y){
        super(handler,x,y, (int)(63*1.7),(int)(117*1.7));

        bounds.x = 42;
        bounds.y = (int)(height/1.5f + 35);
        bounds.width = width - 78;
        bounds.height = 9;

        maxHealth = health = 6;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 20),(int)(y + 60)));
        handler.getWorld().getEntityManager().getPlayer().addExp(2);

        if(chances(25))
            handler.getWorld().getItemManager().addItem(Item.plankItem.createNew((int)(x + 30),(int)(y+180)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.tree1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
