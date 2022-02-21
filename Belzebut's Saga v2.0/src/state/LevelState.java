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

public class LevelState extends State{

    private final UIManager uiManager;
    private final BufferedImage wallpaper, gamename1,gamename2;

    public LevelState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        wallpaper = ImageLoader.loadimage("../res/textures/Wallpaper.jpg");
        gamename1 = ImageLoader.loadimage("../res/textures/GameName1.png");
        gamename2 = ImageLoader.loadimage("../res/textures/GameName2.png");

        int DEFAULT_BUTTON_WIDTH = 284;
        int DEFAULT_BUTTON_HEIGHT = 53;

        final int numberOfOptions = 4;
        int[] menuOptions_y = {200,290,390,480};
        BufferedImage[][] menuOptions = {Assets.btn_lvl1,Assets.btn_lvl2, Assets.btn_lvl3 ,Assets.btn_back};

        //OPTION 1 : Level1

        uiManager.addObject(new UIImageButton(1,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[0], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[0], new ClickListener() {
            @Override
            public void onClick() {
                if(handler.getGame().getUnlockedLevels() >= 1) {
                    handler.getGame().currentLevel = 1;
                    handler.getMouseManager().setUiManager(null); //Disable Mouse for UI
                    handler.getGame().gameState = new GameState(handler,1);
                    State.setState(handler.getGame().gameState);
                }

            }
        }));

        //OPTION 2 : Level2

        uiManager.addObject(new UIImageButton(2,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[1], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[1], new ClickListener() {
            @Override
            public void onClick() {
                if(handler.getGame().getUnlockedLevels() >= 2) {
                    handler.getGame().currentLevel = 2;
                    handler.getMouseManager().setUiManager(null); //Disable Mouse for UI
                    handler.getGame().gameState = new GameState(handler,2);
                    State.setState(handler.getGame().gameState);
                }
            }
        }));

        //OPTION 3 : Level3

        uiManager.addObject(new UIImageButton(3,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[2], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[2], new ClickListener() {
            @Override
            public void onClick() {
                if(handler.getGame().getUnlockedLevels() >= 3) {
                    handler.getGame().currentLevel = 3;
                    handler.getMouseManager().setUiManager(null); //Disable Mouse for UI
                    handler.getGame().gameState = new GameState(handler,3);
                    State.setState(handler.getGame().gameState);
                }
            }
        }));

        //OPTION 4 : BACK

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[3], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[3], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().menuState = new MenuState(handler);
                State.setState(handler.getGame().menuState);
            }
        }));



    }

    public void tick(){
        //System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        uiManager.tick();
    }
    public void render(Graphics g){
        g.drawImage(wallpaper,0,0,null);
        g.drawImage(gamename1,(handler.getGame().getWidth() - gamename1.getWidth() ) / 2,30,null);
        g.drawImage(gamename2,(handler.getGame().getWidth() - gamename2.getWidth() ) / 2,100,null);
        Text.drawString(g,"A game created by Istrate Sebastian",490,handler.getGame().getHeight() - 25,false, Color.WHITE,Assets.font24);
        uiManager.render(g);
    }
}
