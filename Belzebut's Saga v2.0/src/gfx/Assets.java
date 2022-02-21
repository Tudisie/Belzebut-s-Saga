package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

public class Assets {

    public static Font font24,font24P,font20,font16,font32,fontHighscores,fontHighscores2;
    public static int numberOfMonsters = 6;

    public static BufferedImage  grass,grass2,dirt,stone,sand,stoneroad,stoneroadshade,woodroad,water,lava;
    public static BufferedImage[] tufa = new BufferedImage[10];

    public static BufferedImage[] player_right,player_left,player_up,player_down;
    public static BufferedImage[] player_idle_right,player_idle_left;
    public static BufferedImage[] player_attack_right,player_attack_left;
    public static BufferedImage[] player_walk_attack_right, player_walk_attack_left;
    public static BufferedImage[] player_run_right,player_run_left;
    public static BufferedImage[] player_win;
    public static BufferedImage[] player_damage_left,player_damage_right;
    public static BufferedImage[] player_death_left,player_death_right;

    public static BufferedImage[][] monsters_down,monsters_up,monsters_left,monsters_right,monsters_idle;

    public static BufferedImage arbust,mushroom1,mushroom2,flower_orange,flower_blue,flower_yellow;
    public static BufferedImage tree1,tree2,tree3,tree4,stone1,bronze,cactus1,cactus2,rocks1,rocks2;
    public static BufferedImage gold1,gold2,iron1,iron2;
    public static BufferedImage[] fireTrap;
    public static BufferedImage portalStart,portalEnd,pillar1,pillar2,statue1,statue2,statue3,angel1,angel2;

    public static BufferedImage wood_item,plank_item,flower_item,
            bronze_item, stone_item, iron_item, gold_item,
            bronze_ingot, stone_block, iron_ingot, gold_ingot;

    public static BufferedImage inventoryScreen;
    public static BufferedImage heart,halfheart, expbar,expbarFilled;
    public static BufferedImage[] btn_start,btn_story,btn_music,btn_settings,btn_exit,btn_back,btn_highscores, btn_lvl1,btn_lvl2,btn_lvl3;
    public static BufferedImage WASD,E,L,ENTER,SHIFT,ESC;

    public static void init(){
        font32 = new Font("Garamond", Font.PLAIN, 32);
        font24 = new Font("Garamond", Font.ITALIC, 24);
        font24P = new Font("Garamond", Font.PLAIN, 24);
        font20 = new Font("Garamond", Font.BOLD, 20);
        font16 = new Font("Garamond", Font.PLAIN, 16);
        fontHighscores = new Font("Papyrus",Font.BOLD, 48);
        fontHighscores2 = new Font("Papyrus",Font.BOLD,32);

        int i;

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadimage("../res/Sprite/knight.png"));
        SpriteSheet maintileset = new SpriteSheet(ImageLoader.loadimage("../res/textures/Tileset.jpg"));
        SpriteSheet trees = new SpriteSheet(ImageLoader.loadimage("../res/textures/TreezPale.png"));
        SpriteSheet stones = new SpriteSheet(ImageLoader.loadimage("../res/textures/Stonez.png"));
        SpriteSheet firetrap = new SpriteSheet(ImageLoader.loadimage("../res/textures/fireTrap.png"));
        SpriteSheet materials = new SpriteSheet(ImageLoader.loadimage("../res/textures/materials.png"));
        SpriteSheet statues = new SpriteSheet(ImageLoader.loadimage("../res/textures/statues.png"));
        SpriteSheet monsters = new SpriteSheet(ImageLoader.loadimage("../res/textures/wolfbeast.png"));
        SpriteSheet monstersBears = new SpriteSheet(ImageLoader.loadimage("../res/textures/catbear.png"));

        player_right = new BufferedImage[6];
        player_left = new BufferedImage[6];
        player_up = new BufferedImage[3];
        player_down = new BufferedImage[3];
        player_idle_right = new BufferedImage[1];
        player_idle_left = new BufferedImage[1];
        player_attack_right = new BufferedImage[5];
        player_attack_left = new BufferedImage[5];
        player_walk_attack_right = new BufferedImage[6];
        player_walk_attack_left = new BufferedImage[6];
        player_run_right = new BufferedImage[8];
        player_run_left = new BufferedImage[8];
        player_win = new BufferedImage[7];
        player_damage_left = new BufferedImage[4];
        player_damage_right = new BufferedImage[4];
        player_death_left = new BufferedImage[9];
        player_death_right = new BufferedImage[9];

        //PLAYER

        for(i = 0; i < 6; ++i){
            player_right[i] = sheet.crop(105*(i+1),0,105,88);
            player_left[i] = sheet.flip(player_right[i]);
        }

        for(i = 0; i < 3; ++i){
            player_up[i] = sheet.crop(105*i,176,105,88);
            player_down[i] = sheet.crop(315 + 105*i,176,105,88);
        }

        player_idle_right[0] = sheet.crop(0,0,105,88);
        player_idle_left[0] = sheet.flip(player_idle_right[0]);

        for(i = 0; i < 5; ++i){
            player_attack_right[i] = sheet.crop(105*i,616,105,88);
            player_attack_left[i] = sheet.flip(player_attack_right[i]);
        }

        for(i = 0; i < 6; ++i){
            player_walk_attack_right[i] = sheet.crop(105*i,440,105,88);
            player_walk_attack_left[i] = sheet.flip(player_walk_attack_right[i]);
        }

        for(i = 0; i < 8; ++i){
            player_run_right[i] = sheet.crop(105 + 105*i,88,105,88);
            player_run_left[i] = sheet.flip(player_run_right[i]);
        }
        for(i = 0; i < 7; ++i)
            player_win[i] = sheet.crop(105*i,528,105,88);

        for(i = 0; i < 4; ++i) {
            player_damage_right[i] = sheet.crop(105*i,264,105,88);
            player_damage_left[i] = sheet.flip(player_damage_right[i]);
        }
        for(i = 0; i < 9; ++i){
            player_death_right[i] = sheet.crop(105*i,352,105,88);
            player_death_left[i] = sheet.flip(player_death_right[i]);
        }

        //MONSTERS

        monsters_down = new BufferedImage[numberOfMonsters][4];
        monsters_up = new BufferedImage[numberOfMonsters][4];
        monsters_left = new BufferedImage[numberOfMonsters][4];
        monsters_right = new BufferedImage[numberOfMonsters][4];
        monsters_idle = new BufferedImage[numberOfMonsters][1];

        //where every monster image starts
        int[] xi = {0, 6*48, 3*48, 9*48, 0};
        int[] yi = {0, 0, 0, 4*48, 4*48};

        for(i = 0; i < 3; ++i) { //3 monsters
            invokeMonstersInit(monsters_down[i], monsters, 3, xi[i], yi[i], 48, 52);
            invokeMonstersInit(monsters_left[i], monsters, 3, xi[i], yi[i] + 52, 48, 52);
            invokeMonstersInit(monsters_right[i], monsters, 3, xi[i], yi[i] + 2 * 52, 48, 52);
            invokeMonstersInit(monsters_up[i], monsters, 3, xi[i], yi[i] + 3 * 52, 48, 52);
            monsters_down[i][3] = monsters_down[i][1]; // left foot, middle, right foot, MIDDLE AGAIN
            monsters_up[i][3] = monsters_up[i][1];
            monsters_left[i][3] = monsters_left[i][1];
            monsters_right[i][3] = monsters_right[i][1];
            monsters_idle[i][0] = monsters_down[i][1];
        }

        for(i = 3; i < 5; ++i) { //2 monsters from other asset
            invokeMonstersInit(monsters_down[i], monstersBears, 3, xi[i], yi[i], 48, 48);
            invokeMonstersInit(monsters_left[i], monstersBears, 3, xi[i], yi[i] + 48, 48, 48);
            invokeMonstersInit(monsters_right[i], monstersBears, 3, xi[i], yi[i] + 2 * 48, 48, 48);
            invokeMonstersInit(monsters_up[i], monstersBears, 3, xi[i], yi[i] + 3 * 48, 48, 48);
            monsters_down[i][3] = monsters_down[i][1]; // left foot, middle, right foot, MIDDLE AGAIN
            monsters_up[i][3] = monsters_up[i][1];
            monsters_left[i][3] = monsters_left[i][1];
            monsters_right[i][3] = monsters_right[i][1];
            monsters_idle[i][0] = monsters_down[i][1];
        }

        //TILES
        grass = maintileset.crop(64,0,64,64);
        grass2 = maintileset.crop(128,0,64,64);
        dirt = maintileset.crop(192,0,64,64);
        stone = maintileset.crop(0,0,64,64);
        sand = maintileset.crop(256,0,64,64);
        stoneroad = maintileset.crop(320,0,64,64);
        stoneroadshade = maintileset.crop(384,0,64,64);
        woodroad = maintileset.crop(448,0,64,64);
        water = maintileset.crop(512,0,64,64);
        lava = maintileset.crop(576,0,64,64);
        for(i=0; i < 10; ++i)
            tufa[i] = maintileset.crop(64*i,64,64,64);


        //ENTITIES
        arbust = trees.crop(0,64,32,32);
        tree1 = trees.crop(0,102,63,117);
        tree2 = trees.crop(127,103,98,120);
        tree3 = trees.crop(709,224,120,128);
        tree4 = trees.crop(320,351,96,160);

        stone1 = stones.crop(144,96,48,48);
        bronze = stones.crop(144,144,48,48);
        gold1 = statues.crop(8*32,32,32,32);
        gold2 = statues.crop(8*32,64,32,64);
        iron1 = statues.crop(9*32,32,32,32);
        iron2 = statues.crop(9*32,64,32,64);

        cactus1 = stones.crop(11,19,38,48);
        cactus2 = stones.crop(66,20,26,48);
        mushroom1 = stones.crop(288,576,48,48);
        mushroom2 = stones.crop(336,576,48,48);
        flower_orange = stones.crop(48,336,48,48);
        flower_blue = stones.crop(144,336,48,48);
        flower_yellow = stones.crop(48,288,48,48);
        rocks1 = stones.crop(0,96,48,48);
        rocks2 = stones.crop(0,136,48,48);

        fireTrap = new BufferedImage[8];
        for(i = 0; i < 8; ++i)
            fireTrap[i] = firetrap.crop(50*i,0,50,80);

        portalStart = statues.crop(367,192,66,64);
        portalEnd = statues.crop(272,192,66,64);

        pillar1 = statues.crop(8*32,8*32,32,64);
        pillar2 = statues.crop(10*32,8*32,32,64);
        statue1 = statues.crop(3*32,1*32,32,64);
        statue2 = statues.crop(4*32,1*32,32,64);
        statue3 = statues.crop(2*32,1*32,32,64);
        angel1 = statues.crop(6*32,3*32,32,64);
        angel2 = statues.crop(7*32,3*32,32,64);

        //MATERIALS / ITEMS

        wood_item = materials.crop(128,0,64,64);
        plank_item = materials.crop(64,0,64,64);
        stone_item = materials.crop(64,64,64,64);
        stone_block = materials.crop(64,128,64,64);
        bronze_item = materials.crop(0,64,64,64);
        bronze_ingot = materials.crop(0,128,64,64);
        iron_item = materials.crop(128,64,64,64);
        iron_ingot = materials.crop(128,128,64,64);
        gold_item = materials.crop(192,64,64,64);
        gold_ingot = materials.crop(192,128,64,64);

        flower_item = stones.crop(190,380,48,48);


        //BUTTONS and INVENTORY

        inventoryScreen = ImageLoader.loadimage("../res/textures/inventoryScreen.png");
        heart = ImageLoader.loadimage("../res/textures/heart.png");
        halfheart = ImageLoader.loadimage("../res/textures/heart2.png");
        expbar = ImageLoader.loadimage(("../res/textures/expbar.png"));
        expbarFilled = ImageLoader.loadimage(("../res/textures/expbarFilled.png"));

        SpriteSheet GUI = new SpriteSheet(ImageLoader.loadimage("../res/textures/rpg_gui.png"));

        btn_start = new BufferedImage[2];
        btn_start[0] = GUI.crop(13,73,284,53);
        btn_start[1] = GUI.crop(13,130,284,53);

        btn_story = new BufferedImage[2];
        btn_story[0] = GUI.crop(311,431,284,53);
        btn_story[1] = GUI.crop(311,488,284,53);

        btn_settings = new BufferedImage[2];
        btn_settings[0] = GUI.crop(13,189,284,53);
        btn_settings[1] = GUI.crop(13,247,284,53);

        btn_music = new BufferedImage[2];
        btn_music[0] = GUI.crop(598,315,284,53);
        btn_music[1] = GUI.crop(598,373,284,53);

        btn_exit = new BufferedImage[2];
        btn_exit[0] = GUI.crop(311,315,284,53);
        btn_exit[1] = GUI.crop(311,373,284,53);

        btn_back = new BufferedImage[2];
        btn_back[0] = GUI.crop(598,198,284,53);
        btn_back[1] = GUI.crop(598,256,284,53);

        btn_highscores = new BufferedImage[2];
        btn_highscores[0] = GUI.crop(598,431,284,53);
        btn_highscores[1] = GUI.crop(598,489,284,53);

        btn_lvl1 = new BufferedImage[2];
        btn_lvl1[0] = GUI.crop(13,432,284,53);
        btn_lvl1[1] = GUI.crop(13,490,284,53);

        btn_lvl2 = new BufferedImage[3];
        btn_lvl2[0] = GUI.crop(13,549,284,53);
        btn_lvl2[1] = GUI.crop(13,607,284,53);
        btn_lvl2[2] = GUI.crop(13,303,284,53);

        btn_lvl3 = new BufferedImage[3];
        btn_lvl3[0] = GUI.crop(311,200,284,53);
        btn_lvl3[1] = GUI.crop(311,258,284,53);
        btn_lvl3[2] = GUI.crop(13,360,284,53);

        WASD = GUI.crop(673,67,159,108);
        E = GUI.crop(670,11,48,48);
        ENTER = GUI.crop(718,11,48,48);
        L = GUI.crop(766,11,48,48);
        SHIFT = GUI.crop(814,11,110,48);
        ESC = GUI.crop(853,61,71,48);
    }

    private static void invokeMonstersInit(BufferedImage[] bf, SpriteSheet image,int cnt, int xi, int yi, int width, int height){
        for(int i = 0; i < cnt; ++i)
            bf[i] = image.crop(xi + i*width, yi,width,height);
    }
}
