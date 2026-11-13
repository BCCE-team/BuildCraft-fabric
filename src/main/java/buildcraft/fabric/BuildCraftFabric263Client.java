package buildcraft.fabric;

import buildcraft.lib.net.MessageManager;
import buildcraft.core.client.FabricCoreClient;
import buildcraft.energy.client.FabricEnergyClient;
import buildcraft.factory.client.FabricFactoryClient;
import buildcraft.builders.client.FabricBuildersClient;
import buildcraft.robotics.client.FabricRoboticsClient;
import net.fabricmc.api.ClientModInitializer;

/** Client-only network registration; no Minecraft client references on the dedicated server. */
public final class BuildCraftFabric263Client implements ClientModInitializer {
    public void onInitializeClient() {
        buildcraft.lib.BCLibEventDist.ClientGame.setup();
        FabricCoreClient.bootstrap();
        FabricEnergyClient.bootstrap();
        buildcraft.transport.client.FabricTransportClient.bootstrap();
        FabricFactoryClient.bootstrap();
        FabricBuildersClient.bootstrap();
        FabricRoboticsClient.bootstrap();
        MessageManager.registerClientReceivers();
    }
}
