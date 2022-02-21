package entities;

import entities.creatures.Player;
import game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    private Entity entityToAdd;

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive())
                it.remove();
        }
        if(entityToAdd != null)
            entities.add(entityToAdd);
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(int i = 0; i < entities.size(); ++i){
            Entity e = entities.get(i);
            e.render(g);
            e.renderHealth(g);
        }
        player.postRender(g);
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public Handler getHandler(){
        return handler;
    }
    public Player getPlayer(){
        return player;
    }
    public void setEntityToAdd(Entity e){ entityToAdd = e;}
}
