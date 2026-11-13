/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.builders.block;

import java.util.List;
import java.util.Map;

import buildcraft.lib.internal.debug.BCLog;
import buildcraft.lib.internal.properties.BuildCraftProperties;
import buildcraft.builders.BCBuildersBlocks;
import buildcraft.lib.block.BlockBCBase_Neptune;
import buildcraft.transport.block.BlockPipeHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import buildcraft.lib.compat.RegistryCompat;

public class BlockFrame extends BlockBCBase_Neptune {
    public static final Map<Direction, BooleanProperty> CONNECTED_MAP = BuildCraftProperties.CONNECTED_MAP;

    public static final VoxelShape BASE_AABB = BlockPipeHolder.BOX_CENTER;
    public static final VoxelShape[] CONNECTION_AABB = BlockPipeHolder.BOX_FACES;
    

    public BlockFrame() {
        super(RegistryCompat.blockProperties(BlockBehaviour.Properties.of()).mapColor(MapColor.STONE).sound(SoundType.METAL).strength(0.25f)
                .forceSolidOn()
				.explosionResistance(3.0f));
        this.registerDefaultState(this.defaultBlockState()
        		.setValue(BuildCraftProperties.CONNECTED_DOWN, false)
            	.setValue(BuildCraftProperties.CONNECTED_EAST, false)
            	.setValue(BuildCraftProperties.CONNECTED_NORTH, false)
            	.setValue(BuildCraftProperties.CONNECTED_WEST, false)
            	.setValue(BuildCraftProperties.CONNECTED_SOUTH, false)
            	.setValue(BuildCraftProperties.CONNECTED_UP, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> bs) {
    	BuildCraftProperties.CONNECTED_MAP.values().forEach(bs::add);
    	super.createBlockStateDefinition(bs);
    }
    
    

	public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        BlockState state = super.getStateForPlacement(bpc);
        if (state == null) return null;
        for (Direction side : CONNECTED_MAP.keySet()) {
            Block block = bpc.getLevel().getBlockState(bpc.getClickedPos().offset(side.getUnitVec3i())).getBlock();
            state = state.setValue(CONNECTED_MAP.get(side), block instanceof BlockFrame || block instanceof BlockQuarry);
        }
        return state;
	}

    /**
     * Minecraft 1.21.11 no longer drives this regular block through the legacy
     * neighborChanged signature. The quarry places frame blocks one at a time, so
     * without this callback only the newly placed frame sees its existing neighbour;
     * the existing frame never gains the reciprocal connection and the structure is
     * rendered as separated centre cubes with gaps between them.
     */
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess,
            BlockPos pos, Direction direction, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        Block neighbour = neighbourState.getBlock();
        state = super.updateShape(state, world, scheduledTickAccess, pos, direction, neighbourPos, neighbourState, random);
        return state.setValue(CONNECTED_MAP.get(direction),
            neighbour instanceof BlockFrame || neighbour instanceof BlockQuarry);
    }

	public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
		return false;
	}

	public boolean isOcclusionShapeFullBlock(BlockState p_222959_, BlockGetter p_222960_, BlockPos p_222961_) {
		return false;
	}
	
	public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
		return false;
	}

	
	
	public boolean skipRendering(BlockState state, BlockState other, Direction side) {
        Direction[] facings = CONNECTED_MAP.keySet().stream()
                .filter(facing -> state.getValue(CONNECTED_MAP.get(facing)))
                .toArray(Direction[]::new);
        if (facings.length == 1) {
            return side == facings[0];
        } else if (facings.length == 2 && facings[0] == facings[1].getOpposite()) {
            return side == facings[0] || side == facings[1];
        }
        return false;
	}
    
    
    
	public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_,
			CollisionContext p_60558_) {
    	Direction[] dirs = Direction.values();
    	Direction[] result = new Direction[6];
    	int len = 0;
    	for(int i = 0;i < 6;i++) {
    		if(state.getValue(CONNECTED_MAP.get(dirs[i])))
    			result[len++] = dirs[i]; 
    	}
    	return BlockPipeHolder.getCachedPipeShape(result, len);
	}

	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		return List.of();
	}
    


}
