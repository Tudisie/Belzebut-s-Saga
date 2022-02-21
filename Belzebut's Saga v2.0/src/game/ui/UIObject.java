package game.ui;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {
    protected float x,y;
    protected int width,height;
    protected Rectangle bounds;
    protected boolean hovering = false;
    protected int level;

    public UIObject(float x, float y, int width, int height, int level){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g, Handler handler);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }

    public void onMouseRelease(MouseEvent e){
        if(hovering)
            onClick();
    }
}
