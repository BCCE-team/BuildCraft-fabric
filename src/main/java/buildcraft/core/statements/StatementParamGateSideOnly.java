/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.core.statements;

import javax.annotation.Nonnull;

import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.lib.internal.statement.IStatement;
import buildcraft.lib.internal.statement.IStatementContainer;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.lib.internal.statement.StatementMouseClick;
import buildcraft.core.BCCoreSprites;
import buildcraft.lib.misc.StackUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.NbtCompat;

public enum StatementParamGateSideOnly implements IStatementParameter {
    ANY(false),
    SPECIFIC(true);

    public final boolean isSpecific;

    private static final StatementParamGateSideOnly[] POSSIBLE_ANY = { ANY, SPECIFIC };
    private static final StatementParamGateSideOnly[] POSSIBLE_SPECIFIC = { SPECIFIC, ANY };

    StatementParamGateSideOnly(boolean isSpecific) {
        this.isSpecific = isSpecific;
    }

    public static StatementParamGateSideOnly readFromNbt(CompoundTag nbt) {
        if (NbtCompat.getBoolean(nbt, "isOn")) {
            return SPECIFIC;
        }
        return ANY;
    }

    public void writeToNbt(CompoundTag compound) {
        compound.putBoolean("isOn", isSpecific);
    }

    @Nonnull
    public ItemStack getItemStack() {
        return StackUtil.EMPTY;
    }

    public ISprite getSprite() {
        if (isSpecific) {
            return BCCoreSprites.PARAM_GATE_SIDE_ONLY;
        } else {
            return null;
        }
    }

    public DrawType getDrawType() {
        return DrawType.SPRITE_ONLY;
    }

    public StatementParamGateSideOnly onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse) {
        return null;
    }

    public Component getDescription() {
        return Component.translatable(isSpecific ? ("gate.parameter.redstone.gateSideOnly") : "");
    }

    public String getUniqueTag() {
        return "buildcraft:redstoneGateSideOnly";
    }

    public IStatementParameter rotateLeft() {
        return this;
    }

    public IStatementParameter[] getPossible(IStatementContainer source) {
        return isSpecific ? POSSIBLE_SPECIFIC : POSSIBLE_ANY;
    }
}
