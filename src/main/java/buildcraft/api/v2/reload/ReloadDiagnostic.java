package buildcraft.api.v2.reload;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public record ReloadDiagnostic(Level level, Optional<Identifier> definitionId, String message) {
    public ReloadDiagnostic {
        Objects.requireNonNull(level, "level");
        Objects.requireNonNull(definitionId, "definitionId");
        Objects.requireNonNull(message, "message");
        if (message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
    }

    public static ReloadDiagnostic warning(Identifier id, String message) {
        return new ReloadDiagnostic(Level.WARNING, Optional.ofNullable(id), message);
    }

    public static ReloadDiagnostic error(Identifier id, String message) {
        return new ReloadDiagnostic(Level.ERROR, Optional.ofNullable(id), message);
    }

    public enum Level {
        WARNING,
        ERROR
    }
}
