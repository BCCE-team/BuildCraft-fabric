/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.builders.snapshot;

import java.util.Optional;

import buildcraft.lib.misc.ItemStackUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.core.registries.BuiltInRegistries;
import buildcraft.lib.compat.NbtCompat;

public class ItemStackRef {
    private final NbtRef<StringTag> item;
    private final NbtRef<IntTag> amount;
    private final NbtRef<CompoundTag> tagCompound;

    public ItemStackRef(NbtRef<StringTag> item, NbtRef<IntTag> amount, NbtRef<CompoundTag> tagCompound) {
        this.item = item;
        this.amount = amount;
        this.tagCompound = tagCompound;
    }

    public ItemStack get(Tag nbt) {
        String id = item.get(nbt)
            .map(NbtCompat::getString)
            .orElseThrow(() -> new IllegalArgumentException("Missing item registry ID for " + item));
        Identifier key = parseRegistryId("item", id);
        Item value = BuiltInRegistries.ITEM.getOptional(key)
            .orElseThrow(() -> new IllegalArgumentException("Unknown item registry ID '" + key + "'"));
        int resolvedAmount = Optional.ofNullable(amount)
            .flatMap(ref -> ref.get(nbt))
            .map(NbtCompat::getInt)
            .orElse(1);
        if (resolvedAmount < 0) {
            throw new IllegalArgumentException(
                "Negative item amount " + resolvedAmount + " for registry ID '" + key + "'"
            );
        }

        ItemStack stack = new ItemStack(value, resolvedAmount);
        Optional.ofNullable(tagCompound)
            .flatMap(ref -> ref.get(nbt))
            .map(CompoundTag::copy)
            .ifPresent(tag -> applyLegacyItemData(stack, tag));
        return stack;
    }

    private static void applyLegacyItemData(ItemStack stack, CompoundTag legacyTag) {
        CompoundTag remaining = legacyTag.copy();
        if (stack.is(Items.POTION) && "minecraft:water".equals(NbtCompat.getString(remaining, "Potion"))) {
            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
            remaining.remove("Potion");
        }
        ItemStackUtil.setCustomData(stack, remaining);
    }

    private static Identifier parseRegistryId(String type, String id) {
        try {
            return Identifier.parse(id);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid " + type + " registry ID '" + id + "'", e);
        }
    }
}
