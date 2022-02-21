package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Flower_Orange extends StaticEntity {
    public Flower_Orange(Handler handler, float x, float y){
        super(handler,x,y, 64,64);

        bounds.x = 20;
        bounds.y = 15;
        bounds.width = 30;
        bounds.height = 20;

        maxHealth = health = 2;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        if(chances(90))
            handler.getWorld().getItemManager().addItem(Item.flowerItem.createNew((int)(x),(int)(y + 10)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.flower_orange,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
