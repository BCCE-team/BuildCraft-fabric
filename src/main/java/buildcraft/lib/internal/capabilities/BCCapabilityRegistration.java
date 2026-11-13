package buildcraft.lib.internal.capabilities;

import buildcraft.lib.platform.capability.BlockCapability;
import buildcraft.lib.platform.storage.FabricStorageLookups;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Fabric equivalent of NeoForge's capability registration callback.
 * The internal capability identity is retained; external item/fluid/energy
 * lookup is installed through Fabric storage lookups once a type is registered.
 */
public final class BCCapabilityRegistration {
    private BCCapabilityRegistration() {}
    public static <T, BE extends BlockEntity & IBCCapabilityProvider> void registerBlockEntity(
        BlockCapability<T, Direction> capability, BlockEntityType<BE> type) {
        FabricStorageLookups.registerAddonType(type);
    }
}
