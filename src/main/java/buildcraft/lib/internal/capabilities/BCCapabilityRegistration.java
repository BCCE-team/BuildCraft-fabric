package buildcraft.lib.internal.capabilities;

import buildcraft.lib.internal.tiles.TilesAPI;
import buildcraft.lib.platform.capability.BlockCapability;
import buildcraft.lib.platform.storage.FabricStorageLookups;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Fabric equivalent of NeoForge's capability registration callback.
 * A BCCE tile implements {@link IBCCapabilityProvider} for internal calls; the
 * exact same scoped value is also exported into Fabric's native BlockApiLookup.
 */
public final class BCCapabilityRegistration {
    private BCCapabilityRegistration() {}

    public static <T, BE extends BlockEntity & IBCCapabilityProvider> void registerBlockEntity(
        BlockCapability<T, Direction> capability, BlockEntityType<BE> type) {
        if (capability == null || type == null) return;
        FabricStorageLookups.registerAddonType(type);
        TilesAPI.fabricLookup(capability).registerForBlockEntities(
            (blockEntity, side) -> ((IBCCapabilityProvider) blockEntity).getCapability(capability, side), type);
    }
}
