package entities.statics;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class Rocks2 extends StaticEntity {

    public Rocks2(Handler handler, float x, float y){
        super(handler,x,y, 64,64);
        height = -1000; //tiganeala: entitatea aceasta trebuie sa fie randata mereu inaintea playerului
        //Comparatorul meu verifica y + height la fiecare entitate. Am setat -1000 ca sa fie randata inaintea playerlui
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g){
        g.drawImage(Assets.rocks2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),64,64,null);
    }
}
