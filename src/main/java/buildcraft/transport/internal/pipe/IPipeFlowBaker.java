package buildcraft.transport.internal.pipe;

import java.util.List;

import buildcraft.lib.compat.mc2612.client.renderer.block.model.BakedQuad;

public interface IPipeFlowBaker<F extends PipeFlow> {
    List<BakedQuad> bake(F flow);
}
