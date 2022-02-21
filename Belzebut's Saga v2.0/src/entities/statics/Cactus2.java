package entities.statics;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class Cactus2 extends StaticEntity {
    public Cactus2(Handler handler, float x, float y){
        super(handler,x,y, 26*2,47*2);

        bounds.x = 0;
        bounds.y = 65;
        bounds.width = 22;
        bounds.height = 10;

        damage = 1;
        maxHealth = health = 8;
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getEntityManager().getPlayer().addExp(2);
    }

    public boolean dealsDamage(){
        return true;
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.cactus2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}
