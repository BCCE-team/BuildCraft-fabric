package buildcraft.factory;

import buildcraft.lib.platform.registry.BCRegistryBinder;
import buildcraft.lib.platform.registry.BCRegistryEntry;
import buildcraft.lib.platform.registry.BCDeferredRegister;
import java.util.ArrayList;
import java.util.List;

import buildcraft.factory.item.ItemWaterGel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.RegistryCompat;

public class BCFactoryItems {
    public static final BCDeferredRegister<Item> ITEMS = BCDeferredRegister.create("minecraft:item", BCFactory.MODID);

    public static final BCRegistryEntry<BlockItem> PUMP_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "pump",
        () -> new BlockItem(BCFactoryBlocks.PUMP_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> TANK_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "tank",
        () -> new BlockItem(BCFactoryBlocks.TANK_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> CHUTE_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "chute",
        () -> new BlockItem(BCFactoryBlocks.CHUTE_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> FLOOD_GATE_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "flood_gate",
        () -> new BlockItem(BCFactoryBlocks.FLOOD_GATE_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> MINING_WELL_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "mining_well",
        () -> new BlockItem(BCFactoryBlocks.MINING_WELL_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> DISTILLER_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "distiller",
        () -> new BlockItem(BCFactoryBlocks.DISTILLER_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> HEAT_EXCHANGE_BLOCK_ITEM = RegistryCompat.registerItem(ITEMS, "heat_exchange",
        () -> new BlockItem(BCFactoryBlocks.HEATEXCHANGE_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> AUTO_BENCH_ITEM = RegistryCompat.registerItem(ITEMS, "autoworkbench_item",
        () -> new BlockItem(BCFactoryBlocks.AUTO_BENCH_BLOCK.get(), RegistryCompat.itemProperties(new Item.Properties())));

    public static final BCRegistryEntry<ItemWaterGel> WATER_GEL_SPAWN =
        RegistryCompat.registerItem(ITEMS, "water_gel", ItemWaterGel::new);
    public static final BCRegistryEntry<Item> GEL =
        RegistryCompat.registerItem(ITEMS, "gel", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));

    private BCFactoryItems() {
    }

    static void registry(BCRegistryBinder bus) {
        ITEMS.register(bus);
    }

    public static List<ItemStack> getCreativeTabItems() {
        List<ItemStack> items = new ArrayList<>();
        add(items, PUMP_BLOCK_ITEM);
        add(items, TANK_BLOCK_ITEM);
        add(items, CHUTE_BLOCK_ITEM);
        add(items, FLOOD_GATE_BLOCK_ITEM);
        add(items, MINING_WELL_BLOCK_ITEM);
        add(items, DISTILLER_BLOCK_ITEM);
        add(items, HEAT_EXCHANGE_BLOCK_ITEM);
        add(items, AUTO_BENCH_ITEM);
        add(items, WATER_GEL_SPAWN);
        add(items, GEL);
        return items;
    }

    private static void add(List<ItemStack> items, BCRegistryEntry<? extends Item> item) {
        if (item.isBound()) {
            items.add(item.get().getDefaultInstance());
        }
    }
}
