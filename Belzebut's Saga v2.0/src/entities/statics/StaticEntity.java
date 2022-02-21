package entities.statics;

import entities.Entity;
import game.Handler;

import java.util.Random;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler,x,y,width,height);
    }
    public boolean chances(int k){
        //used for dropping a semi-random amount of items with k% chances of winning
        Random rand = new Random();
        if(rand.nextInt(100) < k)
            return true;
        return false;
    }

}
