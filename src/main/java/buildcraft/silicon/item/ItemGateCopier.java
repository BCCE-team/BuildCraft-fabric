package buildcraft.silicon.item;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import buildcraft.lib.misc.ItemStackUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import buildcraft.lib.compat.NbtCompat;
import buildcraft.lib.compat.RegistryCompat;

public class ItemGateCopier extends Item {
    public static final String NBT_DATA = "gate_data";

    public ItemGateCopier() {
        super(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(1));
    }


    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, tooltip, flag);
        if (getCopiedGateData(stack) != null) {
            tooltip.accept(Component.translatable("buildcraft.item.nonclean.usage"));
        }
    }

    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (world.isClientSide()) {
            return InteractionResult.PASS;
        }
        if (player.isDescending()) {
            return clearData(stack);
        }
        return InteractionResult.PASS;
    }

    private InteractionResult clearData(@Nonnull ItemStack stack) {
        if (getCopiedGateData(stack) == null) {
            return InteractionResult.PASS;
        }
        CompoundTag nbt = ItemStackUtil.getCustomData(stack);
        nbt.remove(NBT_DATA);
        ItemStackUtil.setCustomData(stack, nbt);
        return InteractionResult.SUCCESS;
    }


    public static CompoundTag getCopiedGateData(ItemStack stack) {
        CompoundTag data = ItemStackUtil.getCustomData(stack);
        return data.contains(NBT_DATA) ? NbtCompat.getCompound(data, NBT_DATA) : null;
    }

    public static void setCopiedGateData(ItemStack stack, CompoundTag nbt) {
        CompoundTag data = ItemStackUtil.getCustomData(stack);
        data.put(NBT_DATA, nbt.copy());
        ItemStackUtil.setCustomData(stack, data);
    }
}
