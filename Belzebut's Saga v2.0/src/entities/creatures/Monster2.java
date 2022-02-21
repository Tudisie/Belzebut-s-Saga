package entities.creatures;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Monster2 extends Monsters{

    public Monster2(Handler handler, float x, float y) {
        super(handler, x, y);
        maxHealth = health = 12;
        damage = 1;
        range = 300;
        setSpeed(1.7f);

        animDown = new Animation(150, Assets.monsters_down[1]);
        animUp = new Animation(150, Assets.monsters_up[1]);
        animLeft = new Animation(150, Assets.monsters_left[1]);
        animRight = new Animation(150, Assets.monsters_right[1]);
        animIdle = new Animation(150, Assets.monsters_idle[1]);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().addExp(15);
    }
}
