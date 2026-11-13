package buildcraft.builders.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

/** A placeable Quarry with the enchanting rules of a diamond pickaxe, but no durability. */
public class ItemQuarry extends BlockItem {
    public ItemQuarry(Block block, Item.Properties properties) {
        super(block, properties);
    }


}
