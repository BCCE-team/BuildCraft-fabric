package buildcraft.api.v2.worldgen;

import buildcraft.api.v2.registry.RegistrationContext;
import java.util.List;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Registry/query service for addon-supplied BuildCraft world-generation rules. */
public interface WorldgenService {
    void register(ResourceDepositRule rule, RegistrationContext context);
    Optional<ResourceDepositRule> rule(Identifier id);
    List<ResourceDepositRule> rules();
}
