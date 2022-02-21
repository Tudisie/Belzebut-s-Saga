package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Tree3 extends StaticEntity {
    public Tree3(Handler handler, float x, float y){
        super(handler,x,y, (int)(200*0.8),(int)(230*0.8));

        bounds.x = 55;
        bounds.y = (int)(height/1.5f + 20);
        bounds.width = width - 112;
        bounds.height = (int)(height - height/1.5f - 45);

        maxHealth = health = 12;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 30),(int)(y + 60)));
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 40),(int)(y + 75)));
        handler.getWorld().getEntityManager().getPlayer().addExp(4);

        if(chances(600))
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 34),(int)(y + 95)));

        if(chances(700))
            handler.getWorld().getItemManager().addItem(Item.plankItem.createNew((int)(x),(int)(y+20)));
        if(chances(200))
            handler.getWorld().getItemManager().addItem(Item.plankItem.createNew((int)(x),(int)(y+20)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.tree3,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
