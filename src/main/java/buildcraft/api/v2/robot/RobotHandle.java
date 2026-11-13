package buildcraft.api.v2.robot;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public interface RobotHandle {
    long id();
    Optional<UUID> owner();
    BlockPos blockPosition();
    RobotStatus status();
    Optional<Identifier> currentTaskType();
    Optional<RobotControl> control();
}
