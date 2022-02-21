package entities.creatures;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Monster3 extends Monsters{

    public Monster3(Handler handler, float x, float y) {
        super(handler, x, y);
        maxHealth = health = 20;
        damage = 2;
        range = 450;
        setSpeed(1.8f);

        animDown = new Animation(150, Assets.monsters_down[2]);
        animUp = new Animation(150, Assets.monsters_up[2]);
        animLeft = new Animation(150, Assets.monsters_left[2]);
        animRight = new Animation(150, Assets.monsters_right[2]);
        animIdle = new Animation(150, Assets.monsters_idle[2]);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().addExp(20);
    }
}
