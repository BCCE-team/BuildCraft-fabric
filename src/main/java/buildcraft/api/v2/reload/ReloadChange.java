package buildcraft.api.v2.reload;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Immutable before/after publication event for a registered definition domain. */
public record ReloadChange<T>(Identifier typeId, ScriptedDefinitionSnapshot<T> previous,
                              ScriptedDefinitionSnapshot<T> candidate) {
    public ReloadChange {
        Objects.requireNonNull(typeId, "typeId");
        Objects.requireNonNull(previous, "previous");
        Objects.requireNonNull(candidate, "candidate");
    }
}
