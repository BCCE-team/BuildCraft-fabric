package buildcraft.api.v2.pipe;

import buildcraft.api.v2.persistence.ApiCodec;
import buildcraft.api.v2.persistence.OpaqueData;
import java.util.Objects;
import net.minecraft.resources.Identifier;

public record PipeSyncChannel<T>(Identifier id, ApiCodec<T, OpaqueData> codec, int maxBytes) {
    public PipeSyncChannel {
        Objects.requireNonNull(id, "id"); Objects.requireNonNull(codec, "codec");
        if (maxBytes <= 0) throw new IllegalArgumentException("maxBytes must be positive");
    }
}
