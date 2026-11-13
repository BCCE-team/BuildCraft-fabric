/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.builders.snapshot.pattern.parameter;

import java.util.Locale;

import javax.annotation.Nonnull;

import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.lib.internal.statement.IStatement;
import buildcraft.lib.internal.statement.IStatementContainer;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.lib.internal.statement.StatementMouseClick;
import buildcraft.builders.BCBuildersSprites;
import buildcraft.lib.misc.StackUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.NbtCompat;

public enum PatternParameterAxis implements IStatementParameter {
    X(Axis.X),
    Y(Axis.Y),
    Z(Axis.Z);

    public final Direction.Axis axis;

    PatternParameterAxis(Axis axis) {
        this.axis = axis;
    }

    public static PatternParameterAxis readFromNbt(CompoundTag nbt) {
        byte ord = NbtCompat.getByte(nbt, "a");
        if (ord <= 0) {
            return X;
        }
        if (ord >= 2) {
            return Z;
        }
        return Y;
    }

    public String getUniqueTag() {
        return "buildcraft:filler_parameter_axis";
    }

    public Component getDescription() {
        return Component.translatable("buildcraft.param.axis." + name().toLowerCase(Locale.ROOT));
    }

    public ISprite getSprite() {
        return BCBuildersSprites.PARAM_AXIS.get(axis);
    }

    @Nonnull
    public ItemStack getItemStack() {
        return StackUtil.EMPTY;
    }

    public IStatementParameter onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse) {
        return null;
    }

    public void writeToNbt(CompoundTag nbt) {
        nbt.putByte("a", (byte) ordinal());
    }

    public IStatementParameter rotateLeft() {
        switch (this) {
            case X:
                return Z;
            case Y:
                return Y;
            case Z:
                return X;
            default:
                throw new IllegalStateException("Unknown axis " + this);
        }
    }

    public IStatementParameter[] getPossible(IStatementContainer source) {
        return values();
    }
}
