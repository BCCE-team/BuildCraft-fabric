package buildcraft.fabric;

import buildcraft.lib.BCLib;
import net.fabricmc.api.ModInitializer;
import buildcraft.lib.net.MessageManager;
import buildcraft.lib.net.FabricServerState;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

/**
 * Work-in-progress Fabric 26.3 module bootstrap. Registration is in the native
 * Fabric registry; remaining BCCE module lifecycle hooks are deliberately NOT
 * claimed to be complete. The Gradle JAR gate prevents shipping this stage.
 */
public final class BuildCraftFabric263 implements ModInitializer {
    public void onInitialize() {
        MessageManager.registerPayloads();
        BCLib.bootstrap();
        ServerLifecycleEvents.SERVER_STARTED.register(FabricServerState::started);
        ServerLifecycleEvents.SERVER_STOPPED.register(FabricServerState::stopped);
    }
}
