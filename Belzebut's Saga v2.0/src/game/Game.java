package game;

import Database.InsertNameBox;
import display.Display;
import gfx.Assets;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import state.*;
import utils.Utils;

import javax.swing.*;

public class Game implements Runnable {
    private Display display;

    private static Game instance = null;

    private final int width,height;
    public String title;
    private int unlockedLevels;
    public final int numberOfLevels = 3;
    public int currentLevel;
    private String name = "Tudisie"; //by default

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States

    public State gameState,menuState,storyState,levelState,settingsState,highscoreState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title,int width,int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    public void init(){
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager );
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        State.init();

        loadSaves("src/res/worlds/saves.txt");

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        Game thisGame = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InsertNameBox(thisGame); //Database.InsertNameBox
            }
        });

        menuState = new MenuState(handler);
        State.setState(menuState); //MenuState
    }

    public void loadSaves(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        unlockedLevels = Utils.parseInt(tokens[0]);
        System.out.println("Levels unlocked: " + unlockedLevels);
    }

    //UPDATES game.Game
    private void tick(){
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen

        g.clearRect(0,0,width,height);

        //Start Drawing

        if(State.getState() != null)
            State.getState().render(g);

        //End Drawing

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        final int fps = 75;
        double timePerTick = 1000000000/fps; //[ns] / frame
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); //Actual time
        long timer = 0;
        int ticks = 0;

        while(running){
            //timer si ticks verifica cate frameuri sunt afisate intr-o secunda
            now = System.nanoTime();
            delta += (now-lastTime)/timePerTick;
            //timer += now - lastTime;
            lastTime = now;

            if(delta >=1) {
                tick();
                render();

                //++ticks;
                --delta;
            }
            /*if(timer >= 1000000000){ // timer > 1second -> reset t
                ticks = 0;
                timer = 0;
            }*/
        }
        stop();
    }

    //Design Pattern: SingleTon
    public static Game getInstance(){
        if(instance == null){
            instance = new Game("Belzebut's Saga",800,600);
        }
        return instance;
    }

    public static void Reset(){
        instance = null;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public synchronized void start(){
        running = true;
        thread = new Thread(this);
        thread.start(); // call run()
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setRunning(boolean opt){
        running = opt;
    }
    public int getUnlockedLevels(){ return unlockedLevels;}
    public void setUnlockedLevels(int lvls){unlockedLevels = lvls;}
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
