package buildcraft.fabric;

import buildcraft.lib.net.MessageManager;
import buildcraft.core.client.FabricCoreClient;
import net.fabricmc.api.ClientModInitializer;

/** Client-only network registration; no Minecraft client references on the dedicated server. */
public final class BuildCraftFabric263Client implements ClientModInitializer {
    public void onInitializeClient() {
        buildcraft.lib.BCLibEventDist.ClientGame.setup();
        FabricCoreClient.bootstrap();
        MessageManager.registerClientReceivers();
    }
}
