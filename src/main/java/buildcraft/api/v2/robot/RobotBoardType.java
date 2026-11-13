package buildcraft.api.v2.robot;

import java.util.Objects;
import java.util.Set;
import net.minecraft.resources.Identifier;

public record RobotBoardType(Identifier id, int tier, Set<Identifier> taskTypes) {
    public RobotBoardType {
        Objects.requireNonNull(id, "id");
        if (tier < 0) throw new IllegalArgumentException("tier must be non-negative");
        taskTypes = Set.copyOf(Objects.requireNonNull(taskTypes, "taskTypes"));
    }
}
