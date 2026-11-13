package buildcraft.api.v2.reload;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * Immutable, atomically published data-definition snapshot.
 */
public final class DefinitionSnapshot<V> {
    private final ReloadGeneration generation;
    private final Map<Identifier, ResolvedDefinition<V>> definitions;

    private DefinitionSnapshot(ReloadGeneration generation, Map<Identifier, ResolvedDefinition<V>> definitions) {
        this.generation = Objects.requireNonNull(generation, "generation");
        this.definitions = Collections.unmodifiableMap(new LinkedHashMap<>(definitions));
    }


    public static <V> DefinitionSnapshot<V> of(
        ReloadGeneration generation, Map<Identifier, ResolvedDefinition<V>> definitions
    ) {
        return new DefinitionSnapshot<>(generation, definitions);
    }

    public static <V> DefinitionSnapshot<V> empty() {
        return new DefinitionSnapshot<>(new ReloadGeneration(0), Map.of());
    }

    public ReloadGeneration generation() {
        return generation;
    }

    public Optional<V> get(Identifier id) {
        ResolvedDefinition<V> definition = definitions.get(id);
        return definition == null ? Optional.empty() : Optional.of(definition.value());
    }

    public Optional<ResolvedDefinition<V>> resolved(Identifier id) {
        return Optional.ofNullable(definitions.get(id));
    }

    public Collection<ResolvedDefinition<V>> definitions() {
        return definitions.values();
    }

    public Map<Identifier, ResolvedDefinition<V>> asMap() {
        return definitions;
    }
}
