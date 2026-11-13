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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.NbtCompat;

public enum PatternParameterYDir implements IStatementParameter {
    UP(true),
    DOWN(false);

    private static final PatternParameterYDir[] POSSIBLE_ORDER = { null, null, UP, null, null, null, DOWN };

    public final boolean up;

    PatternParameterYDir(boolean up) {
        this.up = up;
    }

    public static PatternParameterYDir readFromNbt(CompoundTag nbt) {
        if (NbtCompat.getBoolean(nbt, "up")) {
            return UP;
        }
        return DOWN;
    }

    public void writeToNbt(CompoundTag nbt) {
        nbt.putBoolean("up", up);
    }

    public String getUniqueTag() {
        return "buildcraft:fillerParameterYDir";
    }

    @Nonnull
    public ItemStack getItemStack() {
        return ItemStack.EMPTY;
    }

    public Component getDescription() {
        return Component.translatable("direction." + (up ? "up" : "down"));
    }

    public PatternParameterYDir onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse) {
        return null;
    }

    public IStatementParameter[] getPossible(IStatementContainer source) {
        return POSSIBLE_ORDER;
    }

    public boolean isPossibleOrdered() {
        return true;
    }

    public IStatementParameter rotateLeft() {
        return this;
    }

    public ISprite getSprite() {
        return up ? BCBuildersSprites.PARAM_STAIRS_UP : BCBuildersSprites.PARAM_STAIRS_DOWN;
    }
}
