package buildcraft.api.v2.client;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** An action identifier and label; menu actions still require server-side authorization. */
public record ActionPresentation(Identifier actionId, String titleKey, Identifier icon) {
    public ActionPresentation {
        Objects.requireNonNull(actionId, "actionId");
        Objects.requireNonNull(titleKey, "titleKey");
    }
}
