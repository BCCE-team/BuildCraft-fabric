package buildcraft.fabric;

import buildcraft.lib.BCLib;
import buildcraft.core.BCCore;
import buildcraft.energy.BCEnergy;
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
        BCCore.bootstrap();
        BCEnergy.bootstrap();
        // Module-dependent registries and marker caches must be finalized only
        // after the last installed BCCE gameplay module has bootstrapped.
        BCLib.finishModuleRegistration();
        ServerLifecycleEvents.SERVER_STARTED.register(FabricServerState::started);
        ServerLifecycleEvents.SERVER_STOPPED.register(FabricServerState::stopped);
    }
}
