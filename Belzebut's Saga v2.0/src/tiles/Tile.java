package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF
    public static int first_tufa_id = 10;

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile grass2Tile = new Grass2Tile(1);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile stoneTile = new RockTile(3);
    public static Tile sandTile = new SandTile(4);
    public static Tile stoneRoadTile = new StoneRoadTile(5);
    public static Tile stoneRoadShadeTile = new StoneRoadShadeTile(6);
    public static Tile woodRoadTile = new WoodRoadTile(7);
    public static Tile waterTile = new WaterTile(8);
    public static Tile lavaTile = new LavaTile(9);

    public static Tile tufa1 = new tufaTile(10);
    public static Tile tufa2 = new tufaTile(11);
    public static Tile tufa3 = new tufaTile(12);
    public static Tile tufa4 = new tufaTile(13);
    public static Tile tufa5 = new tufaTile(14);
    public static Tile tufa6 = new tufaTile(15);
    public static Tile tufa7 = new tufaTile(16);
    public static Tile tufa8 = new tufaTile(17);
    public static Tile tufa9 = new tufaTile(18);
    public static Tile tufa10 = new tufaTile(19);
    //CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture,int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g,int x, int y){
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }
}
