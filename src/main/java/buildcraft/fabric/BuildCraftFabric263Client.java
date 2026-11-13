package buildcraft.fabric;

import buildcraft.lib.net.MessageManager;
import buildcraft.core.client.FabricCoreClient;
import buildcraft.energy.client.FabricEnergyClient;
import buildcraft.factory.client.FabricFactoryClient;
import buildcraft.builders.client.FabricBuildersClient;
import buildcraft.robotics.client.FabricRoboticsClient;
import buildcraft.silicon.client.FabricSiliconClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

/** Client-only network registration; no Minecraft client references on the dedicated server. */
public final class BuildCraftFabric263Client implements ClientModInitializer {
    public void onInitializeClient() {
        buildcraft.lib.BCLibEventDist.ClientGame.setup();
        FabricCoreClient.bootstrap();
        FabricEnergyClient.bootstrap();
        buildcraft.transport.client.FabricTransportClient.bootstrap();
        FabricSiliconClient.bootstrap();
        FabricFactoryClient.bootstrap();
        FabricBuildersClient.bootstrap();
        FabricRoboticsClient.bootstrap();
        MessageManager.registerClientReceivers();
        // All mod and client entrypoints, including addon registrations, are
        // complete by CLIENT_STARTED. Do not seal them in onInitializeClient().
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> buildcraft.lib.BCLib.finishApiRegistration());
    }
}
