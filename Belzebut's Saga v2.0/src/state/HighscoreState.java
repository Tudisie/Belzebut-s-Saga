package state;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import Database.DatabaseOperations;
import Database.Score;
import game.Handler;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.Text;

public class HighscoreState extends State {

    private final UIManager uiManager;
    private final BufferedImage wallpaper;

    private static ArrayList<Score> scores_lvl1 = new ArrayList<>();
    private static ArrayList<Score> scores_lvl2 = new ArrayList<>();
    private static ArrayList<Score> scores_lvl3 = new ArrayList<>();

    private static Color textcolor = new Color(255,189,123);


    public HighscoreState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        wallpaper = ImageLoader.loadimage("../res/textures/WallpaperDarker.jpg");

        int DEFAULT_BUTTON_WIDTH = 284;
        int DEFAULT_BUTTON_HEIGHT = 53;

        final int numberOfOptions = 1;
        int[] menuOptions_y = {525};
        BufferedImage[][] menuOptions = {Assets.btn_back};

        DatabaseOperations.getTable("Level1");
        DatabaseOperations.getTable("Level2");
        DatabaseOperations.getTable("Level3");

        //BACK

        uiManager.addObject(new UIImageButton(0, (handler.getGame().getWidth() - DEFAULT_BUTTON_WIDTH) / 2, menuOptions_y[0], DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, menuOptions[0], new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().menuState = new MenuState(handler);
                State.setState(handler.getGame().menuState);
            }
        }));
    }

    public void tick() {
        uiManager.tick();
    }

    public void render(Graphics g) {
        g.drawImage(wallpaper, 0, 0, null);

        Text.drawString(g,"Highscores",handler.getGame().getWidth()/2,50,true,textcolor,Assets.fontHighscores);

        Text.drawString(g,"Level 1",60,120,false,textcolor,Assets.fontHighscores2);
        for (int i = 0; i < scores_lvl1.size() && i < 10; ++i)
            Text.drawString(g, scores_lvl1.get(i).toString(), 50, 160 + 35 * i, false, Color.WHITE, Assets.font24);

        Text.drawString(g,"Level 2",handler.getGame().getWidth()/2 - 60,120,false,textcolor,Assets.fontHighscores2);
        for (int i = 0; i < scores_lvl2.size() && i < 10; ++i)
            Text.drawString(g, scores_lvl2.get(i).toString(), handler.getGame().getWidth()/2 - 60, 160 + 35 * i, false, Color.WHITE, Assets.font24);

        Text.drawString(g,"Level 3",handler.getGame().getWidth() - 160,120,false,textcolor,Assets.fontHighscores2);
        for (int i = 0; i < scores_lvl3.size() && i < 10; ++i)
            Text.drawString(g, scores_lvl3.get(i).toString(), handler.getGame().getWidth() - 160, 160 + 35 * i, false, Color.WHITE, Assets.font24);

        uiManager.render(g);
    }

    public static void clearScores(int level) {
        if (level == 1)
            scores_lvl1 = new ArrayList<>();
        else if (level == 2)
            scores_lvl2 = new ArrayList<>();
        else if (level == 3)
            scores_lvl3 = new ArrayList<>();
    }

    public static void addScore(ArrayList scrs, Score scr) {
        scrs.add(scr);
    }

    public static ArrayList<Score> getScores(int level){
        if(level == 2)
            return scores_lvl2;
        else if(level == 3)
            return scores_lvl3;
        return scores_lvl1;
    }
}