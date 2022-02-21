package tiles;

import gfx.Assets;


public class tufaTile extends Tile{

    public tufaTile(int id) {
        super(Assets.tufa[id-first_tufa_id], id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
