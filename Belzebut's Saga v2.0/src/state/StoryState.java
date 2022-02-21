package state;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import game.Handler;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.Text;

public class StoryState extends State{

    private final UIManager uiManager;
    private final BufferedImage wallpaper;

    public StoryState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        wallpaper = ImageLoader.loadimage("../res/textures/WallpaperDarker.jpg");

        int DEFAULT_BUTTON_WIDTH = 284;
        int DEFAULT_BUTTON_HEIGHT = 53;

        final int numberOfOptions = 1;
        int[] menuOptions_y = {525};
        BufferedImage[][] menuOptions = {Assets.btn_back};

        //BACK

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[0], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[0], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().menuState = new MenuState(handler);
                State.setState(handler.getGame().menuState);
            }
        }));
    }

    public void tick(){
        uiManager.tick();
    }
    public void render(Graphics g){
        g.drawImage(wallpaper,0,0,null);
        Story.render(g);
        uiManager.render(g);
    }
}
