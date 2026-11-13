/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.silicon.client.model.key;

import buildcraft.transport.internal.pluggable.PluggableModelKey;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.Direction;
import buildcraft.lib.compat.RenderCompat;

public class KeyPlugPulsar extends PluggableModelKey {
    public KeyPlugPulsar(Direction side) {
        super(RenderCompat.cutout(), side);
    }
}
