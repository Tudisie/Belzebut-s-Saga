package entities.creatures;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

public class Monster5 extends Monsters{

    public Monster5(Handler handler, float x, float y) {
        super(handler, x, y);
        maxHealth = health = 40;
        damage = 2;
        range = 450;
        setSpeed(1.2f);

        animDown = new Animation(150, Assets.monsters_down[4]);
        animUp = new Animation(150, Assets.monsters_up[4]);
        animLeft = new Animation(150, Assets.monsters_left[4]);
        animRight = new Animation(150, Assets.monsters_right[4]);
        animIdle = new Animation(150, Assets.monsters_idle[4]);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().addExp(20);
    }
}
