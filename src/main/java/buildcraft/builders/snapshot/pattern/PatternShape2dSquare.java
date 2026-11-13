package buildcraft.builders.snapshot.pattern;

import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.builders.BCBuildersSprites;

public class PatternShape2dSquare extends PatternShape2d {
    public PatternShape2dSquare() {
        super("2d_square");
    }

    public int minParameters() {
        return 2;
    }

    public int maxParameters() {
        return 2;
    }

    public ISprite getSprite() {
        return BCBuildersSprites.FILLER_2D_SQUARE;
    }

    protected void genShape(int maxA, int maxB, LineList list) {
        list.lineTo(maxA, 0);
        list.lineTo(maxA, maxB);
        list.lineTo(0, maxB);
        list.lineTo(0, 0);
        list.setFillPoint(maxA / 2, maxB / 2);
    }
}
