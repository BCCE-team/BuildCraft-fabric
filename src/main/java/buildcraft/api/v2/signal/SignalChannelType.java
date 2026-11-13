package buildcraft.api.v2.signal;

import buildcraft.api.v2.persistence.ApiCodec;
import buildcraft.api.v2.persistence.OpaqueData;
import java.util.Objects;
import net.minecraft.resources.Identifier;

public final class SignalChannelType<T> {
    private final Identifier id;
    private final T defaultValue;
    private final ApiCodec<T, OpaqueData> codec;
    private final SignalCombiner<T> combiner;

    public SignalChannelType(Identifier id, T defaultValue, ApiCodec<T, OpaqueData> codec, SignalCombiner<T> combiner) {
        this.id = Objects.requireNonNull(id, "id");
        this.defaultValue = Objects.requireNonNull(defaultValue, "defaultValue");
        this.codec = Objects.requireNonNull(codec, "codec");
        this.combiner = Objects.requireNonNull(combiner, "combiner");
    }

    public Identifier id() { return id; }
    public T defaultValue() { return defaultValue; }
    public ApiCodec<T, OpaqueData> codec() { return codec; }
    public T combine(T current, T incoming) { return combiner.combine(current, incoming); }
}
