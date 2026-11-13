package buildcraft.api.v2.persistence;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/**
 * Serialized persisted extension envelope.
 */
public record EncodedPayload<P>(Identifier typeId, int schemaVersion, P payload) {
    public EncodedPayload {
        Objects.requireNonNull(typeId, "typeId");
        Objects.requireNonNull(payload, "payload");
        if (schemaVersion < 0) {
            throw new IllegalArgumentException("schemaVersion must be non-negative");
        }
    }
}
