package buildcraft.core;

import buildcraft.lib.platform.registry.BCRegistryBinder;
import buildcraft.lib.platform.registry.BCRegistryEntry;
import buildcraft.lib.platform.registry.BCDeferredRegister;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import buildcraft.lib.internal.enums.EnumEngineType;
import buildcraft.lib.internal.enums.EnumSpring;
import buildcraft.core.item.MapLocationType;
import buildcraft.core.item.ItemFragileFluidContainer;
import buildcraft.core.item.ItemList_BC8;
import buildcraft.core.item.ItemMapLocation;
import buildcraft.core.item.ItemMarkerConnector;
import buildcraft.core.item.ItemPaintbrush_BC8;
import buildcraft.core.item.ItemVolumeBox;
import buildcraft.core.item.ItemWrench;
import buildcraft.lib.item.ItemByEnum;
import buildcraft.lib.BCLibItems;
import buildcraft.lib.item.MultiBlockItem;
import buildcraft.lib.list.ListHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import buildcraft.lib.platform.client.ClientModelProperties;
import net.minecraft.world.entity.ItemOwner;
import javax.annotation.Nullable;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.RegistryCompat;

public class BCCoreItems {
    public static final BCDeferredRegister<Item> ITEMS = BCDeferredRegister.create("minecraft:item", BCCore.MODID);
    public static final BCRegistryEntry<Item> WRENCH = RegistryCompat.registerItem(ITEMS, "wrench", ItemWrench::new);
    public static final BCRegistryEntry<Item> GEAR_WOOD = RegistryCompat.registerItem(ITEMS, "gears/gear_wood", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<Item> GEAR_STONE = RegistryCompat.registerItem(ITEMS, "gears/gear_stone", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<Item> GEAR_IRON = RegistryCompat.registerItem(ITEMS, "gears/gear_iron", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<Item> GEAR_GOLD = RegistryCompat.registerItem(ITEMS, "gears/gear_gold", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<Item> GEAR_DIAMOND = RegistryCompat.registerItem(ITEMS, "gears/gear_diamond", () -> new Item(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<ItemPaintbrush_BC8> PAINT_BRUSH = RegistryCompat.registerItem(ITEMS, "paintbrush/clean", () -> new ItemPaintbrush_BC8(RegistryCompat.itemProperties(new Item.Properties()), null));
    public static final BCRegistryEntry<ItemMarkerConnector> MARKER_CONNECTOR = RegistryCompat.registerItem(ITEMS, "marker_connector", () -> new ItemMarkerConnector(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(1)));
    public static final BCRegistryEntry<ItemVolumeBox> VOLUME_BOX = RegistryCompat.registerItem(ITEMS, "volume_box", () -> new ItemVolumeBox(RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<ItemMapLocation> MAP_LOCATION = RegistryCompat.registerItem(ITEMS, "map_location", () -> new ItemMapLocation(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(16)));
    public static final BCRegistryEntry<ItemList_BC8> LIST = RegistryCompat.registerItem(ITEMS, "list", () -> new ItemList_BC8(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(1)));



    public static final BCRegistryEntry<ItemFragileFluidContainer> FRAGILE_FLUID_SHARD = RegistryCompat.registerItem(ITEMS, "fragile_fluid_shard", ItemFragileFluidContainer::new);



    public static final EnumMap<DyeColor, ItemPaintbrush_BC8> PAINT_BRUSHS = ItemByEnum.creatItems(ItemPaintbrush_BC8::new, RegistryCompat.itemProperties(new Item.Properties()).durability(64),
            DyeColor.values(), DyeColor.class, "paintbrush", ITEMS);;
    public static final EnumMap<EnumEngineType, MultiBlockItem<EnumEngineType>> ENGINE_ITEM_MAP = new EnumMap<EnumEngineType, MultiBlockItem<EnumEngineType>>(EnumEngineType.class);
    public static final EnumMap<EnumSpring, MultiBlockItem<EnumSpring>> SPRING_ITEM_MAP = new EnumMap<EnumSpring, MultiBlockItem<EnumSpring>>(EnumSpring.class);

    public static final BCRegistryEntry<MultiBlockItem<EnumEngineType>> ENGINE_RESTONE_ITEM_BC8 = RegistryCompat.registerItem(ITEMS, "engine_redstone", () -> new MultiBlockItem<EnumEngineType>(BCCoreBlocks.ENGINE_BC8.get(),RegistryCompat.itemProperties(new Item.Properties()),EnumEngineType.WOOD, ENGINE_ITEM_MAP));
    public static final BCRegistryEntry<MultiBlockItem<EnumEngineType>> ENGINE_CREATIVE_ITEM_BC8 = RegistryCompat.registerItem(ITEMS, "engine_creative", () -> new MultiBlockItem<EnumEngineType>(BCCoreBlocks.ENGINE_BC8.get(),RegistryCompat.itemProperties(new Item.Properties()),EnumEngineType.CREATIVE, ENGINE_ITEM_MAP));

    public static final BCRegistryEntry<MultiBlockItem<EnumSpring>> SPRING_WATER = RegistryCompat.registerItem(ITEMS, "spring_water", () -> new MultiBlockItem<EnumSpring>(BCCoreBlocks.SPRING.get(), RegistryCompat.itemProperties(new Item.Properties()), EnumSpring.WATER, SPRING_ITEM_MAP));
    public static final BCRegistryEntry<MultiBlockItem<EnumSpring>> SPRING_OIL = RegistryCompat.registerItem(ITEMS, "spring_oil", () -> new MultiBlockItem<EnumSpring>(BCCoreBlocks.SPRING.get(), RegistryCompat.itemProperties(new Item.Properties()), EnumSpring.OIL, SPRING_ITEM_MAP));

    public static final BCRegistryEntry<BlockItem> MARKER_PATH = RegistryCompat.registerItem(ITEMS, "marker_path", () -> new BlockItem(BCCoreBlocks.MARKER_PATH.get(), RegistryCompat.itemProperties(new Item.Properties())));
    public static final BCRegistryEntry<BlockItem> MARKER_VOLUME = RegistryCompat.registerItem(ITEMS, "marker_volume", () -> new BlockItem(BCCoreBlocks.MARKER_VOLUME.get(), RegistryCompat.itemProperties(new Item.Properties())));


    /** Core-owned contents of the BuildCraft main creative tab. */
    public static List<ItemStack> getCreativeTabItems() {
        List<ItemStack> items = new ArrayList<>();
        add(items, BCLibItems.GUIDE);
        add(items, MARKER_VOLUME);
        add(items, MARKER_PATH);
        add(items, VOLUME_BOX);
        add(items, ENGINE_RESTONE_ITEM_BC8);
        add(items, ENGINE_CREATIVE_ITEM_BC8);
        add(items, WRENCH);
        add(items, GEAR_WOOD);
        add(items, GEAR_STONE);
        add(items, GEAR_IRON);
        add(items, GEAR_GOLD);
        add(items, GEAR_DIAMOND);
        add(items, PAINT_BRUSH);
        PAINT_BRUSHS.values().forEach(item -> items.add(new ItemStack(item)));
        add(items, LIST);
        add(items, MAP_LOCATION);
        add(items, MARKER_CONNECTOR);
        return items;
    }

    private static void add(List<ItemStack> items, BCRegistryEntry<? extends Item> item) {
        items.add(new ItemStack(item.get()));
    }

    static void registry(BCRegistryBinder m) {
        ITEMS.register(m);
    }

    public static void registerItemModelProperties(ClientModelProperties.Ranges event) {
        event.register(Identifier.fromNamespaceAndPath("buildcraftcore", "map_type"), MapTypeModelProperty.MAP_CODEC);
        event.register(Identifier.fromNamespaceAndPath("buildcraftcore", "isempty"), ListEmptyModelProperty.MAP_CODEC);
    }

    public record MapTypeModelProperty() implements RangeSelectItemModelProperty {
        public static final MapCodec<MapTypeModelProperty> MAP_CODEC = MapCodec.unit(new MapTypeModelProperty());

        public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
            return (8 - MapLocationType.getFromStack(stack).meta) / 8.0F;
        }

        public MapCodec<MapTypeModelProperty> type() {
            return MAP_CODEC;
        }
    }

    public record ListEmptyModelProperty() implements RangeSelectItemModelProperty {
        public static final MapCodec<ListEmptyModelProperty> MAP_CODEC = MapCodec.unit(new ListEmptyModelProperty());

        public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
            return ListHandler.hasItems(stack) ? 1.0F : 0.0F;
        }

        public MapCodec<ListEmptyModelProperty> type() {
            return MAP_CODEC;
        }
    }
}
