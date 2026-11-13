package buildcraft.api.v2.registry;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Registration happens before freeze. After freeze this becomes immutable. */
public interface ApiRegistry<T> {
    void register(Identifier id, T value);

    default void register(Identifier id, T value, RegistrationContext context) {
        register(id, value);
    }

    default void registerAlias(Identifier alias, Identifier canonicalId, RegistrationContext context) {
        throw new UnsupportedOperationException("Aliases are not supported by this registry");
    }

    T get(Identifier id);

    default Optional<RegistryEntry<T>> entry(Identifier id) {
        T value = get(id);
        return value == null ? Optional.empty() : Optional.of(new RegistryEntry<>(canonicalId(id), value, "unknown"));
    }

    default Identifier canonicalId(Identifier id) {
        return id;
    }

    Collection<T> values();

    Collection<RegistryEntry<T>> entries();

    boolean frozen();
}
