package buildcraft.robotics;

import buildcraft.transport.internal.pipe.PipeApi;
import buildcraft.transport.internal.pluggable.PluggableDefinition;
import buildcraft.lib.BCLibRegistries;
import buildcraft.robotics.plug.RobotStationPluggable;
import net.minecraft.resources.Identifier;

public final class BCRoboticsPlugs {
    public static PluggableDefinition robotStation;

    private BCRoboticsPlugs() {
    }

    public static void preInit() {
        if (robotStation == null) {
            robotStation = new PluggableDefinition(idFor("robot_station"), RobotStationPluggable::readFromNbt, RobotStationPluggable::loadFromBuffer);
        }
        BCLibRegistries.initApiRegistries();
        PipeApi.pluggableRegistry.register(robotStation);
    }

    private static Identifier idFor(String name) {
        return Identifier.fromNamespaceAndPath(BCRobotics.MODID, name);
    }
}
