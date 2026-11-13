package buildcraft.robotics.internal.legacy.robots;

import buildcraft.lib.internal.core.BlockIndex;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import buildcraft.lib.compat.NbtCompat;

/** Internal persistent reservation key for API2 slot-independent item requests. */
public final class ResourceIdApiRequest extends ResourceId {
    private BlockIndex index = new BlockIndex();
    @Nullable private Direction side;
    private Identifier requestId = Objects.requireNonNull(Identifier.tryParse("buildcraft:invalid_request"));

    public ResourceIdApiRequest() {}

    public ResourceIdApiRequest(DockingStation station, Identifier requestId) {
        this.index = station.index();
        this.side = station.side();
        this.requestId = Objects.requireNonNull(requestId, "requestId");
    }

    public Identifier requestId() { return requestId; }

    public boolean equals(Object obj) {
        return obj instanceof ResourceIdApiRequest other
            && Objects.equals(index, other.index)
            && side == other.side
            && Objects.equals(requestId, other.requestId);
    }

    public int hashCode() { return Objects.hash(index, side, requestId); }

    public void writeToNBT(CompoundTag nbt) {
        super.writeToNBT(nbt);
        CompoundTag indexTag = new CompoundTag();
        index.writeTo(indexTag);
        nbt.put("index", indexTag);
        nbt.putByte("side", (byte) (side == null ? -1 : side.ordinal()));
        nbt.putString("requestId", requestId.toString());
    }

    protected void readFromNBT(CompoundTag nbt) {
        super.readFromNBT(nbt);
        index = new BlockIndex(NbtCompat.getCompound(nbt, "index"));
        byte sideId = NbtCompat.getByte(nbt, "side");
        side = sideId >= 0 && sideId < Direction.values().length ? Direction.values()[sideId] : null;
        Identifier parsed = Identifier.tryParse(NbtCompat.getString(nbt, "requestId"));
        if (parsed != null) requestId = parsed;
    }
}
