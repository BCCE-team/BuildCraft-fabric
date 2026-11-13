/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.factory.item;

import buildcraft.factory.BCFactoryBlocks;
import buildcraft.factory.block.BlockWaterGel;
import buildcraft.factory.block.BlockWaterGel.GelStage;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import buildcraft.lib.compat.RegistryCompat;

public class ItemWaterGel extends Item {

    public ItemWaterGel() {
        super(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(16));
    }
    
	public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);//start, end, true, false, true);

        if (ray == null || ray.getBlockPos() == null) {
            return InteractionResult.FAIL;
        }

        Block b = world.getBlockState(ray.getBlockPos()).getBlock();

        if (b != Blocks.WATER) {
            return InteractionResult.FAIL;
        }
        
        // Check the actual server-side placement result before consuming the gel.
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (!world.mayInteract(player, ray.getBlockPos())
            || !player.mayUseItemAt(ray.getBlockPos(), ray.getDirection(), stack)) {
            return InteractionResult.FAIL;
        }
        if (!world.setBlockAndUpdate(ray.getBlockPos(),
            BCFactoryBlocks.WATER_GEL.get().defaultBlockState().setValue(BlockWaterGel.PROP_STAGE, GelStage.SPREAD_0))) {
            return InteractionResult.FAIL;
        }
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
            SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        return InteractionResult.SUCCESS;
	}


}
