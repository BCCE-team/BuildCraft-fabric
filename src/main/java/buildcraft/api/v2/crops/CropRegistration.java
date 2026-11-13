package buildcraft.api.v2.crops;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record CropRegistration(Identifier id, int priority, CropAdapter adapter) {
    public CropRegistration {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(adapter, "adapter");
    }
}
