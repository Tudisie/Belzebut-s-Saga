package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyManager implements KeyListener {

    private boolean[] keys,justPressed,cantPress;
    public boolean up,down,left,right,attack,run,L_key,F_key,ENTER_key;

    public boolean key1,key2,key3, CTRL_key, ALT_key,ESC_key;

    public KeyManager(){
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick(){
        for(int i = 0; i < keys.length; ++i){
            if(cantPress[i] && !keys[i])
                cantPress[i] = false;
            else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        attack = keys[KeyEvent.VK_SPACE];
        run = keys[KeyEvent.VK_SHIFT];
        L_key = keys[KeyEvent.VK_L];
        F_key = keys[KeyEvent.VK_F];
        CTRL_key = keys[KeyEvent.VK_CONTROL];
        ALT_key = keys[KeyEvent.VK_ALT];
        key1 = keys[KeyEvent.VK_1];
        key2 = keys[KeyEvent.VK_2];
        key3 = keys[KeyEvent.VK_3];
        ESC_key = keys[KeyEvent.VK_ESCAPE];
        ENTER_key = keys[KeyEvent.VK_ENTER];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }
}
