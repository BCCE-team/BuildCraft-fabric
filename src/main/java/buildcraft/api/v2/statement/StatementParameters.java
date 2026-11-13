package buildcraft.api.v2.statement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public final class StatementParameters {
    public static final StatementParameters EMPTY = new StatementParameters(Map.of());
    private final Map<Identifier, ParameterValue<?>> values;

    public StatementParameters(Map<Identifier, ParameterValue<?>> values) {
        this.values = Map.copyOf(new LinkedHashMap<>(Objects.requireNonNull(values, "values")));
    }

    public Map<Identifier, ParameterValue<?>> values() { return values; }

    public <T> Optional<T> get(Identifier slotId, ParameterType<T> type) {
        ParameterValue<?> value = values.get(slotId);
        if (value == null || !value.type().id().equals(type.id())) return Optional.empty();
        @SuppressWarnings("unchecked") T cast = (T) value.value();
        return Optional.of(cast);
    }
}
