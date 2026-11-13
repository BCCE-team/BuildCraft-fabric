package buildcraft.robotics;

import buildcraft.lib.platform.registry.BCRegistryBinder;
import buildcraft.lib.platform.registry.BCRegistryEntry;
import buildcraft.lib.platform.registry.BCDeferredRegister;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import buildcraft.robotics.item.ItemRedstoneBoard;
import buildcraft.robotics.item.ItemRobot;
import buildcraft.robotics.item.ItemRobotStation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.RegistryCompat;

public final class BCRoboticsItems {
    public static final BCDeferredRegister<Item> ITEMS = BCDeferredRegister.create("minecraft:item", BCRobotics.MODID);

    public static final BCRegistryEntry<ItemRobot> ROBOT = RegistryCompat.registerItem(ITEMS, "robot",
            () -> new ItemRobot(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(1)));

    public static final BCRegistryEntry<ItemRobotStation> ROBOT_STATION = RegistryCompat.registerItem(ITEMS, "robot_station",
            () -> new ItemRobotStation(RegistryCompat.itemProperties(new Item.Properties())));

    public static final BCRegistryEntry<ItemRedstoneBoard> REDSTONE_BOARD = RegistryCompat.registerItem(ITEMS, "redstone_board",
            () -> new ItemRedstoneBoard(RegistryCompat.itemProperties(new Item.Properties()).stacksTo(16)));

    public static final BCRegistryEntry<BlockItem> ZONE_PLANNER = RegistryCompat.registerItem(ITEMS, "zone_planner",
            () -> new BlockItem(BCRoboticsBlocks.ZONE_PLANNER.get(), RegistryCompat.itemProperties(new Item.Properties())));

    public static final BCRegistryEntry<BlockItem> REQUESTER = RegistryCompat.registerItem(ITEMS, "requester",
            () -> new BlockItem(BCRoboticsBlocks.REQUESTER.get(), RegistryCompat.itemProperties(new Item.Properties())));

    private BCRoboticsItems() {
    }

    public static void registry(BCRegistryBinder bus) {
        ITEMS.register(bus);
    }

    /** All robot, board and station stacks displayed in the dedicated robotics tab. */
    public static Collection<ItemStack> getRoboticsTabItems() {
        List<ItemStack> stacks = new ArrayList<>();
        ROBOT.get().addCreativeTabItems(stacks::add);
        REDSTONE_BOARD.get().addCreativeTabItems(stacks::add);
        stacks.add(ROBOT_STATION.get().getDefaultInstance());
        return List.copyOf(stacks);
    }

    /** Robotics blocks exposed through BuildCraft's main creative tab. */
    public static Collection<ItemStack> getMainTabItems() {
        return List.of(
                ZONE_PLANNER.get().getDefaultInstance(),
                REQUESTER.get().getDefaultInstance()
        );
    }
}
