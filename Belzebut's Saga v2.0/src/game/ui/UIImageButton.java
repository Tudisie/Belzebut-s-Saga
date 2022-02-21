package game.ui;

import game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(int level, float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker){
        super(x,y,width,height, level);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g, Handler handler) {
        if(handler.getGame().getUnlockedLevels() < level)
            g.drawImage(images[2],(int)x,(int)y,width,height,null);
        else if(hovering)
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
