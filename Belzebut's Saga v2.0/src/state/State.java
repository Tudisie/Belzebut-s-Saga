package state;

import java.awt.Graphics;
import java.util.Objects;

import game.Handler;

import javax.sound.sampled.*;

public abstract class State {
    private static State currentState = null;
    private static AudioInputStream inputStreamMenu,inputStreamGame;
    protected static Clip clip;
    public static boolean changeMusic = true; // YES from game, NO from  Story / Settings
    public static boolean silentMusic = false;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    //CLASS

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public static void init(){ //Music
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void setMenuMusic(){
        try {
            inputStreamMenu = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(State.class.getResourceAsStream("../res/sounds/menuMusic.wav")));
            clip.open(inputStreamMenu);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void setGameMusic(){
        try {
            inputStreamMenu = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(State.class.getResourceAsStream("../res/sounds/gameMusic.wav")));
            clip.open(inputStreamMenu);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
