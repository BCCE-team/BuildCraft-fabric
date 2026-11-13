/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.transport.statements;

import java.util.Locale;

import buildcraft.lib.internal.statement.IActionInternal;
import buildcraft.lib.internal.statement.IStatement;
import buildcraft.lib.internal.statement.IStatementContainer;
import buildcraft.lib.internal.statement.IStatementParameter;
import buildcraft.core.statements.BCStatement;
import buildcraft.lib.client.sprite.SpriteHolderRegistry.SpriteHolder;
import buildcraft.transport.BCTransportSprites;
import buildcraft.transport.BCTransportStatements;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.network.chat.Component;

public class ActionPipeDirection extends BCStatement implements IActionInternal {
    public final Direction direction;

    public ActionPipeDirection(Direction direction) {
        super("buildcraft:pipe.dir." + direction.name().toLowerCase(Locale.ROOT), "buildcraft.pipe.dir." + direction.name().toLowerCase(Locale.ROOT));
        this.direction = direction;
    }

    public Component getDescription() {
        Component side = Component.translatable("direction." + direction.getName());
        return Component.translatable("gate.action.pipe.direction", side);
    }

    public IStatement rotateLeft() {
        Direction face = direction.getAxis() == Axis.Y ? direction : direction.getClockWise();
        return BCTransportStatements.ACTION_PIPE_DIRECTION[face.ordinal()];
    }

    public void actionActivate(IStatementContainer source, IStatementParameter[] parameters) {}

    public String toString() {
        return "ActionPipeDirection[" + direction + "]";
    }

    public SpriteHolder getSprite() {
        return BCTransportSprites.getPipeDirection(direction);
    }

    public IStatement[] getPossible() {
        return BCTransportStatements.ACTION_PIPE_DIRECTION;
    }
}
