package buildcraft.transport.internal.pluggable;

import java.util.List;

import buildcraft.lib.compat.mc2612.client.renderer.block.model.BakedQuad;
public interface IPluggableStaticBaker<K extends PluggableModelKey> {
    List<BakedQuad> bake(K key);
}
