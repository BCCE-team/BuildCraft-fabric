package buildcraft.api.v2.permission;

import java.util.UUID;
import net.minecraft.resources.Identifier;

/** Creates stable automation identities without exposing FakePlayer implementations. */
public interface ActorService {
    AutomationActor player(UUID playerId, String playerName);
    AutomationActor machineOwner(UUID ownerId, String ownerName, Identifier sourceId);
    AutomationActor system(Identifier sourceId);
    AutomationActor unknown();
}
