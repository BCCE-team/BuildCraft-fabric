package buildcraft.api.v2.robot;

import net.minecraft.resources.Identifier;

public interface RobotTask {
    Identifier typeId();
    RobotTaskResult tick(RobotTaskContext context);
}
