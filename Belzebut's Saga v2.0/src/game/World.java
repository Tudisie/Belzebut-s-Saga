package game;

import Database.DatabaseOperations;
import entities.Entity;
import entities.EntityManager;
import entities.creatures.Player;
import items.ItemManager;
import state.LevelState;
import state.MenuState;
import state.State;
import tiles.Tile;
import utils.Utils;

import java.awt.Graphics;

public class World {
    private Handler handler;

    private int width,height;
    private int spawnX,spawnY;
    private boolean creativeMode = false; //enabled/disabled by (CTRL + 1 + 0)
    private int expToNextLevel;
    private int[][] tiles;

    //Entities
    private EntityManager entityManager;
    //Items
    private ItemManager itemManager;

    public String pathEntitiesFile;

    public World(Handler handler,String pathW, String pathE){
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player(handler,100,100));
        itemManager = new ItemManager(handler);

        pathEntitiesFile = pathE;

        loadEntities(pathE);
        loadWorld(pathW);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart =(int) Math.max(0,handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width,(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0,handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height,(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; ++y)
            for(int x = xStart; x < xEnd; ++x){
                getTile(x,y).render(g,(int)(x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int)(y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }

        //Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.grassTile; //default tile
        }
        return t;
    }

    private void loadEntities(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        for(int i = 0; i < tokens.length; i+=3){
            String entityName = tokens[i];
            int x = Utils.parseInt(tokens[i+1]);
            int y = Utils.parseInt(tokens[i+2]);

            entityManager.addEntity(Entity.getEntity(entityName,handler, x,y));
        }
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        expToNextLevel = Utils.parseInt(tokens[4]);

        tiles = new int [width][height];
        for(int y = 0; y < height; ++y)
            for(int x = 0; x < width; ++x){
                tiles[x][y] = Utils.parseInt(tokens[x + y*width + 5]);
            }
    }

    public void finishLevel(){
        //load score into database

        String tableName = "Level" + handler.getGame().currentLevel;
        DatabaseOperations.insertRecord(tableName,handler.getGame().getName(),handler.getWorld().getEntityManager().getPlayer().getExp());

        //update unlocked levels
        if(handler.getGame().numberOfLevels > handler.getGame().currentLevel) {
            handler.getGame().setUnlockedLevels(handler.getGame().currentLevel + 1);

            handler.getGame().levelState = new LevelState(handler);
            State.setState(handler.getGame().levelState);

        } else{ //finished all levels -> go to main menu
            handler.getGame().menuState = new MenuState(handler);
            State.setState(handler.getGame().menuState);
        }
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
    public ItemManager getItemManager(){
        return itemManager;
    }
    public boolean isCreativeMode(){ return creativeMode;}
    public void setCreativeMode(boolean opt){ creativeMode = opt;}
    public int getExpToNextLevel(){ return expToNextLevel;}
}
