package state;

import java.awt.Graphics;
import java.io.FileNotFoundException;

import game.Handler;
import game.World;

import javax.sound.sampled.AudioInputStream;

public class GameState extends State{

    private World world;

    public GameState(Handler handler, int level) {
        super(handler);

        world = new World(handler,"src/res/worlds/World" + level + ".txt", "src/res/worlds/Entities" + level + ".txt");
        handler.setWorld(world);

        if(!silentMusic) {
            if (clip.isRunning())
                clip.close();
            clip.setFramePosition(0); //rewind to the beginning
            setGameMusic();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void tick(){
        world.tick();
    }

    @Override
    public void render(Graphics g){
        world.render(g);
    }
}
