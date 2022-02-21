package inventory;

import game.Handler;
import gfx.Assets;
import gfx.Text;
import items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    private static Color textcolor = new Color(255,166,77),
                        selectedtextcolor = new Color(255,220,66),
                        zerocolor = new Color(179, 179, 179);
    private static Color auxColor;

    private final int invX = 250, invY = 48,
            invWidth = (int)(502 * 0.75), invHeight = (int)(686 * 0.75),
            invTitleX = invX + invWidth/2, invTitleY = invY + 27,
            invListCenterX = invX + invWidth/2, invListCenterY = invY + 163, invListSpacing = 33,
            invExpX = invX + 260, invExpY = invY + 370, invDmgX = invExpX, invDmgY = invExpY + 25;

    private final int invImageX = invX + 50,invImageY = invY + 343,
                invImageWidth = 65, invImageHeight = 65;

    private final int invCountX = invX + 84, invCountY = invY + 427;

    private int selectedItem = 0;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList();

        //to see all the items in inventary, whether you collected one of its kind or not.
        addItem(Item.flowerItem.createNew(0));
        addItem(Item.mushroomItem.createNew(0));
        addItem(Item.woodItem.createNew(0));
        addItem(Item.plankItem.createNew(0));
        addItem(Item.stoneItem.createNew(0));
        addItem(Item.stoneBlockItem.createNew(0));
        addItem(Item.bronzeItem.createNew(0));
        addItem(Item.bronzeIngotItem.createNew(0));
        addItem(Item.ironItem.createNew(0));
        addItem(Item.ironIngotItem.createNew(0));
        addItem(Item.goldItem.createNew(0));
        addItem(Item.goldIngotItem.createNew(0));
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            if(!handler.getKeyManager().ALT_key)
                active = !active;
        if(!active)
            return;

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
            --selectedItem;
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            ++selectedItem;

        if(selectedItem < 0)
            selectedItem = 0;
        else if(selectedItem >= inventoryItems.size())
            selectedItem = inventoryItems.size() - 1;

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
            inventoryItems.get(selectedItem).craft();

    }

    public void render(Graphics g){
        if(!active)
            return;

        g.drawImage(Assets.inventoryScreen, invX,invY,invWidth,invHeight,null);
        int len = inventoryItems.size();
        if(len == 0)
            return;

        for(int i = -3; i < 4; ++i){
            if(selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if(i == 0){
                Text.drawString(g,"> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
                        invListCenterY + i*invListSpacing, true, selectedtextcolor,Assets.font24P);
            } else{
                if(inventoryItems.get(selectedItem + i).getCount() > 0)
                    auxColor = textcolor;
                else
                    auxColor = zerocolor;
                Text.drawString(g,inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + i*invListSpacing, true, auxColor,Assets.font24P);
            }

        }

        Item item = inventoryItems.get(selectedItem);
        Text.drawString(g,"Inventory", invTitleX,invTitleY,true,textcolor,Assets.font32);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g,Integer.toString(item.getCount()), invCountX, invCountY, true, textcolor, Assets.font20);

        String expText = "Exp: ";
        expText += handler.getWorld().getEntityManager().getPlayer().getExp() + "/" + handler.getWorld().getExpToNextLevel();
        Text.drawString(g,expText,invExpX,invExpY,true,textcolor,Assets.font16);

        String dmgText = "Dmg: ";
        dmgText += handler.getWorld().getEntityManager().getPlayer().getDamage();
        Text.drawString(g,dmgText,invDmgX,invDmgY,true,textcolor,Assets.font16);

        Text.drawString(g,getCraftingInfo(inventoryItems.get(selectedItem).getName()),invX + 40, invY + invHeight - 52, false,textcolor,Assets.font16);
    }

    //Inventory methods

    public String getCraftingInfo(String str){
        String s;
        switch(str){
            case "Wood":
                s = "8 Wood -> 1 Plank";
                break;
            case "Plank":
                s = "10 Plank -> Wooden Sword";
                break;
            case "Rock":
                s = "7 Rock -> 1 Stone";
                break;
            case "Stone":
                s = "6 Stones -> Stone Sword";
                break;
            case "Bronze":
                s = "12 Bronze -> 1 Bronze Ingot";
                break;
            case "Bronze Ingot":
                s = "4 Bronze Ingots -> Bronze Sword";
                break;
            case "Iron":
                s = "6 Iron -> 1 Iron Ingot";
                break;
            case "Iron Ingot":
                s = "5 Iron Ingots -> Iron Sword";
                break;
            case "Gold":
                s = "8 Gold -> 1 Gold Ingot";
                break;
            case "Gold Ingot":
                s = "5 Gold Ingots -> Golden Sword";
                break;
            case "Mushroom":
                s = "3 Mushrooms -> + 1 HP";
                break;
            case "Flower":
                s = "10 Flowers -> + 0.5 HP";
                break;
            default:
                s = "No crafting option";
                break;
        }
        return s;
    }

    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public Handler getHandler(){
        return handler;
    }

    public boolean isActive(){
        return active;
    }
}
