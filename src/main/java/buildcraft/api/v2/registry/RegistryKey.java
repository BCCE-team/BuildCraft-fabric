package buildcraft.api.v2.registry;

import net.minecraft.resources.Identifier;

public record RegistryKey<T>(Identifier id) {
    public RegistryKey {
        if (id == null) throw new NullPointerException("id");
    }
}
