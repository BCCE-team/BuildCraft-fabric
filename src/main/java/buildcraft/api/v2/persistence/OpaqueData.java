package buildcraft.api.v2.persistence;

import java.util.Arrays;
import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Immutable loader-neutral opaque payload for addon-owned runtime state. */
public final class OpaqueData {
    private final Identifier format;
    private final byte[] bytes;

    public OpaqueData(Identifier format, byte[] bytes) {
        this.format = Objects.requireNonNull(format, "format");
        this.bytes = Objects.requireNonNull(bytes, "bytes").clone();
    }

    public Identifier format() { return format; }
    public byte[] bytes() { return bytes.clone(); }
    public int size() { return bytes.length; }

    public boolean equals(Object other) {
        return other instanceof OpaqueData that && format.equals(that.format) && Arrays.equals(bytes, that.bytes);
    }

    public int hashCode() {
        return 31 * format.hashCode() + Arrays.hashCode(bytes);
    }
}
