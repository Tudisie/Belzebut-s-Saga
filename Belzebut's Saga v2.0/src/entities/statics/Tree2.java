package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Tree2 extends StaticEntity {
    public Tree2(Handler handler, float x, float y){
        super(handler,x,y, (int)(98*1.7),(int)(120*1.7));

        bounds.x = 45;
        bounds.y = (int)(height/1.5f + 15);
        bounds.width = width - 92;
        bounds.height = 15;

        maxHealth = health = 10;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 30),(int)(y + 60)));
        handler.getWorld().getEntityManager().getPlayer().addExp(3);

        if(chances(60))
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 40),(int)(y + 75)));

        if(chances(40))
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + 20),(int)(y + 99)));

        if(chances(50))
            handler.getWorld().getItemManager().addItem(Item.plankItem.createNew((int)(x + 35),(int)(y+120)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.tree2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
