package buildcraft.robotics.statements;

import buildcraft.lib.internal.statement.IActionInternal;
import buildcraft.lib.internal.statement.IStatementContainer;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.lib.internal.statement.StatementParameterItemStack;
import buildcraft.core.statements.BCStatement;
import buildcraft.lib.internal.core.render.ISprite;
import buildcraft.robotics.BCRoboticsSprites;
import net.minecraft.network.chat.Component;

public class ActionStationProvideFluids extends BCStatement implements IActionInternal {

    public ActionStationProvideFluids() {
        super("buildcraft:station.provide_fluids");
    }

    public int maxParameters() { return 3; }
    public int minParameters() { return 1; }

    public IStatementParameter createParameter(int index) {
        return StatementParameterItemStack.EMPTY;
    }

    public Component getDescription() {
        return Component.translatable("gate.action.station.provideFluids");
    }

    public void actionActivate(IStatementContainer container, IStatementParameter[] parameters) {}

    public ISprite getSprite() {
        return BCRoboticsSprites.ACTION_STATION_PROVIDE_FLUIDS;
    }
}
