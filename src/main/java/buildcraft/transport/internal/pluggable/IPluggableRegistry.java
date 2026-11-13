package buildcraft.transport.internal.pluggable;

import net.minecraft.resources.Identifier;

public interface IPluggableRegistry {
    default void register(PluggableDefinition definition) {
        register(definition.identifier, definition);
    }

    void register(Identifier identifier, PluggableDefinition definition);

    PluggableDefinition getDefinition(Identifier identifier);
}
