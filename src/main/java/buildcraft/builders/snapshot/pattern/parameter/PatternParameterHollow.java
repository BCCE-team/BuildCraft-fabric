/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.builders.snapshot.pattern.parameter;

import javax.annotation.Nonnull;

import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.lib.internal.statement.IStatement;
import buildcraft.lib.internal.statement.IStatementContainer;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.lib.internal.statement.StatementMouseClick;
import buildcraft.builders.BCBuildersSprites;
import buildcraft.lib.misc.StackUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.NbtCompat;

public enum PatternParameterHollow implements IStatementParameter {
    FILLED_INNER(true, false),
    FILLED_OUTER(true, true),
    HOLLOW(false, false);

    public final boolean filled;
    public final boolean outerFilled;

    PatternParameterHollow(boolean filled, boolean outerFilled) {
        this.filled = filled;
        this.outerFilled = outerFilled;
    }

    public static PatternParameterHollow readFromNbt(CompoundTag nbt) {
        if (NbtCompat.getBoolean(nbt, "filled")) {
            if (NbtCompat.getBoolean(nbt, "outer")) {
                return FILLED_OUTER;
            } else {
                return FILLED_INNER;
            }
        }
        return HOLLOW;
    }

    public void writeToNbt(CompoundTag compound) {
        compound.putBoolean("filled", filled);
        if (filled) {
            compound.putBoolean("outer", outerFilled);
        }
    }

    public String getUniqueTag() {
        return "buildcraft:fillerParameterHollow";
    }

    public ISprite getSprite() {
        if (filled) {
            if (outerFilled) {
                return BCBuildersSprites.PARAM_FILLED_OUTER;
            } else {
                return BCBuildersSprites.PARAM_FILLED_INNER;
            }
        }
        return BCBuildersSprites.PARAM_HOLLOW;
    }

    @Nonnull
    public ItemStack getItemStack() {
        return StackUtil.EMPTY;
    }

    public Component getDescription() {
        String after = filled ? (outerFilled ? "filled_outer" : "filled") : "hollow";
        return Component.translatable("fillerpattern.parameter." + after);
    }

    public PatternParameterHollow onClick(IStatementContainer source, IStatement stmt, ItemStack stack, StatementMouseClick mouse) {
        return null;
    }

    public IStatementParameter rotateLeft() {
        return this;
    }

    public PatternParameterHollow[] getPossible(IStatementContainer source) {
        return values();
    }
}
