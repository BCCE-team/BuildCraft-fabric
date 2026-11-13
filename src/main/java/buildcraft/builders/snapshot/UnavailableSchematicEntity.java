/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */
package buildcraft.builders.snapshot;

import java.util.Objects;

import buildcraft.builders.internal.schematic.legacy.ISchematicEntity;
import buildcraft.builders.internal.schematic.legacy.SchematicEntityContext;
import buildcraft.lib.internal.core.InvalidInputDataException;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import buildcraft.lib.compat.NbtCompat;

/** Lossless placeholder for a legacy schematic entity whose owning addon is currently unavailable. */
public final class UnavailableSchematicEntity implements ISchematicEntity {
    private static final Identifier FALLBACK_TYPE = Objects.requireNonNull(
        Identifier.tryParse("buildcraft:unavailable_schematic_entity")
    );

    private final CompoundTag serialized;
    private final Identifier typeId;

    public UnavailableSchematicEntity(CompoundTag serialized) {
        this.serialized = Objects.requireNonNull(serialized, "serialized").copy();
        Identifier parsed = Identifier.tryParse(NbtCompat.getString(serialized, "name"));
        this.typeId = parsed == null ? FALLBACK_TYPE : parsed;
    }

    public CompoundTag serializedEnvelope() {
        return serialized.copy();
    }

    public Identifier typeId() {
        return typeId;
    }

    public void init(SchematicEntityContext context) {
    }

    public Vec3 getPos() {
        // Unavailable entities are filtered from BuildingInfo before this position participates in scheduling.
        return Vec3.ZERO;
    }

    public ISchematicEntity getRotated(Rotation rotation) {
        return this;
    }

    public Entity build(BlockGetter world, BlockPos basePos) {
        return null;
    }

    public Entity buildWithoutChecks(BlockGetter world, BlockPos basePos) {
        return null;
    }

    public CompoundTag serializeNBT() {
        return NbtCompat.getCompound(serialized, "data").copy();
    }

    public void deserializeNBT(CompoundTag nbt) throws InvalidInputDataException {
        throw new InvalidInputDataException("Unavailable schematic entity placeholders are immutable");
    }

    public boolean equals(Object obj) {
        return obj instanceof UnavailableSchematicEntity other && serialized.equals(other.serialized);
    }

    public int hashCode() {
        return serialized.hashCode();
    }
}

