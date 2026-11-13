package buildcraft.builders.snapshot.pattern;

import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.builders.BCBuildersSprites;

public class PatternShape2dOctagon extends PatternShape2d {
    public PatternShape2dOctagon() {
        super("2d_octagon");
    }

    public int minParameters() {
        return 2;
    }

    public int maxParameters() {
        return 2;
    }

    public ISprite getSprite() {
        return BCBuildersSprites.FILLER_2D_OCTAGON;
    }

    protected void genShape(int maxA, int maxB, LineList list) {

    }
}
