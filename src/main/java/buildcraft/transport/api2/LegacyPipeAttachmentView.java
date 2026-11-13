package buildcraft.transport.api2;

import buildcraft.api.v2.pipe.PipeAttachment;
import buildcraft.transport.internal.pluggable.PipePluggable;
import java.util.Objects;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

/** API2 view over a BCCE internal pluggable. The pluggable implementation remains unsupported/internal. */
public final class LegacyPipeAttachmentView implements PipeAttachment {
    private final Identifier typeId;
    private final Direction side;
    private final PipePluggable pluggable;

    public LegacyPipeAttachmentView(Identifier typeId, Direction side, PipePluggable pluggable) {
        this.typeId = Objects.requireNonNull(typeId, "typeId");
        this.side = Objects.requireNonNull(side, "side");
        this.pluggable = Objects.requireNonNull(pluggable, "pluggable");
    }

    public Identifier typeId() { return typeId; }
    public Direction side() { return side; }

    public PipePluggable internalPluggable() { return pluggable; }
}
