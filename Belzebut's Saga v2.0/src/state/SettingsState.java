package state;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.Handler;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.Text;

public class SettingsState extends State{

    private final UIManager uiManager;
    private final BufferedImage wallpaper;

    public SettingsState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        wallpaper = ImageLoader.loadimage("../res/textures/Wallpaper.jpg");

        int DEFAULT_BUTTON_WIDTH = 284;
        int DEFAULT_BUTTON_HEIGHT = 53;

        final int numberOfOptions = 2;
        int[] menuOptions_y = {445,525};
        BufferedImage[][] menuOptions = {Assets.btn_music,Assets.btn_back};

        //OPTION 1 : Mute/Unmute Music

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[0], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[0], new ClickListener() {
            @Override
            public void onClick() {
                if(!silentMusic){
                    if (clip.isRunning())
                        clip.close();
                }else{
                    if (clip.isRunning())
                        clip.close();
                    clip.setFramePosition(0);
                    setMenuMusic();
                    clip.loop(clip.LOOP_CONTINUOUSLY);
                }
                silentMusic = !silentMusic;

            }
        }));

        //OPTION 2 : Back

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[1], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[1], new ClickListener() {
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
        g.drawImage(Assets.WASD,220,20,null);
        g.drawImage(Assets.SHIFT,267,138,null);
        g.drawImage(Assets.ESC,306,198,null);
        g.drawImage(Assets.E,328,258,null);
        g.drawImage(Assets.L,328,318,null);
        g.drawImage(Assets.ENTER,328,380,null);

        Text.drawString(g,"Moving Keys",390,110,false, Color.WHITE,Assets.font24);
        Text.drawString(g,"Run",390,170,false, Color.WHITE,Assets.font24);
        Text.drawString(g,"Back to game.Main Menu",390,230,false, Color.WHITE,Assets.font24);
        Text.drawString(g,"Inventory",390,290,false, Color.WHITE,Assets.font24);
        Text.drawString(g,"Relax Animation",390,350,false, Color.WHITE,Assets.font24);
        Text.drawString(g,"Confirm Action",390,410,false, Color.WHITE,Assets.font24);
        uiManager.render(g);
    }
}
