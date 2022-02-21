package entities.creatures;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Monster4 extends Monsters{

    public Monster4(Handler handler, float x, float y) {
        super(handler, x, y);
        maxHealth = health = 25;
        damage = 2;
        range = 300;
        setSpeed(1.2f);

        animDown = new Animation(150, Assets.monsters_down[3]);
        animUp = new Animation(150, Assets.monsters_up[3]);
        animLeft = new Animation(150, Assets.monsters_left[3]);
        animRight = new Animation(150, Assets.monsters_right[3]);
        animIdle = new Animation(150, Assets.monsters_idle[3]);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().addExp(10);
    }
}
