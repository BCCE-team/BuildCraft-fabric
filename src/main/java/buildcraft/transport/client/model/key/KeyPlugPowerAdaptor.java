package buildcraft.transport.client.model.key;

import buildcraft.transport.internal.pluggable.PluggableModelKey;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.Direction;
import buildcraft.lib.compat.RenderCompat;

public class KeyPlugPowerAdaptor extends PluggableModelKey {
    public KeyPlugPowerAdaptor(Direction side) {
        super(RenderCompat.cutout(), side);
    }
}
