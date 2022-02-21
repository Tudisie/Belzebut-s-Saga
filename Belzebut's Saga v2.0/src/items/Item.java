package items;

import game.Handler;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    //Handler
    public static Handler handlerManager;

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood_item, "Wood",0);
    public static Item plankItem = new Item(Assets.plank_item, "Plank",1);
    public static Item stoneItem = new Item(Assets.stone_item, "Rock",2);
    public static Item stoneBlockItem = new Item(Assets.stone_block, "Stone",3);
    public static Item bronzeItem = new Item(Assets.bronze_item, "Bronze",4);
    public static Item bronzeIngotItem = new Item(Assets.bronze_ingot, "Bronze Ingot",5);
    public static Item ironItem = new Item(Assets.iron_item, "Iron",6);
    public static Item ironIngotItem = new Item(Assets.iron_ingot, "Iron Ingot",7);
    public static Item goldItem = new Item(Assets.gold_item, "Gold",8);
    public static Item goldIngotItem = new Item(Assets.gold_ingot, "Gold Ingot",9);

    public static Item mushroomItem = new Item(Assets.mushroom2, "Mushroom",10);
    public static Item flowerItem = new Item(Assets.flower_item, "Flower",11);

    //Class

    public static final int ITEMWIDTH = 64, ITEMHEIGHT = 64;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x,y,count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x,y,ITEMWIDTH,ITEMHEIGHT);

        items[id] = this;
    }

    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g){ // map
        if(handler == null)
            return;
        render(g,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g,int x,int y){ //inventory
        g.drawImage(texture,x,y,ITEMWIDTH,ITEMHEIGHT,null);
    }

    public void craft(){
        switch(name){
            case "Flower":
                if(count >= 10){
                    if(handlerManager.getWorld().getEntityManager().getPlayer().addHealth(1))
                        count -=10;
                }
                break;
            case "Mushroom":
                if(count >= 3){
                    if(handlerManager.getWorld().getEntityManager().getPlayer().addHealth(2))
                        count -=3;
                }
                break;
            case "Wood":
                if(count >= 8){
                    count -=8;
                    handlerManager.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.plankItem.createNew(1));
                }
                break;
            case "Plank":
                if(count >= 10 && !handlerManager.getWorld().getEntityManager().getPlayer().woodenSword){
                    count -=10;
                    handlerManager.getWorld().getEntityManager().getPlayer().setDamage(2);
                    System.out.println("Unlocked: Wooden Sword");
                    handlerManager.getWorld().getEntityManager().getPlayer().woodenSword = true;
                }
                break;
            case "Rock":
                if(count >= 7){
                    count -=7;
                    handlerManager.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.stoneBlockItem.createNew(1));
                }
                break;
            case "Stone":
                if(count >= 6 && handlerManager.getWorld().getEntityManager().getPlayer().woodenSword && !handlerManager.getWorld().getEntityManager().getPlayer().stoneSword){
                    count -=6;
                    handlerManager.getWorld().getEntityManager().getPlayer().setDamage(3);
                    System.out.println("Unlocked: Stone Sword");
                    handlerManager.getWorld().getEntityManager().getPlayer().stoneSword = true;
                }
                break;
            case "Bronze":
                if(count >= 12){
                    count -=12;
                    handlerManager.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.bronzeIngotItem.createNew(1));
                }
                break;
            case "Bronze Ingot":
                if(count >= 4 && handlerManager.getWorld().getEntityManager().getPlayer().stoneSword && !handlerManager.getWorld().getEntityManager().getPlayer().bronzeSword){
                    count -=4;
                    handlerManager.getWorld().getEntityManager().getPlayer().setDamage(4);
                    System.out.println("Unlocked: Bronze Sword");
                    handlerManager.getWorld().getEntityManager().getPlayer().bronzeSword = true;
                }
                break;
            case "Iron":
                if(count >= 6){
                    count -=6;
                    handlerManager.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.ironIngotItem.createNew(1));
                }
                break;
            case "Iron Ingot":
                if(count >= 5 && handlerManager.getWorld().getEntityManager().getPlayer().bronzeSword && !handlerManager.getWorld().getEntityManager().getPlayer().ironSword){
                    count -=5;
                    handlerManager.getWorld().getEntityManager().getPlayer().setDamage(6);
                    System.out.println("Unlocked: Iron Sword");
                    handlerManager.getWorld().getEntityManager().getPlayer().ironSword = true;
                }
                break;
            case "Gold":
                if(count >= 8){
                    count -=8;
                    handlerManager.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.goldIngotItem.createNew(1));
                }
                break;
            case "Gold Ingot":
                if(count >= 5 && handlerManager.getWorld().getEntityManager().getPlayer().ironSword && !handlerManager.getWorld().getEntityManager().getPlayer().goldSword){
                    count -=5;
                    handlerManager.getWorld().getEntityManager().getPlayer().setDamage(9);
                    System.out.println("Unlocked: Gold Sword");
                    handlerManager.getWorld().getEntityManager().getPlayer().goldSword = true;
                }
                break;
            default:
                break;
        }
    }

    public Item createNew(int x,int y){
        Item i = new Item(texture,name,id);
        i.setPosition(x,y);
        return i;
    }

    public Item createNew(int count){
        Item i = new Item(texture,name,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public int getCount(){
        return count;
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public int getId(){
        return id;
    }
    public void setCount(int count){
        this.count = count;
    }
    public String getName(){
        return name;
    }
    public void setPickedUp(boolean opt){
        pickedUp = opt;
    }
    public BufferedImage getTexture(){
        return texture;
    }
    public boolean isPickedUp(){
        return pickedUp;
    }
}
