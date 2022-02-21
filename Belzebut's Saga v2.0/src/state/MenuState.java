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

public class MenuState extends State{

    private final UIManager uiManager;
    private final BufferedImage wallpaper, gamename1,gamename2;

    public MenuState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        wallpaper = ImageLoader.loadimage("../res/textures/Wallpaper.jpg");
        gamename1 = ImageLoader.loadimage("../res/textures/GameName1.png");
        gamename2 = ImageLoader.loadimage("../res/textures/GameName2.png");

        if(State.changeMusic) {
            if(!silentMusic) {
                State.changeMusic = false;
                if (clip.isRunning())
                    clip.close();
                clip.setFramePosition(0); //rewind to the beginning
                setMenuMusic();
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }
        }

        int DEFAULT_BUTTON_WIDTH = 284;
        int DEFAULT_BUTTON_HEIGHT = 53;

        final int numberOfOptions = 5;
        int[] menuOptions_y = {180,260-4,340-8,420-12,500-16};
        BufferedImage[][] menuOptions = {Assets.btn_start,Assets.btn_story, Assets.btn_settings, Assets.btn_highscores, Assets.btn_exit};
        //IMPORTANT: la  crearea unui buton UIImageButton, se pune level 0 daca nu este nivel
        //sau X pentru Level X. Pentru nivele avem new BufferedImage[3], adica accesibil sau nu.
        //pentru restul optiunilor avem doar 2 imagini (with/without hover)

        //OPTION 1 : START

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[0], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[0], new ClickListener() {
            @Override
            public void onClick() {
                /*handler.getMouseManager().setUiManager(null); //Disable Mouse for UI
                handler.getGame().gameState = new GameState(handler);
                State.setState(handler.getGame().gameState);*/
                handler.getGame().levelState = new LevelState(handler);
                State.setState(handler.getGame().levelState);
            }
        }));

        //OPTION 2 : STORY

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[1], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[1], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().storyState = new StoryState(handler);
                State.setState(handler.getGame().storyState);
            }
        }));

        //OPTION 3 : SETTINGS

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[2], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[2], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().settingsState = new SettingsState(handler);
                State.setState(handler.getGame().settingsState);
            }
        }));

        //OPTION 4 : HIGHSCORES

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[3], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT,menuOptions[3], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().highscoreState = new HighscoreState(handler);
                State.setState(handler.getGame().highscoreState);
            }
        }));

        //OPTION 5 : EXIT

        uiManager.addObject(new UIImageButton(0,(handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH)/2,menuOptions_y[4], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[4], new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
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
