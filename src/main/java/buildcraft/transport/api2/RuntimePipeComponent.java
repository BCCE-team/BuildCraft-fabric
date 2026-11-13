package buildcraft.transport.api2;

import buildcraft.api.v2.pipe.PipeComponent;
import java.util.Objects;
import net.minecraft.resources.Identifier;

/**
 * Marker component used to expose one built-in BCCE runtime behaviour through API2 without
 * making the implementation class part of the supported addon API.
 */
public final class RuntimePipeComponent implements PipeComponent {
    private final Identifier typeId;

    public RuntimePipeComponent(Identifier typeId) {
        this.typeId = Objects.requireNonNull(typeId, "typeId");
    }

    public Identifier typeId() {
        return typeId;
    }
}
