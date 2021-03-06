package gfx;

import java.awt.image.BufferedImage;

public class Animation {
    private int speed,index;
    private BufferedImage[] frames;
    private long lastTime,timer;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            ++index;
            timer = 0;
            if(index >= frames.length)
                index = 0;
        }
    }

    public void reset(){
        timer = 0;
        index = 0;
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
}
