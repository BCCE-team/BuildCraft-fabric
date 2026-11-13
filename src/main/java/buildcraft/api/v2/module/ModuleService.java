package buildcraft.api.v2.module;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public interface ModuleService {
    Collection<ModuleInfo> modules();
    Optional<ModuleInfo> module(Identifier id);

    default boolean loaded(Identifier id) {
        return module(id).map(ModuleInfo::loaded).orElse(false);
    }

    /** A physically loaded addon advertises its own module before the API freeze. */
    default void registerExternal(ModuleInfo info, Collection<Identifier> required,
                                  buildcraft.api.v2.registry.RegistrationContext context) {
        throw new UnsupportedOperationException("External module declarations are not supported");
    }
}
