package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Tree4 extends StaticEntity {
    public Tree4(Handler handler, float x, float y){
        super(handler,x,y, (int)(96*1.5),(int)(160*1.5));

        bounds.x = 40;
        bounds.y = (int)(height/1.5f + 40);
        bounds.width = width - 92;
        bounds.height = (int)(height - height/1.5f - 65);

        maxHealth = health = 20;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(4);
        handler.getWorld().getItemManager().addItem(Item.plankItem.createNew((int)(x),(int)(y+20)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.tree4,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
