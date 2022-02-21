package entities.creatures;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Monster1 extends Monsters{

    public Monster1(Handler handler, float x, float y) {
        super(handler, x, y);
        maxHealth = health = 8;
        damage = 1;
        range = 300;
        setSpeed(1.7f);

        animDown = new Animation(150, Assets.monsters_down[0]);
        animUp = new Animation(150, Assets.monsters_up[0]);
        animLeft = new Animation(150, Assets.monsters_left[0]);
        animRight = new Animation(150, Assets.monsters_right[0]);
        animIdle = new Animation(150, Assets.monsters_idle[0]);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().addExp(10);
    }

}
