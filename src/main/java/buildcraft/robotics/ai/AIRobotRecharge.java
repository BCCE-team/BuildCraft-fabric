package buildcraft.robotics.ai;

import buildcraft.robotics.internal.legacy.robots.AIRobot;
import buildcraft.robotics.internal.legacy.robots.DockingStation;
import buildcraft.robotics.internal.legacy.robots.EntityRobotBase;
import buildcraft.robotics.IStationFilter;
import net.minecraft.world.phys.Vec3;

public class AIRobotRecharge extends AIRobot {
    public AIRobotRecharge(EntityRobotBase robot) {
        super(robot);
    }

    public void start() {
        robot.getRegistry().releaseResources(robot);
        robot.setDeltaMovement(Vec3.ZERO);

        startDelegateAI(new AIRobotSearchAndGotoStation(robot, new IStationFilter() {
            public boolean matches(DockingStation station) {
                return station.providesPower();
            }
        }, null));
    }

    public int getEnergyCost() {
        return 0;
    }

    public void update() {
        if (robot.getEnergy() >= EntityRobotBase.MAX_ENERGY - 500) {
            terminate();
        }
    }

    public void delegateAIEnded(AIRobot ai) {
        if (ai instanceof AIRobotSearchAndGotoStation && !ai.success()) {
            setSuccess(false);
            terminate();
        }
    }
}
