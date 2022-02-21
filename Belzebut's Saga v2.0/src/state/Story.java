package state;

import gfx.Assets;
import gfx.Text;

import java.awt.*;

public class Story {
    private static int spacingY = 26;
    private static int heading = 37;

    public static void render(Graphics g){
        Text.drawString(g,"Intr-un univers mult indepartat de imaginatia noastra, a existat un razboi intre bine si rau",30,heading, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"cum nu s-a mai vazut.  Diavolul, infrant in toiul razboiului, ofuscat de puterile Sfantului",30,heading + spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"Mihail si ale ingerilor sai,  este izgonit din Imparatia Cerurilor.  Acesta se retrage pe",30,heading + 2*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"Pamant impreuna cu ingerii decazuti, iar prin propria-i alegere deliberata de a nu asculta de",30,heading + 3*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"glasul binelui, ii invoca pe cei sapte printi ai Iadului,  Astaroth,  Mamona,  Asmodeus,",30,heading + 4*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"Leviatan, Belphegor, Satana si nu in cele din urma, Belzebut.",30,heading + 5*spacingY, false, Color.WHITE, Assets.font24);

        Text.drawString(g,"Diavolul le porunceste sa deschida portile iadului pentru a lasa infernul sa stinga chiar si",30,heading + 7*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"ultima lumina din univers.Pentru aceasta, fiii trebuie sa elibereze blestemul existentei umane",30,heading + 8*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"cu care au fost izgoniti pe Pamant, sa se ridice in ceruri si sa infrunte fortele binelui intr-o",30,heading + 9*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"lupta decisiva pentru una dintre cele doua dihotomii divine. Fiecare din cei sapte fii, inclusiv",30,heading + 10*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"diavolul s-au metamorforzat intr-o creatura de pe aceasta planeta care simboliza pacatele",30,heading + 11*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"umane, pentru a-si ascunde chipul. Diavolul s-a transformat in sarpe, Lucifer in balaur,",30,heading + 12*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"iar Belzebut, in om.",30,heading + 13*spacingY, false, Color.WHITE, Assets.font24);

        Text.drawString(g,"Jocul urmareste povestea lui Belzebut, printul caruia i-a fost oferita indatorirea de a ucide",30,heading + 15*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"toate vietatile de pe planeta, pentru a intampina sosirea demonilor inchisi de o eternitate.",30,heading + 16*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"Acesta cutremura pamantul cu o silnicie zdrobitoare si privind ultimul apus de soare,",30,heading + 17*spacingY, false, Color.WHITE, Assets.font24);
        Text.drawString(g,"incepe sa dezlantuie inevitabilul…",30,heading + 18*spacingY, false, Color.WHITE, Assets.font24);

    }
}
