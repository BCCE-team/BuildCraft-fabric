package buildcraft.robotics.internal.api2;

import buildcraft.api.v2.BuildCraftApi;
import buildcraft.api.v2.BuildCraftServices;
import buildcraft.api.v2.permission.AutomationActor;
import buildcraft.api.v2.robot.RobotEventContext;
import buildcraft.api.v2.robot.RobotEventDecision;
import buildcraft.api.v2.robot.RobotEventKind;
import buildcraft.robotics.internal.legacy.robots.EntityRobotBase;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import buildcraft.lib.compat.GameProfileCompat;

/** Internal bridge from live robot interactions to API2 lifecycle listeners. */
public final class RobotEventSupport {
    private RobotEventSupport() {}

    public static boolean denied(RobotEventKind kind, EntityRobotBase robot, Player player, ItemStack heldItem) {
        AutomationActor actor = player == null
            ? AutomationActor.system(Objects.requireNonNull(Identifier.tryParse("buildcraft:robot")))
            : AutomationActor.player(player.getUUID(), GameProfileCompat.name(player.getGameProfile()));
        RobotEventContext context = new RobotEventContext(
            kind,
            RobotServiceImpl.view(robot),
            robot.level(),
            Optional.of(actor),
            heldItem == null ? ItemStack.EMPTY : heldItem
        );
        return BuildCraftApi.service(BuildCraftServices.ROBOTS).evaluateEvent(context) == RobotEventDecision.DENY;
    }
}
