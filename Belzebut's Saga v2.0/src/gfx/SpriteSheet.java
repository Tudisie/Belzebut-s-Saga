package gfx;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage crop(int x,int y,int width,int height){
        return sheet.getSubimage(x,y,width,height);
    }

    public BufferedImage flip(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        return img;
    }

    public BufferedImage translate(BufferedImage img){
        AffineTransform tx = new AffineTransform();
        tx.translate(2, 1);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);

        return img;
    }
}
