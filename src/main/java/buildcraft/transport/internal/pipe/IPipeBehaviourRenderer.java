package buildcraft.transport.internal.pipe;

import com.mojang.blaze3d.vertex.PoseStack;

import buildcraft.lib.compat.minecraft.render.BCVertexBuffers;
public interface IPipeBehaviourRenderer<B extends PipeBehaviour> {
    void render(B behaviour, float partialTicks, PoseStack matrix, BCVertexBuffers buffer, int combinedLight, int combinedOverlay);
}
