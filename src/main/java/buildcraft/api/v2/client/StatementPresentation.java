package buildcraft.api.v2.client;

import java.util.Objects;
import net.minecraft.resources.Identifier;

public record StatementPresentation(Identifier statementId, String titleKey, String descriptionKey, Identifier icon) {
    public StatementPresentation {
        Objects.requireNonNull(statementId, "statementId"); Objects.requireNonNull(titleKey, "titleKey");
        descriptionKey = descriptionKey == null ? "" : descriptionKey;
    }
}
