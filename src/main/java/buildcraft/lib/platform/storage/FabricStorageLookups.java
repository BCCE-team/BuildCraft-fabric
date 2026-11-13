/*
 * Copyright (c) 2026 the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */
package buildcraft.lib.platform.storage;

import java.util.List;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Registers the BuildCraft block-storage contract after every BCCE module has
 * registered its block-entity types. Fabric lookups then expose the same
 * sided endpoint to BuildCraft pipes, third-party pipes and vanilla transfer
 * consumers.
 *
 * <p>Only BCCE-owned types are bulk-registered. Addons can call
 * {@link #registerAddonType(BlockEntityType)} for their own block entities.</p>
 */
public final class FabricStorageLookups {
    private static boolean installed;
    private static final Set<BlockEntityType<?>> REGISTERED =
        Collections.newSetFromMap(new IdentityHashMap<>());

    private FabricStorageLookups() {}

    public static synchronized void install() {
        if (installed) return;
        List<BlockEntityType<?>> buildcraftTypes = BuiltInRegistries.BLOCK_ENTITY_TYPE.stream()
            .filter(type -> {
                var id = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(type);
                return id != null && id.getNamespace().startsWith("buildcraft");
            })
            .toList();
        if (!buildcraftTypes.isEmpty()) {
            registerTypes(buildcraftTypes.toArray(BlockEntityType<?>[]::new));
        }
        installed = true;
    }

    /** Register a non-BCCE block-entity type whose instance implements the provider contract. */
    public static synchronized void registerAddonType(BlockEntityType<?> type) {
        if (type == null) return;
        registerTypes(type);
    }

    private static synchronized void registerTypes(BlockEntityType<?>... types) {
        for (BlockEntityType<?> type : types) {
            if (type == null || REGISTERED.contains(type)) continue;
            var id = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(type);
            try {
                ItemStorage.SIDED.registerForBlockEntities(
                    (blockEntity, side) -> blockEntity instanceof FabricBlockStorageProvider provider
                        ? provider.buildcraftItemStorage(side) : null,
                    type
                );
            } catch (IllegalArgumentException invalidType) {
                buildcraft.lib.internal.debug.BCLog.logger.warn(
                    "Could not register Fabric item storage for block entity {}",
                    id, invalidType
                );
            }
            try {
                FluidStorage.SIDED.registerForBlockEntities(
                    (blockEntity, side) -> blockEntity instanceof FabricBlockStorageProvider provider
                        ? provider.buildcraftFluidStorage(side) : null,
                    type
                );
            } catch (IllegalArgumentException invalidType) {
                buildcraft.lib.internal.debug.BCLog.logger.warn(
                    "Could not register Fabric fluid storage for block entity {}",
                    id, invalidType
                );
            }
            try {
                team.reborn.energy.api.EnergyStorage.SIDED.registerForBlockEntities(
                    (blockEntity, side) -> blockEntity instanceof FabricBlockStorageProvider provider
                        ? provider.buildcraftEnergyStorage(side) : null,
                    type
                );
            } catch (IllegalArgumentException invalidType) {
                buildcraft.lib.internal.debug.BCLog.logger.warn(
                    "Could not register Fabric energy storage for block entity {}",
                    id, invalidType
                );
            }
            REGISTERED.add(type);
        }
    }
}
