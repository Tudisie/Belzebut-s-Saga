package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Mushroom1 extends StaticEntity {
    public Mushroom1(Handler handler, float x, float y){
        super(handler,x,y, 64,64);

        bounds.x = 20;
        bounds.y = 19;
        bounds.width = 30;
        bounds.height = 10;

        maxHealth = health = 3;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.mushroomItem.createNew((int)(x),(int)(y + 10)));
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.mushroom1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
