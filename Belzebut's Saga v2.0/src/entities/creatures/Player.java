package entities.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import entities.Entity;
import entities.statics.StaticEntity;
import gfx.Animation;
import gfx.Assets;
import game.Handler;
import inventory.Inventory;
import items.Item;
import state.MenuState;
import state.State;



public class Player extends Creature{

    //Animations
    private final Animation animRight,animLeft,animUp,animDown,animIdleRight,animIdleLeft,animAttackRight,animAttackLeft,animWalkAttackRight,animWalkAttackLeft,animRunRight,animRunLeft,animWin,animDamageLeft,animDamageRight,animDeathLeft,animDeathRight;
    private int timer_for_win_animation,timer_for_damage_animation,timer_for_death_animation;

    protected boolean lastDir; //false = onX, true = onY;
    protected boolean lastXDir; //false = left; true = right;
    protected boolean lastYDir; //false = up; true = down;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;
    private String entityNameSelected = "Tree1"; //in creative mode
    private int exp; //experience to level up
    public final int MAX_HEALTH = 12;
    public boolean woodenSword,stoneSword,bronzeSword,ironSword,goldSword; //basicSword in the beginning
    private boolean flyMode = false;

    public Player(Handler handler,float x, float y) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 60;
        bounds.y = 80;
        bounds.width = 37;
        bounds.height = 35;
        woodenSword = stoneSword = bronzeSword = ironSword = goldSword = false;

        setHealth(6); //2 per heart
        damage = 1;
        exp = 0;

        //Animations
        animRight = new Animation(100,Assets.player_right);
        animLeft = new Animation(100,Assets.player_left);
        animUp = new Animation(150,Assets.player_up);
        animDown = new Animation(120,Assets.player_down);
        animIdleRight = new Animation(100,Assets.player_idle_right);
        animIdleLeft = new Animation(100,Assets.player_idle_left);
        animAttackRight = new Animation(100,Assets.player_attack_right);
        animAttackLeft = new Animation(100,Assets.player_attack_left);
        animWalkAttackRight = new Animation(100,Assets.player_walk_attack_right);
        animWalkAttackLeft = new Animation(100,Assets.player_walk_attack_left);
        animRunRight = new Animation(90,Assets.player_run_right);
        animRunLeft = new Animation(90,Assets.player_run_left);
        animWin = new Animation(120,Assets.player_win);
        animDamageLeft = new Animation(150,Assets.player_damage_left);
        animDamageRight = new Animation(120,Assets.player_damage_right);
        animDeathLeft = new Animation(165,Assets.player_death_left);
        animDeathRight = new Animation(165,Assets.player_death_right);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animations
        animRight.tick();
        animLeft.tick();
        animUp.tick();
        animDown.tick();
        animAttackRight.tick();
        animAttackLeft.tick();
        animWalkAttackLeft.tick();
        animWalkAttackRight.tick();
        animRunLeft.tick();
        animRunRight.tick();
        if(timer_for_win_animation > 0){
            animWin.tick();
            --timer_for_win_animation;
        }

        if(timer_for_damage_animation > 0){
            animDamageLeft.tick();
            animDamageRight.tick();
            --timer_for_damage_animation;
        }
        if(timer_for_death_animation > 0){
            animDeathLeft.tick();
            animDeathRight.tick();
            --timer_for_death_animation;
            if(timer_for_death_animation == 0){ //here it goes to menu state
                active = false;
                State.changeMusic = true;
                handler.getGame().menuState = new MenuState(handler);
                State.setState(handler.getGame().menuState);
            }
        }


        //Movement
        getInput();
        if(!isFlyMode())
            move(); //Creatures.java
        else{
            x += xMove;
            y += yMove;
        }
        handler.getGameCamera().centerOnEntity(this);

        //Attack
        if(!run)
            checkAttacks();

        inventory.tick();
    }

    private void checkAttacks(){
        if(inventory.isActive())
            return;

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        attackTimer = 0;

        Rectangle colbounds = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 30;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().attack){
            if(handler.getKeyManager().right){
                ar.x = colbounds.x + colbounds.width;
                ar.y = colbounds.y + colbounds.height/2 - arSize/2;
            }else if(handler.getKeyManager().left){
                ar.x = colbounds.x - arSize;
                ar.y = colbounds.y + colbounds.height/2 - arSize / 2;
            }else if(handler.getKeyManager().up){
                ar.x = colbounds.x + colbounds.width/2 - arSize/2;
                ar.y = colbounds.y - arSize;
            }else if(handler.getKeyManager().down){
                ar.x = colbounds.x + colbounds.width/2 - arSize/2;
                ar.y = colbounds.y + colbounds.height;
            }else if(lastXDir == true){ //right
                ar.x = colbounds.x + colbounds.width;
                ar.y = colbounds.y + colbounds.height/2 - arSize/2;
            }
            else{ //lastXDir = false : left
                ar.x = colbounds.x - arSize;
                ar.y = colbounds.y + colbounds.height/2 - arSize / 2;
            }


            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0,0).intersects(ar)){
                    e.hurt(damage);
                    return;
                }
            }
        }


    }

    @Override
    public void die(){
        if(active)
            timer_for_death_animation = 100;
    }

    public void hurt(int amt){
        health -= amt;
        if(health <= 0) {
            die();
        }else
            timer_for_damage_animation = 35;
        animDamageLeft.reset();
        animDamageRight.reset();
    }

    private void getInput(){
        xMove = yMove = 0;

        if(inventory.isActive())
            return;

        attack = run = false;
        if(isFlyMode())
            this.setSpeed(3.0f);
        else
            this.setSpeed(DEFAULT_SPEED);
        animUp.setSpeed(150);
        animDown.setSpeed(120);

        if(timer_for_damage_animation > 0) {
            if(lastDir == false) {
                if (lastXDir == false) {
                    xMove = speed;
                } else {
                    xMove = -speed;
                }
            }else{
                if(lastYDir == false)
                    yMove = speed;
                else
                    yMove = -speed;
            }
            return;
        }

        if(timer_for_death_animation > 0)
            return;

        if(handler.getKeyManager().ESC_key){
            State.changeMusic = true;
            handler.getGame().menuState = new MenuState(handler);
            State.setState(handler.getGame().menuState);
        }

        if(handler.getKeyManager().CTRL_key){ //Creative Mode
            if(handler.getKeyManager().key1)
                if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)) {
                    handler.getWorld().setCreativeMode(!handler.getWorld().isCreativeMode());

                    if(handler.getWorld().isCreativeMode())
                        System.out.println("Creative mode: enabled");
                    else
                        System.out.println("Creative mode: disabled");
                }
            if(handler.getWorld().isCreativeMode()){
                if(handler.getKeyManager().key2)
                    ++exp;
                if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)){
                    inventory.addItem(Item.woodItem.createNew(99));
                    inventory.addItem(Item.plankItem.createNew(99));
                    inventory.addItem(Item.stoneItem.createNew(99));
                    inventory.addItem(Item.stoneBlockItem.createNew(99));
                    inventory.addItem(Item.ironItem.createNew(99));
                    inventory.addItem(Item.ironIngotItem.createNew(99));
                    inventory.addItem(Item.goldItem.createNew(99));
                    inventory.addItem(Item.goldIngotItem.createNew(99));
                    inventory.addItem(Item.mushroomItem.createNew(99));
                    inventory.addItem(Item.flowerItem.createNew(99));
                    inventory.addItem(Item.bronzeItem.createNew(99));
                    inventory.addItem(Item.bronzeIngotItem.createNew(99));
                }
                if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)){
                    if(isFlyMode())
                        System.out.println("Fly Mode: Disabled");
                    else
                        System.out.println("Fly Mode: Enabled");

                    setFlyMode(!isFlyMode());

                }
            }
        }

        if(handler.getWorld().isCreativeMode()) {

            if(handler.getKeyManager().ALT_key){
                boolean selected = false;

                if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) { entityNameSelected = "Tree1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) { entityNameSelected = "Tree2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) { entityNameSelected = "Tree3";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) { entityNameSelected = "Tree4";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) { entityNameSelected = "Arbust";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6)) { entityNameSelected = "Stone1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7)) { entityNameSelected = "Bronze";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8)) { entityNameSelected = "Iron1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9)) { entityNameSelected = "Iron2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)) { entityNameSelected = "Gold1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) { entityNameSelected = "Gold2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) { entityNameSelected = "fireTrap";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) { entityNameSelected = "Cactus1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) { entityNameSelected = "Cactus2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) { entityNameSelected = "Flower_Blue";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) { entityNameSelected = "Flower_Orange";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_U)) { entityNameSelected = "Flower_Yellow";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) { entityNameSelected = "Mushroom1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_O)) { entityNameSelected = "Mushroom2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) { entityNameSelected = "Rocks1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) { entityNameSelected = "Rocks2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) { entityNameSelected = "Pillar1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) { entityNameSelected = "Pillar2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) { entityNameSelected = "Statue1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)) { entityNameSelected = "Statue2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)) { entityNameSelected = "Statue3";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_J)) { entityNameSelected = "Angel1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_K)) { entityNameSelected = "Angel2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) { entityNameSelected = "PortalStart";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_X)) { entityNameSelected = "PortalEnd";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) { entityNameSelected = "Monster1";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_V)) { entityNameSelected = "Monster2";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_B)) { entityNameSelected = "Monster3";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) { entityNameSelected = "Monster4";selected = true; }
                else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)) { entityNameSelected = "Monster5";selected = true; }

                if(selected) {
                    System.out.println(entityNameSelected);
                }
                return;
            }

            handler.getWorld().getEntityManager().setEntityToAdd(null);
            if(handler.getMouseManager().isLeftPressed()){
                int mx = handler.getMouseManager().getMouseX();
                int my = handler.getMouseManager().getMouseY();
                handler.getMouseManager().setLeftPressed(false);
                //The offset of camera
                mx += (int)handler.getGame().getGameCamera().getxOffset();
                my +=(int)handler.getGame().getGameCamera().getyOffset();


                //Add on map
                handler.getWorld().getEntityManager().setEntityToAdd(StaticEntity.getEntity(entityNameSelected,handler, mx,my));
                //Add on file
                try {
                    File file = new File(handler.getWorld().pathEntitiesFile);
                    FileWriter fr = new FileWriter(file,true);
                    fr.write("\n" + entityNameSelected + " " + mx + " " + my);
                    fr.close();
                }catch(IOException e){
                    System.out.println(e);
                }
            }
        }

        if(handler.getKeyManager().L_key && timer_for_win_animation <= 0) {
            animWin.reset();
            timer_for_win_animation = 100;
        }
        if(timer_for_win_animation > 0)
            return;

        if(handler.getKeyManager().run){
            run = true;
            if(isFlyMode())
                this.setSpeed(7.0f);
            else
                this.setSpeed(3.0f);
            animUp.setSpeed(80);
            animDown.setSpeed(70);
        }

        if(handler.getKeyManager().up){
            lastYDir = false;
            lastDir = true;
            yMove = -speed;
        }
        if(handler.getKeyManager().down){
            lastYDir = true;
            lastDir = true;
            yMove = speed;
        }
        if(handler.getKeyManager().left) {
            xMove = -speed;
            lastXDir = false; //left
            lastDir = false; //onX
        }
        if(handler.getKeyManager().right) {
            xMove = speed;
            lastXDir = true; //right
            lastDir = false; //onX
        }
        if(handler.getKeyManager().attack){
                attack = true;
        }
    }


    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width,height,null);
        //g.setColor(Color.red);
        //g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
        //        (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
        //        bounds.width,bounds.height);
    }

    public void postRender(Graphics g){
        //HUD
        //HP
        for(int i = 0; i < health/2; ++i)
            g.drawImage(Assets.heart,20 + (Assets.heart.getWidth()+10) * i,handler.getGame().getHeight() - Assets.heart.getHeight() - 10,null);
        if(health % 2 == 1)
            g.drawImage(Assets.halfheart, 20 + (Assets.heart.getWidth()+10) * (health/2), handler.getGame().getHeight() - Assets.heart.getHeight() - 10,null);

        //EXP
        int xwid = Assets.expbar.getWidth();
        int xhei = Assets.expbar.getHeight();
        int xbar = handler.getGame().getWidth() - xwid - 20;
        int ybar = handler.getGame().getHeight() - xhei - 10;
        double percent = (double)handler.getWorld().getEntityManager().getPlayer().getExp() / handler.getWorld().getExpToNextLevel();
        g.drawImage(Assets.expbarFilled,xbar,ybar,(int)(xbar + (double)xwid * percent),ybar + xhei,0,0,(int)((double)xwid*percent),xhei,null);
        g.drawImage(Assets.expbar, xbar,ybar,null);

        //INVENTORY
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame(){

        if(run == true && (yMove != 0 || xMove != 0)){
            if(xMove > 0)
                return animRunRight.getCurrentFrame();
            else if(xMove < 0)
                return animRunLeft.getCurrentFrame();
        }
        else if(attack == true){
            if(xMove == 0 && yMove == 0){
                if(lastXDir == false)
                    return animAttackLeft.getCurrentFrame();
                else
                    return animAttackRight.getCurrentFrame();
            }
            else if(lastXDir == true)
                return animWalkAttackRight.getCurrentFrame();
            else
                return animWalkAttackLeft.getCurrentFrame();

        }

        if(timer_for_death_animation > 0){
            if(lastXDir == false)
                return animDeathLeft.getCurrentFrame();
            else
                return animDeathRight.getCurrentFrame();
        }

        if(timer_for_damage_animation > 0){
            if (lastXDir == false)
                return animDamageLeft.getCurrentFrame();
            return animDamageRight.getCurrentFrame();

        }

        if(xMove < 0)
            return animLeft.getCurrentFrame();
        else if(xMove > 0)
            return animRight.getCurrentFrame();
        else if(yMove < 0)
            return animUp.getCurrentFrame();
        else if( yMove > 0)
            return animDown.getCurrentFrame();
        else if(timer_for_win_animation > 0)
            return animWin.getCurrentFrame();
        else if(lastXDir == false)
            return animIdleLeft.getCurrentFrame();
        else
            return animIdleRight.getCurrentFrame();
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void addExp(int exp){
        this.exp +=exp;
    }

    public int getExp(){
        return exp;
    }

    public boolean addHealth(int h){
        if(health == MAX_HEALTH)
            return false;
        health += h;
        if(health > MAX_HEALTH)
            health = MAX_HEALTH;
        return true;
    }

    public void setFlyMode(boolean opt){
        flyMode = opt;
    }
    public boolean isFlyMode(){
        return flyMode;
    }
}
