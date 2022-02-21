package entities.statics;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class fireTrap extends StaticEntity {
    private int current_image;
    private final int NUMBER_OF_IMAGES = 8;
    private int timer;

    public fireTrap(Handler handler, float x, float y){
        super(handler,x,y, 38*2,47*2);

        bounds.x = 30;
        bounds.y = 70;
        bounds.width = 22;
        bounds.height = 2;
        current_image = 0;
        timer = 0;

        damage = 2;
        maxHealth = health = 1000;
    }

    @Override
    public void tick(){
        ++timer;
        if(timer >= 10) {
            timer = 0;
            ++current_image;
            if (current_image >= NUMBER_OF_IMAGES)
                current_image = 0;
        }
    }

    @Override
    public void die(){

    }

    public boolean dealsDamage(){
        return true;
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.fireTrap[current_image], (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }
}