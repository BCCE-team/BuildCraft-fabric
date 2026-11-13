package buildcraft.robotics.statements;

import buildcraft.lib.internal.statement.IActionInternal;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.core.statements.BCStatement;
import buildcraft.core.statements.StatementParameterItemStackExact;

public abstract class ActionStationInputItems extends BCStatement implements IActionInternal {

    public ActionStationInputItems(String... tags) {
        super(tags);
    }

    public int maxParameters() { return 3; }
    public int minParameters() { return 1; }

    public IStatementParameter createParameter(int index) {
        return new StatementParameterItemStackExact();
    }
}
