package items;

import game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        Item.handlerManager = handler;
        items = new ArrayList();
    }

    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.isPickedUp()) { //&& handler.getKeyManager().keyJustPressed(KeyEvent.VK_F) // if you want to pick up manually the items
                it.remove();
                return;
            }
        }
    }

    public void render(Graphics g){
        for(Item i : items)
            i.render(g);
    }

    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }
}
