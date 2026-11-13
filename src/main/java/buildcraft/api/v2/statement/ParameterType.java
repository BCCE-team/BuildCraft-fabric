package buildcraft.api.v2.statement;

import buildcraft.api.v2.persistence.ApiCodec;
import buildcraft.api.v2.persistence.OpaqueData;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.resources.Identifier;

public final class ParameterType<T> {
    private final Identifier id;
    private final ApiCodec<T, OpaqueData> codec;
    private final StatementSuggestionProvider<T> suggestions;
    private final Function<StatementContext, T> initialValue;

    /**
     * Supply an initial value even if the type offers no selectable suggestions. The factory
     * is invoked when a gate parameter is first created; it must return a valid value or
     * null if the parameter cannot yet be initialized (for example, without a world view).
     * Values are created per slot rather than shared between independent gates.
     */
    public ParameterType(Identifier id, ApiCodec<T, OpaqueData> codec,
        StatementSuggestionProvider<T> suggestions, Function<StatementContext, T> initialValue) {
        this.id = Objects.requireNonNull(id, "id");
        this.codec = Objects.requireNonNull(codec, "codec");
        this.suggestions = Objects.requireNonNull(suggestions, "suggestions");
        this.initialValue = Objects.requireNonNull(initialValue, "initialValue");
    }

    public ParameterType(Identifier id, ApiCodec<T, OpaqueData> codec, StatementSuggestionProvider<T> suggestions) {
        this(id, codec, suggestions, context -> null);
    }

    public ParameterType(Identifier id, ApiCodec<T, OpaqueData> codec) {
        this(id, codec, context -> List.of());
    }

    public Identifier id() { return id; }
    public ApiCodec<T, OpaqueData> codec() { return codec; }
    public List<T> suggestions(StatementContext context) { return List.copyOf(suggestions.suggestions(context)); }

    /** Returns empty when no default is provided; it never invents arbitrary addon data. */
    public Optional<T> initialValue(StatementContext context) {
        return Optional.ofNullable(initialValue.apply(Objects.requireNonNull(context, "context")));
    }
}
